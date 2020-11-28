import java.util.*;

public class MazeSolver implements InterfaceMazeSolver{


    private Node[][] matrix;
    private Collection<InterfaceEdge> edges = new ArrayList<InterfaceEdge>();
    private int startX;
    private int startY;
    private int endX;
    private int endY;



    public MazeSolver(boolean[][] arr,int startX,int startY,int endX,int endY){
        //System.out.println("constructer maze solver girdi.");
        matrix = new Node[arr.length][arr[0].length];
        this.startX=startX;
        this.startY = startY;
        this.endX = endX;
        this.endY=endY;

        for(int i = 0; i<matrix.length;i++){ // init nodes
            for(int j=0;j<matrix[0].length;j++){
                matrix[i][j] = new Node();
            }
        }

        for(int i = 0; i<matrix.length;i++){ // obstacle ya da değil init
            for(int j=0;j<matrix[0].length;j++){
                matrix[i][j].setObstacle(arr[i][j]);
            }
        }

        setConnections();
    }

    public void setConnections(){
        Edge e;
        for(int i = 0; i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){

                if(!matrix[i][j].isObstacle() && i==0 && j==0){  //sol üst
                    if(!matrix[i][j+1].isObstacle()){ // sol üstün sağı için
                        e = new Edge();
                        e.setParent(matrix[i][j]);
                        e.setChild(matrix[i][j+1]);
                        edges.add(e);
                    }
                    if(!matrix[i+1][j].isObstacle()){ //sol üstün altı
                        e = new Edge();
                        e.setParent(matrix[i][j]);
                        e.setChild(matrix[i+1][j]);
                        edges.add(e);
                    }
                }
                else if (!matrix[i][j].isObstacle() && i==0&& j==(matrix[0].length -1) ){ // sağ üst

                    if(!matrix[i+1][j].isObstacle()){ // sağ üstün altı
                        e = new Edge();
                        e.setParent(matrix[i][j]);
                        e.setChild(matrix[i+1][j]);
                        edges.add(e);
                    }
                }
                else if (!matrix[i][j].isObstacle() && i==(matrix.length -1)&& j==0 ){ // sol alt

                    if(!matrix[i][j+1].isObstacle()){ // sol altın sağı
                        e = new Edge();
                        e.setParent(matrix[i][j]);
                        e.setChild(matrix[i][j+1]);
                        edges.add(e);
                    }
                }
                else if (!matrix[i][j].isObstacle() && i==(matrix.length -1)&& j==(matrix[0].length -1) ){

                    // sağ alt için boş geçmesi gerekir.
                }

                else if (!matrix[i][j].isObstacle() && i==0){ // üst satır
                    if(!matrix[i+1][j].isObstacle()){ // üst satırın altı
                        e = new Edge();
                        e.setParent(matrix[i][j]);
                        e.setChild(matrix[i+1][j]);
                        edges.add(e);
                    }

                    if(!matrix[i][j+1].isObstacle()){ // üst satırın sağı
                        e = new Edge();
                        e.setParent(matrix[i][j]);
                        e.setChild(matrix[i][j+1]);
                        edges.add(e);
                    }
                }
                else if (!matrix[i][j].isObstacle() && i==(matrix.length -1)){ // alt satır


                    if(!matrix[i][j+1].isObstacle()){ // alt satırın sağı
                        e = new Edge();
                        e.setParent(matrix[i][j]);
                        e.setChild(matrix[i][j+1]);
                        edges.add(e);
                    }
                }
                else if (!matrix[i][j].isObstacle() && j==0){ // sol sütun

                    if(!matrix[i+1][j].isObstacle()){ // sol sütun altı
                        e = new Edge();
                        e.setParent(matrix[i][j]);
                        e.setChild(matrix[i+1][j]);
                        edges.add(e);
                    }
                    if(!matrix[i][j+1].isObstacle()){ // sol sütun sağı
                        e = new Edge();
                        e.setParent(matrix[i][j]);
                        e.setChild(matrix[i][j+1]);
                        edges.add(e);
                    }
                }
                else if (!matrix[i][j].isObstacle() && j==(matrix[0].length -1)){ // sağ sütun

                    if(!matrix[i+1][j].isObstacle()){ // sağ sütun altı
                        e = new Edge();
                        e.setParent(matrix[i][j]);
                        e.setChild(matrix[i+1][j]);
                        edges.add(e);
                    }

                }
                else { // orta kare

                    if(!matrix[i-1][j].isObstacle()){ // orta kare altı
                        e = new Edge();
                        e.setParent(matrix[i][j]);
                        e.setChild(matrix[i+1][j]);
                        edges.add(e);
                    }

                    if(!matrix[i][j+1].isObstacle()){ // orta kare sağı
                        e = new Edge();
                        e.setParent(matrix[i][j]);
                        e.setChild(matrix[i][j+1]);
                        edges.add(e);
                    }
                }
            }
        }
    }
    public void setHeuristic(){
        for(int i = 0; i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++) {
                matrix[i][j].setHeuristic(Math.abs(i-endX) + Math.abs(j-endY));
            }
        }

    }

    public static void main(String args[]){  // tryout dummy main
        boolean arr[][] = new boolean[3][3];
        for(int i = 0; i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++) {
                arr[i][j]=false;
            }
        }

        MazeSolver m = new MazeSolver(arr,0,0,2,2);
        System.out.println(m.edges);


    }


}
