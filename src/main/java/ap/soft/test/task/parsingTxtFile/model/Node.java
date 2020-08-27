package ap.soft.test.task.parsingTxtFile.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.LinkedList;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Node<T> {

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

    public Node addNode(T lineID, int depth){
        Node node;
        if (this.depth < depth ){
            node = new Node(lineID, depth, this);
            this.children.add(node);
        }else if (this.depth == depth){
            node = new Node(lineID, depth, this.parent);
            this.parent.children.add(node);
        }else {
            Node parent = findParentNode(this,depth);
            node = new Node(lineID, depth, parent);
            parent.children.add(node);
        }
        return node;
    }

    private Node findParentNode(Node node, int depth){
        if (node.depth < depth) {
            return node;
        }else if (node.depth == depth){
            return node.parent;
        }else
            return findParentNode(node.parent, depth);
        }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + lineID + '\'' +
                ", depth=" + depth +
                ", children.size=" + ( children != null ? children.size() + "" : 0 ) + "" +
                '}';
    }
}
