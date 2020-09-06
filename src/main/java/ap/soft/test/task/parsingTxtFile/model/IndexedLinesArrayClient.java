package ap.soft.test.task.parsingTxtFile.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Indexed;

import java.util.List;

public class IndexedLinesArrayClient extends FileStructure {

    public static final Logger logger = LoggerFactory.getLogger(IndexedLinesArrayClient.class);

    public IndexedLinesArrayClient(char signSection, String currentLine, List<String> strings) {
        super(signSection, currentLine, strings);
        logger.trace("Constructor \"IndexedLinesArrayClient\" started");
        addString(currentLine);
    }

    @Override
    public void addString(String currentLine) {
        logger.trace("Method \"addString\" started");
        logger.debug("Current line = \"{}\"", currentLine);

        this.strings.add(currentLine);
        logger.info("Line added to list strings, Line = \"{}\"", this.strings.get(strings.size() - 1) );

        if (!currentLine.isEmpty() && currentLine.toCharArray()[0] == this.signSection ) {
            this.currentNode = this.currentNode.addNode(this.strings.size() - 1, getDepth(currentLine.toCharArray()));
            logger.info("New node added to file structure");
        }
    }

    @Override
    protected Node<Integer> initFirstNode() {
        logger.trace("Method \"initFirstNode\" started");
        return Node.firstNode(0);
    }

}
