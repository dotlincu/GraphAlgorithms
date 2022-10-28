import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
//        var g1 = new GraphList("./cm/rg300_4730.txt");
//        var g2 = new GraphMatrix("./cm/rg300_4730.txt");
////        System.out.println(g1.getAdjList());
//
//        long startTime = System.currentTimeMillis();
//        System.out.println("Dijsktra: " + g1.dijkstra(49, 50));
//        long finalTime = System.currentTimeMillis();
//        System.out.printf("Tempo: %.3fs\n", ((finalTime - startTime) / 1000d));
//
//        startTime = System.currentTimeMillis();
//        System.out.println("BellmanFord: " + g1.bellmanFord(49, 50));
//        finalTime = System.currentTimeMillis();
//
//        System.out.printf("Tempo: %.3fs\n", ((finalTime - startTime) / 1000d));
//
//        startTime = System.currentTimeMillis();
//        System.out.println("BellmanFord Melhorado: " + g1.bellmanFordMelhorado(49, 50));
//        finalTime = System.currentTimeMillis();
//
//        System.out.printf("Tempo: %.3fs\n", ((finalTime - startTime) / 1000d));
//
//        startTime = System.currentTimeMillis();
//        System.out.println("Floyd Warshall: " + g2.floydWarshall(49, 50));
//        finalTime = System.currentTimeMillis();
//
//        System.out.printf("Tempo: %.3fs\n", ((finalTime - startTime) / 1000d));


        int x = 0, origin, destiny;
        String name;
        Scanner sc = new Scanner(System.in);
        long startTime, finalTime;
        File file;
        GraphList graph;
        try {
            while (x != 3) {
                System.out.println("\nInforme a tarefa: ");
                System.out.println("\t1 - Caminho Mínimo\n\t2 - Labirinto\n\t3 - Sair");
                x = Integer.parseInt(sc.nextLine());
                switch (x) {
                    case 1:
                        name = "./cm/";
                        System.out.print("Arquivo: ");
                        name += sc.nextLine();
                        System.out.print("Origem: ");
                        origin = Integer.parseInt(sc.nextLine());
                        System.out.print("Destino: ");
                        destiny = Integer.parseInt(sc.nextLine());
                        file = new File(name);
                        if (file.isFile()) {
                            graph = new GraphList(name);
                            startTime = System.currentTimeMillis();
                            System.out.println("Processando...");
                            System.out.println("Caminho: " + graph.dijkstra(origin, destiny));
                            finalTime = System.currentTimeMillis();
                            System.out.printf("Tempo: %.3fs\n", ((finalTime - startTime) / 1000d));
                        } else
                            System.out.println("\nArquivo não encontrado!");
                        break;
                    case 2:
                        name = "./maze/";
                        System.out.print("Arquivo: ");
                        name += sc.nextLine();
                        file = new File(name);
                        if(file.isFile()) {
                            startTime = System.currentTimeMillis();
                            System.out.println("Processando...");
                            GraphMatrix.mazeGenerator(name);
                            finalTime = System.currentTimeMillis();
                            System.out.printf("Tempo: %.3fs\n", ((finalTime - startTime) / 1000d));
                        } else
                            System.out.println("\nArquivo não encontrado!");
                        break;
                    case 3:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Comando Inválido!");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
