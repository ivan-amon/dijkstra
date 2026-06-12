public class Node implements Comparable<Node> {
    private int id;
    private int totalCost;

    public Node(int id, int minDistance) {
        this.id = id;
        this.totalCost = minDistance;
    }

    @Override
    public int compareTo(Node otherNode) {
        return Integer.compare(totalCost, otherNode.getTotalCost());
    }

    public int getId() {
        return id;
    }

    public int getTotalCost() {
        return totalCost;
    }
}