package ap.soft.test.task.parsingTxtFile.controller;

import ap.soft.test.task.parsingTxtFile.service.FileProcessing;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;


@RestController
public class ClientController {

    private final FileProcessing fileProcessing;

    public ClientController(FileProcessing fileProcessing) {
        this.fileProcessing = fileProcessing;
    }

    @PostMapping(value="/upload")
    public @ResponseBody String handleFileUpload(
                                                  @RequestParam("txt_file") MultipartFile file){
        String name ="MY";
        if (!file.isEmpty()) {
            try {

                fileProcessing.parsingTxtFile(file);
//                byte[] bytes = file.getBytes();
//                BufferedOutputStream stream =
//                        new BufferedOutputStream(new FileOutputStream(new File(name + "-uploaded")));
//                stream.write(bytes);
//                stream.close();
                return "Вы удачно загрузили " + name + " в " + name + "-uploaded !";
            } catch (Exception e) {
                return "Вам не удалось загрузить " + name + " => " + e.getMessage();
            }

        } else {
            return "Вам не удалось загрузить " + name + " потому что файл пустой.";
        }
    }
}
