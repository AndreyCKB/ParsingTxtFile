package ap.soft.test.task.parsingTxtFile.controller;

import ap.soft.test.task.parsingTxtFile.model.ClientType;
import ap.soft.test.task.parsingTxtFile.service.HandlerTextFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class ClientController {

    public static final Logger logger = LoggerFactory.getLogger(ClientController.class);

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
        logger.info("ClientType = " + clientType.name() + " MultipartFile "
                + (file != null ? "File.size = " +  file.getSize() + " bytes," + " File.name = " + file.getOriginalFilename() : "null"));
        if (file != null && !file.isEmpty()) {
            logger.debug("File processing to start");
            try {
                return new ResponseEntity<>(fileProcessing.parsingTxtFile(file, clientType), HttpStatus.OK);
            } catch (Exception e) {
                logger.warn("file could not be processed", e);           ;
                return new ResponseEntity<>("File is not read", HttpStatus.EXPECTATION_FAILED);
            }
        } else {
            logger.debug("Error message \"File is empty\" sent to user");
            return new ResponseEntity<>("File is empty", HttpStatus.EXPECTATION_FAILED);
        }
    }

    @RequestMapping("/exit")
    public ResponseEntity<?> serviceDisable() {
        logger.debug("Service disable to start");
        disable();
        logger.debug("Message \"Service disabled\" sent client");
        return  new ResponseEntity<>("Service disabled", HttpStatus.OK);
    }

    private void disable(){
        Thread thread = new Thread(() -> {
            try {
                logger.debug("thread =" + Thread.currentThread().getName() + "started");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.warn("Error during shutdown", e);
            } finally {
                logger.info("Service disabled");
                System.exit(0);
            }
        });
        logger.debug("Created new thread =" + thread.getName());
        thread.start();
    }


}