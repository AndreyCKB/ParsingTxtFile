package ap.soft.test.task.parsingTxtFile.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NodeTest {

    private final Node<Integer> firstNode = Node.firstNode(0);

    @Test
    void addNodeDepth1() {
        this.firstNode.addNode(1,1).addNode(2,1);
        assertTrue(this.firstNode.getChildren().size() == 2);
        List<Node<Integer>> children = this.firstNode.getChildren();
        assertTrue(children.get(0).getDepth()==1);
        assertTrue(children.get(1).getDepth()==1);
    }

    @Test
    void addNodeDepth3() {
        this.firstNode.addNode(3,2).addNode(4,3);
        List<Node<Integer>> children = this.firstNode.getChildren();
        Node<Integer> integerNode = children.get(0).getChildren().get(0);
        assertTrue(integerNode.getDepth()==3);
    }
}