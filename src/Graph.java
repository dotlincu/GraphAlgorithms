import java.util.Arrays;

public class Graph {
    private int countNodes;
    private int countEdges;
    private int[][] adjMatrix;

    public int getCountNodes() {
        return countNodes;
    }

    public int getCountEdges() {
        return countEdges;
    }

    public int[][] getAdjMatrix() {
        return adjMatrix;
    }

    public Graph(int countNodes){
        this.countNodes = countNodes;
        this.adjMatrix = new int[countNodes][countNodes];
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < this.adjMatrix.length; i++) {
            for (int j = 0; j < this.adjMatrix[i].length; j++) {
                str += this.adjMatrix[i][j] + "\t";
            }
            str += "\n";
        }
        return str;
    }

    public void addEdge(int source, int sink, int weight){
        if(source < 0 || source > (this.countNodes - 1)
                || sink < 0 || sink > (this.countNodes -1)
                || weight <= 0){
            System.err.println("Invalid edge: " + source + " "
                    + sink + " " + weight);
            return;
        }
        this.adjMatrix[source][sink] = weight;
        this.countEdges++;
    }

    public int degree(int node){
        if(node < 0 || node > (this.countNodes - 1) )
            System.err.println("Invalid node: " + node);
        int count = 0;
        for (int i = 0; i < this.adjMatrix[node].length; i++) {
            if(this.adjMatrix[node][i] != 0)
                ++count;
        }
//        System.out.println("Degree (" + node + ") = " + count);
        return count;
    }
}
