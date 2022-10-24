import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class GraphList {
    private int countNodes;
    private int countEdges;
    private ArrayList<ArrayList<Edge>> adjList;

    private static final int INF = 999;

    public int getCountNodes() {
        return countNodes;
    }

    public void setCountNodes(int countNodes) {
        this.countNodes = countNodes;
    }

    public int getCountEdges() {
        return countEdges;
    }

    public void setCountEdges(int countEdges) {
        this.countEdges = countEdges;
    }

    public ArrayList<ArrayList<Edge>> getAdjList() {
        return adjList;
    }

    public void setAdjList(ArrayList<ArrayList<Edge>> adjList) {
        this.adjList = adjList;
    }

    public GraphList(int countNodes) {
        this.countNodes = countNodes;
        adjList = new ArrayList<>(this.countNodes);
        for (int i = 0; i < this.countNodes; ++i) {
            adjList.add(new ArrayList<Edge>());
        }
    }

    public GraphList(String fileName) throws Exception {
        File file = new File(fileName);
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);

        String[] line = bufferedReader.readLine().split(" ");
        this.countNodes = (Integer.parseInt(line[0]));
        int fileLines = (Integer.parseInt(line[1]));

        adjList = new ArrayList<>(this.countNodes);
        for (int i = 0; i < this.countNodes; ++i) {
            adjList.add(new ArrayList<Edge>());
        }
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

    public void addEdge(int source, int sink, int weight){
        if(source < 0 || source > (this.countNodes - 1)
                || sink < 0 || sink > (this.countNodes -1)
                || weight <= 0){
            System.err.println("Invalid edge: " + source + " "
                    + sink + " " + weight);
            return;
        }
        this.adjList.get(source).add(new Edge(source, sink, weight));
        this.countEdges++;
    }

    public void addUnorientedEdge(int source, int sink, int weight){
        addEdge(source, sink, weight);
        addEdge(sink, source, weight);
    }

    public int degree(int node) {
        if(node < 0 || node > (this.countNodes - 1) )
            System.err.println("Invalid node: " + node);
        return this.adjList.get(node).size();
    }

    public int highestDegree(){
        int highest = 0;
        for (int i = 0; i < this.adjList.size(); i++) {
            int degreeNodeI = this.degree(i);
            if(this.adjList.get(i).size() > highest)
                highest = degreeNodeI;
        }
        return highest;
    }

    public int lowestDegree(){
        int lowest = this.adjList.size();
        for (int i = 1; i < this.adjList.size(); i++) {
            int degreeNodeI = this.degree(i);
            if(degreeNodeI < lowest)
                lowest = degreeNodeI;
        }
        return lowest;
    }

    public float density() {
        return (float)(this.countEdges / (this.countNodes * (this.countNodes - 1)));
    }

    public GraphList complement(){
        var g2 = new GraphList(this.countNodes);
        for (int u = 0; u < this.adjList.size(); ++u) {
            for (int v = 0; v < this.highestDegree(); ++v) {
                boolean addEdgeUV = true;
                for (int idx = 0; idx < this.adjList.get(u).size(); ++idx) {
                    int v2 = this.adjList.get(u).get(idx).getSink();
                    if(v2 == v) {
                        addEdgeUV = false;
                        break;
                    }
                }
                if(addEdgeUV && u != v)
                    g2.addEdge(u, v, 1);
            }
        }
        return g2;
    }

    public boolean isOriented() {
        for (int u = 0; u < this.adjList.size(); ++u) {
            for (int idx = 0; idx < this.adjList.get(u).size(); ++idx) {
                int v = this.adjList.get(u).get(idx).getSink();
                boolean hasEdgeVU = false;
                for (int idx2 = 0; idx2 < this.adjList.get(v).size(); ++idx2) {
                    int u2 = this.adjList.get(v).get(idx2).getSink();
                    if(u == u2) {
                        hasEdgeVU = true;
                        break;
                    }
                }
                if(!hasEdgeVU)
                    return true;
            }
        }
        return false;
    }

    public boolean subgraph(GraphList g2) {
        if(g2.countNodes > this.countNodes || g2.countEdges > this.countEdges)
            return false;
        for (int u = 0; u < g2.adjList.size(); ++u) {
            boolean foundEdge = false;
            for (int idx = 0; idx < g2.adjList.get(u).size(); ++idx) {
                int v = g2.adjList.get(u).get(idx).getSink();
                for (int idx2 = 0; idx2 < this.adjList.get(u).size(); ++idx2) {
                    int v2 = this.adjList.get(u).get(idx2).getSink();
                    if(v == v2) {
                        foundEdge = true;
                        break;
                    }
                }
                if(!foundEdge)
                    return false;
            }
        }
        return true;
    }

    public ArrayList<Integer> bfs(int s) {
        int[] desc = new int[this.countNodes];
        ArrayList<Integer> Q = new ArrayList<>();
        ArrayList<Integer> R = new ArrayList<>();
        Q.add(s);
        R.add(s);
        desc[s] = 1;

        while (Q.size() > 0) {
            int u = Q.remove(0);
            for (int v = 0; v < this.adjList.get(u).size(); v++) {
                if (desc[this.adjList.get(u).get(v).getSink()] == 0) {
                    Q.add(this.adjList.get(u).get(v).getSink());
                    R.add(this.adjList.get(u).get(v).getSink());
                    desc[this.adjList.get(u).get(v).getSink()] = 1;
                }
            }
        }
        return R;
    }

    public ArrayList<Integer> dfs(int s) {
        int[] desc = new int[this.countNodes];
        ArrayList<Integer> S = new ArrayList<>();
        ArrayList<Integer> R = new ArrayList<>();
        S.add(s);
        R.add(s);
        desc[s] = 1;

        while (S.size() > 0) {
            boolean unstack = true;
            int u = S.get(S.size() - 1);
            for (int v = 0; v < this.adjList.get(u).size(); v++) {
                if (desc[this.adjList.get(u).get(v).getSink()] == 0) {
                    S.add(this.adjList.get(u).get(v).getSink());
                    R.add(this.adjList.get(u).get(v).getSink());
                    desc[this.adjList.get(u).get(v).getSink()] = 1;
                    unstack = false;
                    break;
                }
            }
            if (unstack)
                S.remove(S.size() - 1);
        }
        return R;
    }

    public ArrayList<Integer> dfsRec(int s) {
        int[] desc = new int[this.countNodes];
        ArrayList<Integer> R = new ArrayList<>();
        dfsRecAux(s, desc, R);
        return R;
    }

    public void dfsRecAux(int u, int[] desc, ArrayList<Integer> R) {
        desc[u] = 1;
        R.add(u);
        for (int idx = 0; idx < this.adjList.get(u).size(); ++idx) {
            int v = this.adjList.get(u).get(idx).getSink();
            if (desc[v] == 0) {
                dfsRecAux(v, desc, R);
            }
        }
    }

    public boolean isConnected() {
        return (this.bfs(0).size() == this.countNodes);
    }

//    public ArrayList<Edge> kruskal() {
//        ArrayList<Edge> T = new ArrayList<Edge>(this.countNodes - 1);
//        int[] F = new int[this.countNodes];
//        // makeset(u)
//        for (int u = 0; u < this.countNodes; ++u)
//            F[u] = u;
//        edgeList.sort(null);
//        for (int idx = 0; idx < edgeList.size(); ++idx) {
//            int u = edgeList.get(idx).getSource();
//            int v = edgeList.get(idx).getSink();
//            if (F[u] != F[v]) { // findset(u) != findset(v)
//                T.add(edgeList.get(idx));
//                // Save some iterations if tree is already built
//                if (T.size() == countNodes - 1)
//                    break;
//                // union(u, v)
//                int k = F[v];
//                for (int i = 0; i < F.length; ++i) {
//                    if (F[i] == k) {
//                        F[i] = F[u];
//                    }
//                }
//            }
//        }
//        return T;
//    }

    public ArrayList<Edge> prim() {
        ArrayList<Edge> T = new ArrayList<Edge>(this.countNodes - 1);
        int s = 0;
        int[] dist = new int[this.countNodes];
        int[] parent = new int[this.countNodes];
        // Q represents the nodes that were not connected yet
        ArrayList<Integer> Q = new ArrayList<Integer>(this.countNodes);
        for (int u = 0; u < this.countNodes; ++u) {
            dist[u] = INF;
            parent[u] = -1;
            Q.add(u);
        }
        dist[s] = 0;
        while (Q.size() != 0) {
            int u = -1;
            int min = INF;
            for (int idx = 0; idx < Q.size(); ++idx) {
                int i = Q.get(idx);
                if (dist[i] < min) {
                    min = dist[i];
                    u = i;
                }
            }
            // Node u is gonna be connected
            Q.remove((Integer) u);
            for (int idx = 0; idx < this.adjList.get(u).size(); ++idx) {
                int v = this.adjList.get(u).get(idx).getSink();
                int w = this.adjList.get(u).get(idx).getWeight();
                if (Q.contains(v) && w < dist[v]) {
                    dist[v] = w;
                    parent[v] = u;
                }
            }
        }
        // Recover the tree from parent array
        for (int u = 0; u < parent.length; ++u) {
            if (parent[u] != -1) {
                T.add(new Edge(u, parent[u], 1));
            }
        }
        return T;
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < this.adjList.size(); ++i) {
            str += i + ": ";
            for (int j = 0; j < this.adjList.get(i).size(); ++j) {
                str += this.adjList.get(i).get(j) + "\t";
            }
            str += "\n";
        }
        return str;
    }
}
