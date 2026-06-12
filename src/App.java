import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class App {

    public static void main(String[] args) {

        // 1. Define the total number of routers in our network (nodes 0, 1, 2, 3)
        int numRouters = 4;
        
        // 2. Initialize the LSDB structure (List of lists / Adjacency List)
        List<List<Link>> graph = new ArrayList<>();
        for (int i = 0; i < numRouters; i++) {
            graph.add(new ArrayList<>());
        }

        // 3. Simulate the link-state flooding (LSAs) by filling the network map
        // Exam graph layout:
        // 0 -> 1 (cost: 4)
        // 0 -> 2 (cost: 1)
        // 2 -> 1 (cost: 2)
        // 1 -> 3 (cost: 1)
        // 2 -> 3 (cost: 5)
        graph.get(0).add(new Link(1, 4));
        graph.get(0).add(new Link(2, 1));
        
        graph.get(2).add(new Link(1, 2));
        
        graph.get(1).add(new Link(3, 1));
        
        graph.get(2).add(new Link(3, 5));

        // 4. Define the source router (our local router executing the SPF)
        int sourceRouter = 0;
        System.out.println("Running Dijkstra algorithm from Router " + sourceRouter + "...");

        computeDijkstra(graph, sourceRouter);
        
        
        int[] distances = computeDijkstra(graph, sourceRouter);
        System.out.println("\n--- RESULT (Minimum Cost Table) ---");
        System.out.println(Arrays.toString(distances));
        System.out.println("Expected output: [0, 3, 1, 4]");
    }

    public static int[] computeDijkstra(List<List<Link>> graph, int sourceId) {

        int[] distances = new int[graph.size()];
        Arrays.fill(distances, 100_000_000); // 100_000_000 simulates infinite
        distances[sourceId] = 0;

        PriorityQueue<Node> queue = new PriorityQueue<>(); 

        queue.add(new Node(sourceId, 0));

        while(!queue.isEmpty()) {
            Node current = queue.poll();
            int currentId = current.getId();
            int currentDistance = current.getTotalCost();

            if(currentDistance > distances[currentId]) {
                continue;
            }

            for(Link edge : graph.get(currentId)) {
                int destinationId = edge.getDestinationId();
                int cost = edge.getCost();

                if(distances[currentId] + cost < distances[destinationId]) {
                    distances[destinationId] = distances[currentId] + cost;
                    queue.add(new Node(destinationId, distances[destinationId]));
                }
            }
        }

        return distances;
     }
}