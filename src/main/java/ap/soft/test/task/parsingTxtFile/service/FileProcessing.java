package ap.soft.test.task.parsingTxtFile.service;

import ap.soft.test.task.parsingTxtFile.model.DataForHtmlClient;
import ap.soft.test.task.parsingTxtFile.model.DataProcessedFromTextFile;
import ap.soft.test.task.parsingTxtFile.model.Node;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.ReadOnlyFileSystemException;
import java.util.ArrayList;


@Service
public class FileProcessing {

    public DataProcessedFromTextFile  parsingTxtFile(MultipartFile file) throws IOException, ReadOnlyFileSystemException {
        DataProcessedFromTextFile result;
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        if (reader.ready()) {
            result = new DataForHtmlClient(reader.readLine(),new ArrayList<String>());
        } else {
            throw new RuntimeException("Ошибка при чтении файла");
        }
        while (reader.ready()){
            result.addString(reader.readLine());
        }
        return result;
    }

}
