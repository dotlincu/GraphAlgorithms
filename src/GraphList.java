import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class GraphList {
    private int countNodes;
    private int countEdges;
    private ArrayList<ArrayList<Edge>> adjList;

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
