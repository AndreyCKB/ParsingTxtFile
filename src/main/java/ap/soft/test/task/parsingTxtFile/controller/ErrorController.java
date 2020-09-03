package ap.soft.test.task.parsingTxtFile.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;


@ControllerAdvice
public class ErrorController {

    public static final Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @ExceptionHandler(MultipartException.class)
    ResponseEntity<?> multipartException(MultipartException e) {
        logger.warn("File not found", e);
        return new ResponseEntity<>("File not found", HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    ResponseEntity<?> maxUploadSizeException(MaxUploadSizeExceededException e) {
        logger.warn("Maximum upload file size exceeded", e);
        return new ResponseEntity<>("Maximum upload file size exceeded", HttpStatus.EXPECTATION_FAILED);
    }



}
