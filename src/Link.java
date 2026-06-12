public class Link {
    private int destinationId;
    private int cost;

    public Link(int destinationId, int cost) {
        this.destinationId = destinationId;
        this.cost = cost;
    }

    public int getDestinationId() {
        return destinationId;
    }

    public int getCost() {
        return cost;
    }    
}