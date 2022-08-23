public class Main {
    public static void main(String[] args) {
//        var g1 = new Graph(4);
//        g1.addEdge(0,1,3);
//        g1.addEdge(1,0,3);
//        g1.addEdge(0,3,4);
//        g1.addEdge(3,0,4);
//        g1.addEdge(3,4,2);
//        System.out.println(g1);
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

        var g1 = new Graph(9);
        g1.addEdgeUnoriented(7, 5, 1);
        g1.addEdgeUnoriented(7, 1, 1);
        g1.addEdgeUnoriented(7, 2, 1);
        g1.addEdgeUnoriented(1, 0, 1);
        g1.addEdgeUnoriented(1, 4, 1);
        g1.addEdgeUnoriented(2, 3, 1);
        g1.addEdgeUnoriented(5, 6, 1);
        g1.addEdgeUnoriented(6, 8, 1);
        System.out.println(g1.bfs(7));
        System.out.println(g1.connected());
    }
}
