package ap.soft.test.task.parsingTxtFile.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collections;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public abstract class FileStructure {

    protected final Node firstNode;
    protected final List<String> strings;
    @JsonIgnore
    protected Node currentNode;
    @JsonIgnore
    protected final char signSection;

    public FileStructure(char signSection, String currentLine, List<String> strings) {
        this.signSection = signSection;
        this.strings = strings;
        this.firstNode = initFirstNode();
        this.currentNode = this.firstNode;
    }

    public abstract void addString(String currentLine);

    protected abstract Node initFirstNode();

    public Node getFirstNode() {
        return firstNode;
    }

    public List<String> getStrings() {
        return Collections.unmodifiableList(strings);
    }

    protected int getDepth(char[] chars){
        int result = 0;
        while (result < chars.length && chars[ result ] == signSection ){
            result++;
        }
        return result;
    }

    @Override
    public String toString() {
        return "FileStructure{" +
                "firstNode=" + firstNode +
                ", strings=" + strings +
                ", currentNode=" + currentNode +
                ", signSection=" + signSection +
                '}';
    }
}
