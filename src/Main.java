import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        var g1 = new Graph(3);
        g1.addEdge(0,1,10);
        g1.addEdge(0,2,20);
        g1.addEdge(2,0,15);
//        g1.addEdge(0,2,6);
//        g1.addEdge(3,2,2);
        System.out.println(g1);

        System.out.println(g1.complement());

//        g1.floyd_warshall(0, 3);
//
//        System.out.println("Degree (0) = " + g1.degree(0));
//        System.out.println("Degree (1) = " + g1.degree(1));
//        System.out.println("Degree (2) = " + g1.degree(2));
//        System.out.println("Degree (3) = " + g1.degree(3));
//        System.out.println();
//        System.out.println("Highest Degree: " + g1.highestDegree());
//        System.out.println("Lowest Degree: " + g1.lowestDegree());
//        System.out.println();
//        System.out.println(g1.complement());
//        System.out.println(g1.density());
//        System.out.println(g1.subGraph(g1.complement()));

//        var g1 = new Graph(9);
//        g1.addEdgeUnoriented(7, 5, 1);
//        g1.addEdgeUnoriented(7, 1, 1);
//        g1.addEdgeUnoriented(7, 2, 1);
//        g1.addEdgeUnoriented(1, 0, 1);
//        g1.addEdgeUnoriented(1, 4, 1);
//        g1.addEdgeUnoriented(2, 3, 1);
//        g1.addEdgeUnoriented(5, 6, 1);
//        g1.addEdgeUnoriented(6, 8, 1);
//        System.out.println(g1.bfs(7));
//        System.out.println(g1.connected());

//        System.out.println("\n");
//        var g2 = new Graph("GraphAlgorithms/graph1.txt");
//        System.out.println(g2);
//        var g3 = new Graph(8);
//        g3.addEdgeUnoriented(5,0,1);
//        g3.addEdgeUnoriented(0,1,1);
//        g3.addEdgeUnoriented(0,4,1);
//        g3.addEdgeUnoriented(5,3,1);
//        g3.addEdgeUnoriented(3,2,1);
//        g3.addEdgeUnoriented(3,6,1);
//        g3.addEdgeUnoriented(1,7,1);
//
//        System.out.println(g3);
//
//        System.out.println(g3.dfs_rec(5));
//        System.out.println(g1.nonOriented());
    }
}
