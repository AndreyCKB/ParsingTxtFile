package ap.soft.test.task.parsingTxtFile.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public enum ClientType {
    ARRAY_CLIENT{
        public FileStructure getFileStructure(char signSection, String currentLine, List<String> strings){
            return new IndexedLinesArrayClient(signSection, currentLine, new ArrayList<>());
        }
    },

    HTML_CLIENT{
        public FileStructure getFileStructure(char signSection, String currentLine, List<String> strings){
            return new DataForHtmlClient(signSection, currentLine, new ArrayList<>());
        }
    };

    public abstract FileStructure getFileStructure(char signSection, String currentLine, List<String> strings);
}
