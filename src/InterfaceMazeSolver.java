import java.util.*;

public interface InterfaceMazeSolver {

    void setConnections();
    void setHeuristic();
    Stack<Node> searchPath();
}
