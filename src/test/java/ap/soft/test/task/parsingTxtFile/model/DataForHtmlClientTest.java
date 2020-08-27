package ap.soft.test.task.parsingTxtFile.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DataForHtmlClientTest {

    private final DataForHtmlClient clientWithoutSignSection= new DataForHtmlClient('#',"first line",new ArrayList<>());
    private final DataForHtmlClient clientWithSignSection= new DataForHtmlClient('#',"#first line",new ArrayList<>());

    @Test
    void addStringFirstLine() {
        assertTrue(this.clientWithoutSignSection.strings.size() == 1);
        assertTrue(this.clientWithoutSignSection.firstNode.getDepth() == 0);
        assertTrue(this.clientWithoutSignSection.firstNode.getChildren().isEmpty());

        assertTrue(this.clientWithSignSection.strings.size() == 1);
        assertTrue(this.clientWithSignSection.firstNode.getDepth() == 0);
        assertTrue(this.clientWithSignSection.firstNode.getChildren().size() == 1);

    }

    @Test
    void addFiveLineClientWithoutSignSection() {
        this.clientWithoutSignSection.addString("# line");
        this.clientWithoutSignSection.addString("");
        this.clientWithoutSignSection.addString("## line");
        this.clientWithoutSignSection.addString("### line");
        this.clientWithoutSignSection.addString("# line");
        assertTrue(this.clientWithoutSignSection.strings.size() == 6);
        assertTrue(this.clientWithoutSignSection.currentNode.getDepth() == 1);
        assertTrue(this.clientWithoutSignSection.currentNode.getChildren().isEmpty());
    }

    @Test
    void addFiveLineClientWithSignSection() {
        this.clientWithoutSignSection.addString("# line");
        this.clientWithoutSignSection.addString("");
        this.clientWithoutSignSection.addString("## line");
        this.clientWithoutSignSection.addString("### line");
        this.clientWithoutSignSection.addString("# line");
        assertTrue(this.clientWithoutSignSection.strings.size() == 6);
        assertTrue(this.clientWithoutSignSection.currentNode.getDepth() == 1);
        assertTrue(this.clientWithoutSignSection.currentNode.getChildren().isEmpty());
    }
}