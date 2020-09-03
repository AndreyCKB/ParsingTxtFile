package ap.soft.test.task.parsingTxtFile.model;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Node<T> {

    public static final Logger logger = LoggerFactory.getLogger(Node.class);

    private final T lineID;
    private final int depth;
    @JsonIgnore
    private final Node parent;
    private final List<Node<T>> children;

    private Node(T lineID, int depth, Node parent) {
        this.lineID = lineID;
        this.depth = depth;
        this.parent = parent;
        this.children = new LinkedList<>();
    }

    public T getLineID() {
        return lineID;
    }

    public int getDepth() {
        return depth;
    }

    public List<Node<T>> getChildren() {
        return children;
    }

    public static <T> Node<T> firstNode(T lineID){
        return new Node(lineID,0, null);
    }

    public <T> Node<T> addNode(T lineID, int depth){
        Node<T> parent = findParentNode(this, depth);
        Node<T> node = new Node(lineID, depth, parent);
        parent.children.add(node);
        logger.info("Created " + node + " his parent " + parent);
        return node;
    }

    private Node findParentNode(Node node, int depth){
        logger.debug("Current Node depth = " + node.depth + "  new Node depth= " + depth);
        if (node.depth < depth) {
            logger.debug(node.depth + " < " + depth + " => parent found = " + node);
            return node;
        }else if (node.depth == depth){
            logger.debug(node.depth + " = " + depth + " => parent found = "+ node.parent);
            return node.parent;
        }else {
            logger.debug(node.depth + " > " + depth + " => " + " we continue fiend a parent");
            return findParentNode(node.parent, depth);
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "lineID='" + lineID + '\'' +
                ", children.size=" + ( children != null ? children.size() + "" : 0 ) + "" +
                ", depth=" + depth +
                '}';
    }
}
