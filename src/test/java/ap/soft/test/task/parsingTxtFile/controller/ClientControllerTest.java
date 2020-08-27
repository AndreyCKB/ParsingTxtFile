package ap.soft.test.task.parsingTxtFile.controller;

import ap.soft.test.task.parsingTxtFile.service.HandlerTextFileImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientControllerTest {
        private final ClientController clientController = new ClientController(new HandlerTextFileImpl());
    @Test
    public void HtmlStatuses() throws IOException {
        ResponseEntity<?> responseEntity = clientController.arrayClient(null);
        assertTrue(responseEntity.getStatusCode().equals(HttpStatus.EXPECTATION_FAILED));

        File file = new File("src/test/resources/test.txt");
        MultipartFile multipartFile = new MockMultipartFile("test.xlsx", new FileInputStream(file));
        responseEntity = clientController.arrayClient(multipartFile);
        assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));

        file = new File("src/test/resources/testEmpty.txt");
        multipartFile = new MockMultipartFile("test.xlsx", new FileInputStream(file));
        responseEntity = clientController.arrayClient(multipartFile);
        assertTrue(responseEntity.getStatusCode().equals(HttpStatus.EXPECTATION_FAILED));
    }
}