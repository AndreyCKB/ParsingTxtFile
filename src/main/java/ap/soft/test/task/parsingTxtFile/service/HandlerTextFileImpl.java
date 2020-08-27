package ap.soft.test.task.parsingTxtFile.service;

import ap.soft.test.task.parsingTxtFile.model.ClientType;
import ap.soft.test.task.parsingTxtFile.model.FileStructure;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.ReadOnlyFileSystemException;
import java.util.ArrayList;

@Service
public class HandlerTextFileImpl implements HandlerTextFile {

    @Override
    public FileStructure parsingTxtFile(MultipartFile file, ClientType clientType) throws IOException, ReadOnlyFileSystemException {
        FileStructure result;
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        if (reader.ready()) {
            result = clientType.getFileStructure('#', reader.readLine(), new ArrayList<>());
        } else {
            throw new RuntimeException("Error reading file.");
        }
        while (reader.ready()){
            result.addString(reader.readLine());
        }
        return result;
    }

}
