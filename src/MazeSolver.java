import java.util.*;

public class MazeSolver implements InterfaceMazeSolver{


    private Node[][] matrix;
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


        for(int i = 0; i<matrix.length;i++){ // obstacle ya da değil init
            for(int j=0;j<matrix[0].length;j++){
                matrix[i][j] = new Node();
                matrix[i][j].setX(i);
                matrix[i][j].setY(j);
                matrix[i][j].setObstacle(arr[i][j]);
            }
        }

        setHeuristic();
        setConnections();



        System.out.println(matrix[3][1].getChildren().size());
        System.out.println(matrix[3][3].getHeuristic());
        System.out.println(matrix[3][2].getHeuristic());
        System.out.println(matrix[0][0].getHeuristic());

        ArrayList<Node> last = searchPath();
        for (Node nodes : last){
            System.out.print(nodes.getX()+" "+nodes.getY()+" --> ");

        }





    }

    public ArrayList<Node> searchPath(){

        HashMap<Node,Integer> visited = new HashMap<Node, Integer>();
        ArrayList<Node> potential = new ArrayList<Node>();
        ArrayList<Node> path = new ArrayList<Node>();


        boolean cont = true;
        Node temp = matrix[startX][startY];
        int minHeuristic = 99999;

        while (cont){

            visited.put(temp,temp.getHeuristic());
            temp.getChildren().forEach(child -> {
                if(!visited.containsKey(child)){
                    potential.add(child);
                }
            });
            for (Node child : potential){
                if(child.getHeuristic()<minHeuristic){
                    minHeuristic = child.getHeuristic();
                }
            }
            for (Node child : potential){
                if(child.getHeuristic()==minHeuristic){
                    temp=child;
                    potential.remove(child);
                }

            }
            if(temp.getX()==endX && temp.getY()==endY){
                path.add(temp);
                while(!temp.getParent().equals(matrix[startX][startY])){
                    path.add(temp.getParent());
                    temp=temp.getParent();
                }
                path.add(temp.getParent());
                cont = false;
            }
        }
        return path;
    }

    public void setConnections(){

        for(int i = 0; i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){


                if(matrix[i][j].isObstacle()){
                       continue;
                }
                else if(!matrix[i][j].isObstacle() && i==0 && j==0){  //sol üst
                    if(!matrix[i][j+1].isObstacle()){ // sol üstün sağı için
                        matrix[i][j].addChild(matrix[i][j+1]);
                        matrix[i][j+1].setParent(matrix[i][j]);
                    }
                    if(!matrix[i+1][j].isObstacle()){ //sol üstün altı
                        matrix[i][j].addChild(matrix[i+1][j]);
                        matrix[i+1][j].setParent(matrix[i][j]);
                    }
                }
                else if (!matrix[i][j].isObstacle() && i==0&& j==(matrix[0].length -1) ){ // sağ üst

                    if(!matrix[i+1][j].isObstacle()){ // sağ üstün altı
                        matrix[i][j].addChild(matrix[i+1][j]);
                        matrix[i+1][j].setParent(matrix[i][j]);
                    }
                    if(!matrix[i][j-1].isObstacle()){ //sol üstün solu
                        matrix[i][j].addChild(matrix[i][j-1]);
                        matrix[i][j-1].setParent(matrix[i][j]);
                    }
                }
                else if (!matrix[i][j].isObstacle() && i==(matrix.length -1)&& j==0 ){ // sol alt

                    if(!matrix[i][j+1].isObstacle()){ // sol altın sağı
                        matrix[i][j].addChild(matrix[i][j+1]);
                        matrix[i][j+1].setParent(matrix[i][j]);
                    }
                    if(!matrix[i-1][j].isObstacle()){ //sol altın üstü
                        matrix[i][j].addChild(matrix[i-1][j]);
                        matrix[i-1][j].setParent(matrix[i][j]);
                    }
                }
                else if (!matrix[i][j].isObstacle() && i==(matrix.length -1)&& j==(matrix[0].length -1) ){ // sağ alt

                    if(!matrix[i-1][j].isObstacle()){ //sağ altın üstü
                        matrix[i][j].addChild(matrix[i-1][j]);
                        matrix[i-1][j].setParent(matrix[i][j]);
                    }
                    if(!matrix[i][j-1].isObstacle()){ //sağ altın solu
                        matrix[i][j].addChild(matrix[i][j-1]);
                        matrix[i][j-1].setParent(matrix[i][j]);
                    }
                }

                else if (!matrix[i][j].isObstacle() && i==0){ // üst satır
                    if(!matrix[i+1][j].isObstacle()){ // üst satırın altı
                        matrix[i][j].addChild(matrix[i+1][j]);
                        matrix[i+1][j].setParent(matrix[i][j]);
                    }

                    if(!matrix[i][j+1].isObstacle()){ // üst satırın sağı
                        matrix[i][j].addChild(matrix[i][j+1]);
                        matrix[i][j+1].setParent(matrix[i][j]);
                    }
                    if(!matrix[i][j-1].isObstacle()){ //üst satırın solu
                        matrix[i][j].addChild(matrix[i][j-1]);
                        matrix[i][j-1].setParent(matrix[i][j]);
                    }
                }
                else if (!matrix[i][j].isObstacle() && i==(matrix.length -1)){ // alt satır


                    if(!matrix[i][j+1].isObstacle()){ // alt satırın sağı
                        matrix[i][j].addChild(matrix[i][j+1]);
                        matrix[i][j+1].setParent(matrix[i][j]);
                    }
                    if(!matrix[i-1][j].isObstacle()){ //alt satırın üstü
                        matrix[i][j].addChild(matrix[i-1][j]);
                        matrix[i-1][j].setParent(matrix[i][j]);
                    }
                    if(!matrix[i][j-1].isObstacle()){ //alt satırın solu
                        matrix[i][j].addChild(matrix[i][j-1]);
                        matrix[i][j-1].setParent(matrix[i][j]);
                    }
                }
                else if (!matrix[i][j].isObstacle() && j==0){ // sol sütun

                    if(!matrix[i+1][j].isObstacle()){ // sol sütun altı
                        matrix[i][j].addChild(matrix[i+1][j]);
                        matrix[i+1][j].setParent(matrix[i][j]);
                    }
                    if(!matrix[i][j+1].isObstacle()){ // sol sütun sağı
                        matrix[i][j].addChild(matrix[i][j+1]);
                        matrix[i][j+1].setParent(matrix[i][j]);
                    }
                    if(!matrix[i-1][j].isObstacle()){ //sol sütun üst
                        matrix[i][j].addChild(matrix[i-1][j]);
                        matrix[i-1][j].setParent(matrix[i][j]);
                    }
                }
                else if (!matrix[i][j].isObstacle() && j==(matrix[0].length -1)){ // sağ sütun

                    if(!matrix[i+1][j].isObstacle()){ // sağ sütun altı
                        matrix[i][j].addChild(matrix[i+1][j]);
                        matrix[i+1][j].setParent(matrix[i][j]);
                    }
                    if(!matrix[i-1][j].isObstacle()){ //sağ sütun üstü
                        matrix[i][j].addChild(matrix[i-1][j]);
                        matrix[i-1][j].setParent(matrix[i][j]);
                    }
                    if(!matrix[i][j-1].isObstacle()){ //sağ sütun solu
                        matrix[i][j].addChild(matrix[i][j-1]);
                        matrix[i][j-1].setParent(matrix[i][j]);
                    }

                }
                else { // orta kare
                    //System.out.println(""+i+" "+j);

                    if(!matrix[i+1][j].isObstacle()){ // orta kare altı
                        matrix[i][j].addChild(matrix[i+1][j]);
                        matrix[i+1][j].setParent(matrix[i][j]);
                    }

                    if(!matrix[i][j+1].isObstacle()){ // orta kare sağı
                        matrix[i][j].addChild(matrix[i][j+1]);
                        matrix[i][j+1].setParent(matrix[i][j]);
                    }
                    if(!matrix[i-1][j].isObstacle()){ //orta kare üstü
                        matrix[i][j].addChild(matrix[i-1][j]);
                        matrix[i-1][j].setParent(matrix[i][j]);
                    }
                    if(!matrix[i][j-1].isObstacle()){ //orta kare solu
                        matrix[i][j].addChild(matrix[i][j-1]);
                        matrix[i][j-1].setParent(matrix[i][j]);
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
    public void setRoadCost(){
        for(int i = 0; i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++) {
                matrix[i][j].setCost(Math.abs(i-startX) + Math.abs(j-startY));
            }
        }

    }

    public static void main(String args[]){  // tryout dummy main
        boolean arr[][] = new boolean[4][4];
        for(int i = 0; i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++) {
                arr[i][j]=false;
            }
        }

        arr[2][1]=true;
        arr[2][2]=true;
        arr[0][3]=true;
        arr[0][1]=true;
        arr[1][1]=true;



        MazeSolver m = new MazeSolver(arr,0,0,0,2);



    }


}
