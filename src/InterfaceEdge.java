import java.util.*;

public interface InterfaceEdge {

    InterfaceNode getParent();

    InterfaceNode getChild();

    InterfaceNode switchSides(InterfaceNode node);

    public void setWeight(int weight);

    public void setParent(Node parent);

    public void setChild(Node child);

    int getWeight();
}
