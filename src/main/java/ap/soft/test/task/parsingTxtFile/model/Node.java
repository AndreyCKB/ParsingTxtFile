package ap.soft.test.task.parsingTxtFile.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Node implements Serializable {

    private  final String lineID;
    private  final int depth;

    @JsonIgnore
    private  final Node parent;

    private  final List<Node> children;

    private Node(String nameNode, int depth, Node parent) {
        this.lineID = nameNode;
        this.depth = depth;
        this.parent = parent;
        this.children = new LinkedList<>();
    }

    public static Node firstNode(String lineID){
        return new Node(lineID,0, null);
    }

    public Node addNode(String nameNode, int depth){
        Node node;
        if (this.depth < depth ){
            node = new Node(nameNode,depth, this);
            this.children.add(node);
        }else if (this.depth == depth){
            node = new Node(nameNode,depth, this.parent);
            this.parent.children.add(node);
        }else {
            Node parent = findParentNode(this,depth);
            node = new Node(nameNode,depth, parent);
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





//    public static void main(String[] args) {
//        Node node = firsNode();
//        node.addNode(1).addNode(2).addNode(2).addNode(3).addNode(2).addNode(1).addNode(4).addNode(2).addNode(1).addNode(2).addNode(3);
//        print(node, 0);
//
//    }
    public String print(Node node){
        StringBuilder sb = new StringBuilder();
        print(node, 0, sb);
        return sb.toString();
    }

    private void print(Node node, int countSpace, StringBuilder result){
        for(int i = 0; i < countSpace; i++){
            result.append("_____");
        }
        result.append(node.lineID).append("<br/>");
        if (!node.children.isEmpty()){
            for (Node n : node.children){
                print(n, countSpace+1,result);
            }
        }else return;

    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + lineID + '\'' +
                ", depth=" + depth +
                '}';
    }
}
