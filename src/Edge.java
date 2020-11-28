import java.util.*;

public class Edge implements InterfaceEdge {

    private InterfaceNode parent;
    private InterfaceNode child;
    private int weight;


    @Override
    public InterfaceNode getParent() {
        return this.parent;
    }

    @Override
    public InterfaceNode getChild() {
        return this.child;
    }

    @Override
    public InterfaceNode switchSides(InterfaceNode node) {
        if (node == this.parent) {
            return this.child;
        }
        if (node == this.child) {
            return this.parent;
        }
        return null;
    }

    @Override
    public int getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public void setChild(Node child) {
        this.child = child;
    }

}
