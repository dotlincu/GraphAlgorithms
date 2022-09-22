import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Graph {
    private int countNodes;
    private int countEdges;
    private ArrayList<ArrayList<Edge>> adjList;

    public Graph(int countNodes) {
        this.countNodes = countNodes;
        adjList = new ArrayList<>(this.countNodes);
        for (int i = 0; i < this.countNodes; ++i) {
            adjList.add(new ArrayList<Edge>());
        }
    }

    public Graph(String fileName) throws Exception {
        File file = new File(fileName);
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);

        // Read header
        String[] line = bufferedReader.readLine().split(" ");
        this.countNodes = (Integer.parseInt(line[0]));
        int fileLines = (Integer.parseInt(line[1]));

        // Create and fill adjList with read edges
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

    public void addEdgeUnoriented(int source, int sink, int weight){
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

    public Graph complement(){
        var g2 = new Graph(this.countNodes);
        for (int i = 0; i < this.adjList.size(); i++) {
            for (int j = 0; j < this.highestDegree(); j++) {
                if(this.adjList.get(i).get(j).getWeight() == 0
                        && this.adjList.get(i).get(j).getSource() != this.adjList.get(i).get(j).getSink())
                    g2.addEdge(this.adjList.get(i).get(j).getSource(), this.adjList.get(i).get(j).getSink(), 5);
            }
        }
        return g2;
    }

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
