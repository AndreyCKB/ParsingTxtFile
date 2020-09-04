package ap.soft.test.task.parsingTxtFile.service;

import ap.soft.test.task.parsingTxtFile.controller.ClientController;
import ap.soft.test.task.parsingTxtFile.model.ClientType;
import ap.soft.test.task.parsingTxtFile.model.FileStructure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.ReadOnlyFileSystemException;
import java.util.ArrayList;

@Service
public class HandlerTextFileImpl implements HandlerTextFile {

    public static final Logger logger = LoggerFactory.getLogger(HandlerTextFileImpl.class);

    @Override
    public FileStructure parsingTxtFile(MultipartFile file, ClientType clientType) throws IOException, ReadOnlyFileSystemException {
        FileStructure result;
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        if (reader.ready()) {
            String firsLine = reader.readLine();
            logger.info("First line read from file. Line = \"{}\" " , firsLine );
            result = clientType.getFileStructure('#', firsLine, new ArrayList<>(500));
            logger.info("New file structure initialized. ", result);
        } else {
            logger.warn("Error reading first line from file.");
            throw new RuntimeException("Error reading file.");
        }
        logger.info("Lines reading and adding in file structure from file");
        while (reader.ready()){
            result.addString(reader.readLine());
        }
        logger.info("File structure created ", result);
        return result;
    }

}
