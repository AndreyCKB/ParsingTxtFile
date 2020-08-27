package ap.soft.test.task.parsingTxtFile.service;

import ap.soft.test.task.parsingTxtFile.model.ClientType;
import ap.soft.test.task.parsingTxtFile.model.FileStructure;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.ReadOnlyFileSystemException;

public interface HandlerTextFile {
    FileStructure parsingTxtFile(MultipartFile file, ClientType clientType) throws IOException, ReadOnlyFileSystemException;
}
