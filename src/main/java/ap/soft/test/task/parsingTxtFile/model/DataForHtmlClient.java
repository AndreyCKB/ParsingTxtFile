package ap.soft.test.task.parsingTxtFile.model;

import java.util.List;

public class DataForHtmlClient extends DataHandlerFromTextFile {

    public DataForHtmlClient(char signSection, String currentLine, List<String> strings) {
        super(signSection, currentLine, strings);
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

    @Override
    protected void addFirstLine(String currentLine) {
        this.addString(currentLine);
        strings.add(0,"<a name=\"start\"></a>" + strings.get(0));
    }
}
