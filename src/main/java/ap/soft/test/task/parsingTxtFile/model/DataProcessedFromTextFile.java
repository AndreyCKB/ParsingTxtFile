package ap.soft.test.task.parsingTxtFile.model;

import java.util.Collections;
import java.util.List;

public abstract class  DataProcessedFromTextFile {
    protected final Node firstNode;
    protected final List<String> strings;
    protected Node currentNode;
    protected int countNode;


    public DataProcessedFromTextFile(String currentLine, List<String> strings) {
        this.strings = strings;
        this.countNode = 0;
        addFirstLine(currentLine);
        this.firstNode = initFirstNode();
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

    protected boolean isAddEmptyLine(String currentLine){
        if (currentLine.isEmpty()){
            strings.add(currentLine);
            return true;
        } else {
            return false;
        }
    }

    protected int getDepth(char[] chars){
        int result = 0;
        while (chars[result] == '#'){
            ++result;
        }
        return result;
    }

}
