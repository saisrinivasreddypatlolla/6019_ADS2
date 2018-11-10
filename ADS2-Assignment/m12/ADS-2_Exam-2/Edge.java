/**
 * Class for edge.
 */
class Edge implements Comparable<Edge> {
    /**
     * first vertex to connect.
     */
    private int vertexOne;
    /**
     * second vertex connect from first one.
     */
    private int vertexTwo;
    /**
     * weight of the edge.
     */
    private double weight;
    /**
     * Constructs the object.
     *
     * @param      v     first vertex
     * @param      w     second vertex
     * @param      wei   The weight
     */
    Edge(final int v, final int w, final double wei) {
        this.vertexOne = v;
        this.vertexTwo = w;
        this.weight = wei;
    }
    /**
     * returns first end of edge.
     *
     * @return     returns first end of edge.
     */
    public int either() {
        return vertexOne;
    }
    /**
     * returns other end of edge.
     *
     * @param      v     connected vertex.
     *
     * @return      returns other end of edge.
     */
    public int other(final int v) {
        if (vertexOne == v) {
            return vertexTwo;
        }
        return vertexOne;
    }
    /**
     * compares both edges weight.
     *
     * @param      that  The that
     *
     * @return     returns integer value.
     */
    public int compareTo(final Edge that) {
        if (this.weight < that.weight) {
            return -1;
        } else if (this.weight > that.weight) {
            return 1;
        }
        return 0;
    }
    /**
     * Gets the weight.
     *
     * @return     The weight.
     */
    public double getWeight() {
        return this.weight;
    }
    /**
     * Returns a string representation of the object.
     *
     * @return     String representation of the object.
     */
    public String toString() {
        return String.format("%d-%d %.5f", vertexOne, vertexTwo, weight);
    }
}