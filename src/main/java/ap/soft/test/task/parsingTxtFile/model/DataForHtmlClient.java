package ap.soft.test.task.parsingTxtFile.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

public class DataForHtmlClient extends FileStructure {

    @JsonIgnore
    private int countNode;

    public DataForHtmlClient(char signSection, String currentLine, List<String> strings) {
        super(signSection, currentLine, strings);
        this.countNode = 0;
        addFirstLine(currentLine);
    }

    @Override
    public void addString(String currentLine) {
        if (currentLine.isEmpty() || currentLine.toCharArray()[0] != signSection ){
            strings.add(currentLine);
        }else {
            strings.add("<a name=\"" + (++countNode) + "\"></a>" + currentLine);
            currentNode = currentNode.addNode("<a href=\"#" + countNode + "\">" + countNode + "</a>" , getDepth(currentLine.toCharArray()));
        }
    }

    @Override
    protected Node initFirstNode() {
        return Node.firstNode("<a href=\"#start\">Начало документа</a>");
    }

    private void addFirstLine(String currentLine) {
        this.addString(currentLine);
        strings.set(0,"<a name=\"start\"></a>" + strings.get(0));
    }
}
