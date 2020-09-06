package ap.soft.test.task.parsingTxtFile.service;

import ap.soft.test.task.parsingTxtFile.model.ClientType;
import ap.soft.test.task.parsingTxtFile.model.DataForHtmlClient;
import ap.soft.test.task.parsingTxtFile.model.FileStructure;
import ap.soft.test.task.parsingTxtFile.model.IndexedLinesArrayClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FileStructureFactoryImpl implements FileStructureFactory{

    public static final Logger logger = LoggerFactory.getLogger(FileStructureFactoryImpl.class);

    @Value("${fileStructure.default.signSection}")
    private char defaultSignSection;
    @Value("${fileStructure.default.sizeListStrings}")
    private int sizeListStrings;
    private List<String> strings = new ArrayList<>(this.sizeListStrings);

    @Autowired
    public FileStructureFactoryImpl() {
        logger.trace("Constructor \"FileStructureFactoryImpl\" started.");
    }

    public FileStructureFactoryImpl(char defaultSignSection, int sizeListStrings) {
        logger.trace("Constructor \"FileStructureFactoryImpl\" started. Parameters: defaultSignSection = \'{}\', sizeListStrings = \'{}\'", defaultSignSection, sizeListStrings);
        this.defaultSignSection = defaultSignSection;
        this.sizeListStrings = sizeListStrings;
    }

    public FileStructure getDefaultFileStructure(ClientType clientType, String currentLine){
        logger.trace("Method \"getDefaultFileStructure\" started. Parameters: defaultSignSection = \\'{}\\', sizeListStrings = \\'{}\\'\"",
                      this.defaultSignSection, this.sizeListStrings );
        return createFileStructure(clientType, this.defaultSignSection, currentLine, this.strings);
    }

    public FileStructure getCustomFileStructure(ClientType clientType, char signSection, String currentLine, List<String> strings){
        logger.trace("Method \"getCustomFileStructure\" started");
        return createFileStructure(clientType, signSection, currentLine, strings);
    }

    private FileStructure createFileStructure(ClientType clientType, char signSection, String currentLine, List<String> strings){
        logger.trace("Method \"createFileStructure\" started");
        logger.debug("File structure will create with parameters: ClientType = \"{}\",  SignSection = \"{}\", CurrentLine = \"{}\" Strings.size = \"{}\"",
                      clientType, signSection, currentLine, strings != null ? strings.size() : "null" );

        switch (clientType){
            case HTML_CLIENT: return new DataForHtmlClient(signSection, currentLine, strings);
            case ARRAY_CLIENT: return new IndexedLinesArrayClient(signSection, currentLine, strings);
            default: throw new IllegalArgumentException("The clint type does not exist ClientType = " + clientType);
        }
    }


}
