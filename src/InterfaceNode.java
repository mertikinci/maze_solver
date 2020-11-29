
import java.util.*;



public interface InterfaceNode {

    Collection<InterfaceNode> getChildren();

    InterfaceNode getPredecessor();

    void setPredecessor(InterfaceNode node);

    public boolean isOpen();

    public void setOpen(boolean open);

    public boolean isObstacle();

    public void setObstacle(boolean obstacle);

    public boolean isVisited();

    public void setVisited(boolean visited);

    public boolean isSelected();

    public Node getParent();

    public void  setParent(Node parent);

    public void setSelected(boolean selected);

    int getHeuristic();

    void setHeuristic(int heuristic);

    int getCost();

    void setCost(int cost);

}