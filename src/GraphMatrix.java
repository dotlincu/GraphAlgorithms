import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class GraphMatrix {
    private int countNodes;
    private int countEdges;
    private int[][] adjMatrix;

    private static int INF = 999;

    public int getCountNodes() {
        return countNodes;
    }

    public int getCountEdges() {
        return countEdges;
    }

    public int[][] getAdjMatrix() {
        return adjMatrix;
    }

    public GraphMatrix(int countNodes){
        this.countNodes = countNodes;
        this.adjMatrix = new int[countNodes][countNodes];
    }

    public GraphMatrix(String fileName) throws IOException {
        File file = new File(fileName);
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);

        // Read header
        String[] line = bufferedReader.readLine().split(" ");
        this.countNodes = (Integer.parseInt(line[0]));
        int fileLines = (Integer.parseInt(line[1]));

        // Create and fill adjMatrix with read edges
        this.adjMatrix = new int[this.countNodes][this.countNodes];
        for (int i = 0; i < fileLines; ++i) {
            String[] edgeInfo = bufferedReader.readLine().split(" ");
            int source = Integer.parseInt(edgeInfo[0]);
            int sink = Integer.parseInt(edgeInfo[1]);
            int weight = Integer.parseInt(edgeInfo[2]);
            addEdge(source, sink, weight);
        }
        bufferedReader.close();
        reader.close();
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

    public void addEdgeUnoriented(int u, int v, int w){
        if(u < 0 || u > (this.countNodes - 1)
                || v < 0 || v > (this.countNodes -1)
                || w <= 0){
            System.err.println("Invalid edge: " + u + " "
                    + v + " " + w);
            return;
        }
        this.adjMatrix[u][v] = w;
        this.adjMatrix[v][u] = w;
        this.countEdges += 2;
    }

    public int degree(int node){
        // TODO usar lista de adjacencias invez de matriz
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

    public GraphMatrix complement(){
        var g2 = new GraphMatrix(this.countNodes);

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

    public boolean subGraph(GraphMatrix g2){
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

    public ArrayList<Integer> bfs(int s){   // breadth-first search
        int[] desc = new int[this.countNodes];
        ArrayList<Integer> Q = new ArrayList<>();
        ArrayList<Integer> R = new ArrayList<>();
        Q.add(s);
        R.add(s);
        desc[s] = 1;

        while (Q.size() > 0){
            int u = Q.remove(0);
            for (int v = 0; v < this.adjMatrix[u].length; v++) {
                if(this.adjMatrix[u][v] != 0 && desc[v] == 0){
                    Q.add(v);
                    R.add(v);
                    desc[v] = 1;
                }
            }
        }
        return R;
    }

    public ArrayList<Integer> dfs(int s){   //depth-first search
        int[] desc = new int[this.countNodes];
        Stack<Integer> S = new Stack<>();   //pilha
        ArrayList<Integer> R = new ArrayList<>();
        S.addElement(s);
        R.add(s);
        desc[s] = 1;

        while(S.size() > 0){
            int u = S.lastElement();
            boolean unstack = true;          //desempilhar
            for (int v = 0; v < this.adjMatrix[u].length; v++) {
                if (this.adjMatrix[u][v] != 0 && desc[v] == 0) {
                    S.addElement(v);
                    R.add(v);
                    desc[v] = 1;
                    unstack = false;
                    break;
                }
            }
            if(unstack)
                S.removeElement(u);
        }
        return R;
    }

    public ArrayList<Integer> dfs_rec(int s){
        int[] desc = new int[this.countNodes];
        ArrayList<Integer> R = new ArrayList<>();
        dfs_rec_aux(s, desc, R);
        return R;
    }

    private void dfs_rec_aux(int u, int[] desc, ArrayList<Integer> R){
        desc[u] = 1;
        R.add(u);
        for (int v = 0; v < this.adjMatrix[u].length; v++) {
            if (this.adjMatrix[u][v] != 0 && desc[v] == 0) {
                dfs_rec_aux(v, desc, R);
            }
        }
    }

    public boolean nonOriented(){
        for (int i = 0; i < this.adjMatrix.length; i++)
            for (int j = i + 1; j < this.adjMatrix[i].length; j++)
                if(this.adjMatrix[i][j] != this.adjMatrix[j][i])
                    return false;
        return true;
    }

    public boolean connected(){
        return this.bfs(0).size() == this.countNodes;
    }

    public ArrayList<Integer> topSort() {
        int[] desc = new int[this.countNodes];
        ArrayList<Integer> R = new ArrayList<>();
        for (int v = 0; v < this.adjMatrix.length; ++v) {
            if (desc[v] == 0)
                topSortAux(v, desc, R);
        }
        return R;
    }

    public void topSortAux(int u, int[] desc, ArrayList<Integer> R) {
        desc[u] = 1;
        for (int v = 0; v < this.adjMatrix[u].length; ++v) {
            if (this.adjMatrix[u][v] != 0 && desc[v] == 0)
                topSortAux(v, desc, R);
        }
        R.add(0, u);
    }

    public void floyd_warshall(int s, int t) {
        int[][] dist = new int[this.countNodes][this.countNodes];
        int[][] pred = new int[this.countNodes][this.countNodes];

        for (int i = 0; i < this.adjMatrix.length; ++i) {
            for (int j = 0; j < this.adjMatrix[i].length; ++j) {
                if (i == j) {
                    dist[i][j] = 0;
                    pred[i][j] = -1;
                } else if (this.adjMatrix[i][j] != 0){
                    dist[i][j] = this.adjMatrix[i][j];
                    pred[i][j] = i;
                } else {
                    dist[i][j] = INF;
                    pred[i][j] = -1;
                }
            }
        }

        for (int k = 0; k < this.countNodes - 1; ++k) {
            for (int i = 0; i < this.countNodes - 1; ++i) {
                for (int j = 0; j < this.countNodes - 1; ++j) {
                    if (dist[i][j] > (dist[i][k] + dist[k][j])){
                        dist[i][j] = (dist[i][k] + dist[k][j]);
                        pred[i][j] = pred[k][j];
                    }
                }
            }
        }

        System.out.printf("Distancia de %d a %d é: %d", s, t, pred[s][t]);
        ArrayList<Integer> C = new ArrayList<Integer>();
        C.add(t);
        int aux = t;
        while(aux != s){
//            aux = pred[aux][];
            C.add(0,aux);
        }
        System.out.println(C);
        System.out.println("\n-- DIST");
        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist[i].length; j++) {
                System.out.print(dist[i][j] + "\t\t");
            }
            System.out.println();
        }

        System.out.println("\n -- PRED");
        for (int i = 0; i < pred.length; i++) {
            for (int j = 0; j < pred[i].length; j++) {
                System.out.print(pred[i][j] + "\t\t");
            }
            System.out.println();
        }
    }
}