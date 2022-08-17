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
        return count;
    }

    public int highestDegree(){
        int highest = 0;
        for (int i = 0; i < this.adjMatrix.length; i++) {
            int degreeNodeI = this.degree(i);
            if(degreeNodeI > highest)
                highest = degreeNodeI;
        }
        return highest;
    }

    public int lowestDegree(){
        int lowest = this.adjMatrix.length;
        for (int i = 1; i < this.adjMatrix.length; i++) {
            int degreeNodeI = this.degree(i);
            if(degreeNodeI < lowest)
                lowest = degreeNodeI;
        }
        return lowest;
    }

    public Graph complement(){
        var g2 = new Graph(this.countNodes);

        for (int i = 0; i < this.adjMatrix.length; i++) {
            for (int j = 0; j < this.adjMatrix[i].length; j++) {
                if(this.adjMatrix[i][j] == 0 && i != j)
                    g2.addEdge(i, j, 1);
            }
        }
        return g2;
    }

    public float density(){
        // d = |E|/(|V|*|V|-1)
        // |E| = countEdges   |V| = countNodes
        float result = (this.countEdges) / ((this.countNodes * this.countNodes) - 1);
        return result;
    }

    public boolean subGraph(Graph g2){
        // retorna true se g2 é subgrafo de thiss / false caso contrario
        if(this.countNodes < g2.countNodes)
            return false;
        else {
            for (int i = 0; i < g2.adjMatrix.length; i++) {
                for (int j = 0; j < g2.adjMatrix[i].length; j++) {
                    if(g2.adjMatrix[i][j] == 1 && this.adjMatrix[i][j] !=  1)
                        return false;
                }
            }
        }
        return true;
    }
}
