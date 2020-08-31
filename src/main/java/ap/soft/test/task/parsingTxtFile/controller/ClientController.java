package ap.soft.test.task.parsingTxtFile.controller;

import ap.soft.test.task.parsingTxtFile.model.ClientType;
import ap.soft.test.task.parsingTxtFile.service.HandlerTextFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ClientController {

    private final HandlerTextFile fileProcessing;

    public ClientController(HandlerTextFile fileProcessing) {
        this.fileProcessing = fileProcessing;
    }

    @PostMapping(value = "/arrayClient")
    public ResponseEntity<?> arrayClient(@RequestParam("file") MultipartFile file) {
        return upload(file, ClientType.ARRAY_CLIENT);
    }

    @PostMapping(value = "/htmlClient")
    public ResponseEntity<?> htmlClient(@RequestParam("file") MultipartFile file) {
        return upload(file, ClientType.HTML_CLIENT);
    }

    private ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, ClientType clientType)  {
        if (file != null && !file.isEmpty()) {
            try {
                return new ResponseEntity<>(fileProcessing.parsingTxtFile(file, clientType), HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>("File is not read", HttpStatus.EXPECTATION_FAILED);
            }
        } else {
            return new ResponseEntity<>("File is empty", HttpStatus.EXPECTATION_FAILED);
        }
    }

    @RequestMapping("/exit")
    public ResponseEntity<?> serviceDisable() {
        disable();
        return  new ResponseEntity<>("Service disabled", HttpStatus.OK);
    }

    private void disable(){
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.exit(0);
            }
        });
        thread.start();
    }

}