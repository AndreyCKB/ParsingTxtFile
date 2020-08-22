package ap.soft.test.task.parsingTxtFile.model;

import java.util.Collections;
import java.util.List;

public class DataProcessedFromTextFile {
    private final Node firstNode;
    private Node node;
    private final List<String> strings;

    public DataProcessedFromTextFile(Node node, List<String> strings) {
        this.firstNode = node;
        this.node = node;
        this.strings = strings;
    }

    public Node getFirstNode() {
        return firstNode;
    }

    public List<String> getStrings() {
        return Collections.unmodifiableList(strings);
    }

    public void addString(String str){
        strings.add(str);
    }

    public void addNode(String nameNode, int deph){
        node = node.addNode(nameNode,deph);
    }

    @Override
    public String toString() {
//        StringBuilder stringBuilder = new StringBuilder();
//        firstNode.print(firstNode,0, stringBuilder);
        return  //stringBuilder.toString() +
                "strings=" + strings +
                '}';
    }
}
