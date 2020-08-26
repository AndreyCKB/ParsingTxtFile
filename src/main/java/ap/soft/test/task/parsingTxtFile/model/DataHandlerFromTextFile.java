package ap.soft.test.task.parsingTxtFile.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collections;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public abstract class DataHandlerFromTextFile {

    protected final Node firstNode;
    protected final List<String> strings;
    @JsonIgnore
    protected Node currentNode;
    @JsonIgnore
    protected final char signSection;
    @JsonIgnore
    protected int countNode;


    public DataHandlerFromTextFile(char signSection, String currentLine, List<String> strings) {
        this.signSection = signSection;
        this.strings = strings;
        this.countNode = 0;
        this.firstNode = initFirstNode();
        this.currentNode = this.firstNode;
        addFirstLine(currentLine);

    }

    public abstract void addString(String currentLine);

    protected abstract Node initFirstNode();

    protected abstract void addFirstLine(String currentLine);

    public Node getFirstNode() {
        return firstNode;
    }

    public List<String> getStrings() {
        return Collections.unmodifiableList(strings);
    }



    protected int getDepth(char[] chars){
        int result = 0;
        while (chars[result] == signSection){
            ++result;
        }
        return result;
    }

}
