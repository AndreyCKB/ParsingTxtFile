package ap.soft.test.task.parsingTxtFile.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class IndexedLinesArrayClient extends FileStructure {

    public static final Logger logger = LoggerFactory.getLogger(IndexedLinesArrayClient.class);

    public IndexedLinesArrayClient(char signSection, String currentLine, List<String> strings) {
        super(signSection, currentLine, strings);
        addString(currentLine);
    }

    @Override
    public void addString(String currentLine) {
        logger.debug("Current line = \"{}\"", currentLine);
        strings.add(currentLine);
        logger.info("Line added to list strings, Line = \"{}\"", strings.get(strings.size() - 1) );
        if (!currentLine.isEmpty() && currentLine.toCharArray()[0] == signSection ) {
            currentNode = currentNode.addNode(strings.size() - 1, getDepth(currentLine.toCharArray()));
            logger.info("New node added to file structure");
        }
    }

    @Override
    protected Node initFirstNode() {
        return Node.firstNode(0);
    }

}
