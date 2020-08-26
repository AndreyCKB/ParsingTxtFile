package ap.soft.test.task.parsingTxtFile.service;

import ap.soft.test.task.parsingTxtFile.model.DataForHtmlClient;
import ap.soft.test.task.parsingTxtFile.model.DataHandlerFromTextFile;

import ap.soft.test.task.parsingTxtFile.model.IndexedLinesArrayClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.ReadOnlyFileSystemException;
import java.util.ArrayList;


@Service
public class FileProcessing {

    public DataHandlerFromTextFile parsingTxtFile(MultipartFile file, String clientType) throws IOException, ReadOnlyFileSystemException {
        DataHandlerFromTextFile result;
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        if (reader.ready()) {
            result = handlerTextFile(clientType, reader.readLine());
        } else {
            throw new RuntimeException("Error reading file.");
        }
        while (reader.ready()){
            result.addString(reader.readLine());
        }
        return result;
    }

    private DataHandlerFromTextFile handlerTextFile(String clientType, String currentLine){
        DataHandlerFromTextFile result = null;
        switch (clientType){
            case "IndexedLinesArrayClient": result = new IndexedLinesArrayClient('#', currentLine, new ArrayList<>());
            case "DataForHtmlClient" : result = new DataForHtmlClient('#', currentLine, new ArrayList<>());
        }
        return result;
    }

}
