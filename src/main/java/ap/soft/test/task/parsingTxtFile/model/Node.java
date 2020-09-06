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
    private final Node<T> parent;
    private final List<Node<T>> children;

    private Node(T lineID, int depth, Node<T> parent, List<Node<T>> children) {
        logger.trace("Constructor \"Node\" started");
        this.lineID = lineID;
        this.depth = depth;
        this.parent = parent;
        this.children = children;
    }

    public T getLineID() {
        logger.trace("Method \"getLineID\" started");
        return lineID;
    }

    public int getDepth() {
        logger.trace("Method \"getDepth\" started");
        return depth;
    }

    public List<Node<T>> getChildren() {
        logger.trace("Method \"getChildren\" started");
        return children;
    }

    public static <T> Node<T> firstNode(T lineID){
        logger.trace("Method \"firstNode\" started");
        return new Node<T>(lineID,0, null, new LinkedList<>());
    }

    public static <T> Node<T> firstNode(T lineID, List<Node<T>> children){
        logger.trace("Method \"firstNode\" started");
        return new Node<T>(lineID,0, null, children);
    }

    public <T> Node<T> addNode(T lineID, int depth){
        logger.trace("Method \"addNode\" started");

        Node<T> parent = findParentNode( this, depth);
        Node<T> node = new Node<T>(lineID, depth, parent, new LinkedList<>());
        parent.children.add(node);

        logger.info("Created " + node + " his parent " + parent);
        return node;
    }

    private Node findParentNode(Node<T> node, int depth){
        logger.trace("Method \"findParentNode\" started");
        logger.debug("Current Node depth = " + node.depth + "  new Node depth = " + depth);

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
                " lineID='" + lineID + '\'' +
                ", children.size= " + ( children != null ? children.size() + "" : 0 ) + "" +
                ", depth=" + depth +
                '}';
    }
}
