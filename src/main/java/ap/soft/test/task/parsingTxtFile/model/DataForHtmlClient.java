package ap.soft.test.task.parsingTxtFile.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DataForHtmlClient extends FileStructure {

    public static final Logger logger = LoggerFactory.getLogger(DataForHtmlClient.class);

    @JsonIgnore
    private int countNode;

    public DataForHtmlClient(char signSection, String currentLine, List<String> strings) {
        super(signSection, currentLine, strings);
        logger.trace("Constructor \"DataForHtmlClient\" started.");
        this.countNode = 0;
        addFirstLine(currentLine);
    }

    @Override
    public void addString(String currentLine) {
        logger.trace("Method \"addString\" started");
        logger.debug("Current line = \"{}\"", currentLine);

        if (currentLine.isEmpty() || currentLine.toCharArray()[0] != this.signSection ){
            this.strings.add(currentLine);
            logger.info("Line added to list strings, Line = \"{}\"", currentLine);
        }else {
            this.currentNode = this.currentNode.addNode("<a href=\"#" + this.countNode + "\">" + this.countNode + "</a>" , getDepth(currentLine.toCharArray()));
            logger.info("New node added to file structure");
            this.strings.add("<a name=\"" + (++this.countNode) + "\"></a>" + currentLine);
            logger.info("Line added to list strings, Line = \"{}\"", this.strings.get(this.strings.size()-1));
        }
    }

    @Override
    protected Node<String> initFirstNode() {
        logger.trace("Method \"initFirstNode\" started");
        return Node.firstNode("<a href=\"#start\">Start of document</a>");
    }

    private void addFirstLine(String currentLine) {
        logger.trace("Method \"addFirstLine\" started");
        this.addString(currentLine);
        this.strings.set(0,"<a name=\"start\"></a>" + this.strings.get(0));
        logger.info("First line created for file structure. Line = \"{}\"", this.strings.get(0));
    }
}
