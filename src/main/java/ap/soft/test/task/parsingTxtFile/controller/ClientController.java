package ap.soft.test.task.parsingTxtFile.controller;

import ap.soft.test.task.parsingTxtFile.service.FileProcessing;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class ClientController {

    private final FileProcessing fileProcessing;

    public ClientController(FileProcessing fileProcessing) {
        this.fileProcessing = fileProcessing;
    }


    @PostMapping(value = "/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) throws MultipartException, IllegalStateException  {
        if (!file.isEmpty()) {
            try {
                return new ResponseEntity<>(fileProcessing.parsingTxtFile(file, "IndexedLinesArrayClient"), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("File is not read", HttpStatus.EXPECTATION_FAILED);
            }
        } else {
            return new ResponseEntity<>("File is empty", HttpStatus.EXPECTATION_FAILED);
        }
    }

    @RequestMapping("/*")
    @ResponseBody
    ResponseEntity<?> multipartException() {
        return new ResponseEntity<>("Incorrect URL. Correct URL  http://localhost:8080/upload; PostRequest with parameter file.", HttpStatus.NOT_FOUND);
    }



}