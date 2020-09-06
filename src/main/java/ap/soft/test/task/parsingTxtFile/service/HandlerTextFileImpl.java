package ap.soft.test.task.parsingTxtFile.service;

import ap.soft.test.task.parsingTxtFile.model.ClientType;
import ap.soft.test.task.parsingTxtFile.model.FileStructure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class HandlerTextFileImpl implements HandlerTextFile {

    public static final Logger logger = LoggerFactory.getLogger(HandlerTextFileImpl.class);

    private final FileStructureFactory fileStructureFactory;

    public HandlerTextFileImpl(FileStructureFactory fileStructureFactory) {
        this.fileStructureFactory = fileStructureFactory;
    }

    @Override
    public FileStructure parsingTxtFile(MultipartFile file, ClientType clientType) {
        logger.trace("Method \"parsingTxtFile\" started");
        FileStructure result;
        long indexLine = 1;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            logger.trace("BufferedReader for file initialized");
            result = initFileStructure(reader, clientType);
            logger.info("New file structure initialized. ", result);

            logger.trace("Lines reading and adding in file structure from file");
            while (reader.ready()) {
                indexLine++;
                result.addString(reader.readLine());
            }
        }catch (IOException e){
            logger.warn("Error reading line from file.\n The index of the line in which the error was found = {}\n", indexLine, e);
            throw new RuntimeException(e);
        }
        logger.info("File structure created ", result);
        return result;
    }

    private FileStructure initFileStructure(BufferedReader reader, ClientType clientType) throws IOException {
        logger.trace("Method \"initFileStructure\" started");
        String firsLine = reader.readLine();
        logger.info("First line read from file. First line = \"{}\" ", firsLine);
        return this.fileStructureFactory.getDefaultFileStructure(clientType, firsLine);
    }
}
