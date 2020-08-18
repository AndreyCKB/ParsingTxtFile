package ap.soft.test.task.parsingTxtFile.model;

import java.util.LinkedList;
import java.util.List;


public class Node {
     private  int depth;
     private  Node parent;
     private  List<Node> children = new LinkedList<>();

    private Node(int depth, Node parent) {
        this.depth = depth;
        this.parent = parent;
    }

    public Node addNode(int depth){
        Node node;
        if (this.depth < depth ){
            node = new Node(depth, this);
            this.children.add(node);
        }else if (this.depth == depth){
            node = new Node(depth, this.parent);
            this.parent.children.add(node);
        }else {
            node = findNode(this,depth);
        }
        return node;
    }

    private Node findNode(Node node,int depth){
        if (node.depth == depth) {
            Node node1 = new Node(depth, this.parent);
            node.parent.children.add(node1);
            return node;
        } else {
           return findNode(node.parent, depth);
        }

    }

    public static Node firsNode(){
        return new Node(0, null);
    }

    public static void main(String[] args) {
        Node node = firsNode();
        node.addNode(1).addNode(2).addNode(2).addNode(3).addNode(2).addNode(1);
        print(node);

    }

    private static void print(Node node){
        if (!node.children.isEmpty()){
            System.out.println(node);
        } else return;
    }

    @Override
    public String toString() {
        return "Node{" +
                "depth=" + depth +
                '}';
    }
}
