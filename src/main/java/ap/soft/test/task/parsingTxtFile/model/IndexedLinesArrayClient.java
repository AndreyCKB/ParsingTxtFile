package ap.soft.test.task.parsingTxtFile.model;

import java.util.List;

public class IndexedLinesArrayClient extends DataHandlerFromTextFile {
    public IndexedLinesArrayClient(char signSection, String currentLine, List<String> strings) {
        super(signSection, currentLine, strings);
    }

    @Override
    public void addString(String currentLine) {
        strings.add(currentLine);
        if (!currentLine.isEmpty() || currentLine.toCharArray()[0] == signSection ) {
            currentNode = currentNode.addNode(strings.size() - 1, getDepth(currentLine.toCharArray()));
        }
    }

    @Override
    protected Node initFirstNode() {
        return Node.firstNode(0);
    }

    @Override
    protected void addFirstLine(String currentLine) {
        addString(currentLine);
    }
}
