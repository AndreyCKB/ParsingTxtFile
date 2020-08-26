package ap.soft.test.task.parsingTxtFile.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collections;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public abstract class  DataProcessedFromTextFile {

    protected final Node firstNode;
    protected final List<String> strings;
    @JsonIgnore
    protected Node currentNode;
    @JsonIgnore
    protected int countNode;


    public DataProcessedFromTextFile(String currentLine, List<String> strings) {
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
        while (chars[result] == '#'){
            ++result;
        }
        return result;
    }

}
