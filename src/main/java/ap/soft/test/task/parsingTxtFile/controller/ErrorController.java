package ap.soft.test.task.parsingTxtFile.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(MultipartException.class)
    @ResponseBody
    ResponseEntity<?> multipartException(MultipartException e) {
        return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.EXPECTATION_FAILED);
    }



}
