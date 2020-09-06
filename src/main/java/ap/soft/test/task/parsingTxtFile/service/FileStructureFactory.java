package ap.soft.test.task.parsingTxtFile.service;

import ap.soft.test.task.parsingTxtFile.model.ClientType;
import ap.soft.test.task.parsingTxtFile.model.FileStructure;

import java.util.List;

public interface FileStructureFactory {

    FileStructure getDefaultFileStructure(ClientType clientType, String currentLine);

    FileStructure getCustomFileStructure(ClientType clientType, char signSection, String currentLine, List<String> strings);


}
