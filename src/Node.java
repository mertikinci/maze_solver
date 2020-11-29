import java.util.*;


public class Node implements InterfaceNode {

    private boolean open = true;
    private boolean visited = false;
    private boolean selected = false;
    private boolean obstacle = false;

    private Node parent;
    private final Collection<InterfaceNode> children = new ArrayList<InterfaceNode>();
    private InterfaceNode predecessor;
    private int cost = 0;
    private int heuristic = 0;


    public void reset() {
        predecessor = null;
        cost = 0;
        open = true;
        visited = false;
        selected = false;
        obstacle = false;
    }


    @Override
    public boolean isOpen() {
        return open;
    }


    @Override
    public void setOpen(boolean open) {
        this.open = open;
    }


    @Override
    public boolean isObstacle() {
        return obstacle;
    }


    @Override
    public void setObstacle(boolean obstacle) {
        this.obstacle = obstacle;
    }

    @Override
    public boolean isVisited() {
        return visited;
    }


    @Override
    public void setVisited(boolean visited) {
        this.visited = visited;
    }


    @Override
    public boolean isSelected() {
        return selected;
    }


    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean addChild(InterfaceNode child) {

        return this.children.add(child);
    }

    public boolean removeChild(InterfaceEdge child) {

        return this.children.remove(child);
    }

    @Override
    public Collection<InterfaceNode> getChildren() {
        return this.children;
    }

    public Node getParent(){
        return parent;
    }

    public void setParent(Node parent){
        this.parent = parent;
    }



    @Override
    public InterfaceNode getPredecessor() {
        return predecessor;
    }


    @Override
    public void setPredecessor(InterfaceNode node) {
        this.predecessor = node;
    }


    @Override
    public int getCost() {
        return this.cost;
    }


    @Override
    public void setCost(int cost) {
        this.cost = cost;
    }


    @Override
    public int getHeuristic() {
        return heuristic;
    }


    @Override
    public void setHeuristic(int heuristic) {
        this.heuristic = heuristic;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(heuristic + cost);
        sb.append("(");
        sb.append(cost);
        sb.append(",");
        sb.append(heuristic);
        sb.append(")");;
        return sb.toString();
    }

}
