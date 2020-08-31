package ap.soft.test.task.parsingTxtFile.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(MultipartException.class)
    ResponseEntity<?> multipartException() {
        return new ResponseEntity<>("File not found", HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    ResponseEntity<?> maxUploadSizeException() {
        return new ResponseEntity<>("Maximum upload file size exceeded", HttpStatus.EXPECTATION_FAILED);
    }

}
