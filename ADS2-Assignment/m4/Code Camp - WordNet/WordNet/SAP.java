import java.util.ArrayList;
/**
 * Class for sap.
 */
public class SAP {
    /**
     * graph object.
     */
    private Digraph graph;
    /**
     * Constructs the object.
     *
     * @param      gra     graph object.
     */
    public SAP(final Digraph gra) {
        graph = gra;
    }

    /**
     * this method returns the shortest path of both vertices.
     *
     * @param      listOne  The list one
     * @param      listTwo  The list two
     *
     * @return     returns the shortest path of both vertices.
     */
    public int[] length(final ArrayList<Integer> listOne,
                        final ArrayList<Integer> listTwo) {
        int min = graph.vertices();
        int tempOne = 0;
        for (int i = 0; i < listOne.size(); i++) {
            for (int k = 0; k < listTwo.size(); k++) {
                BreadthFirstDirectedPaths bfsOne =
                    new BreadthFirstDirectedPaths(graph, listOne.get(i));
                BreadthFirstDirectedPaths bfsTwo =
                    new BreadthFirstDirectedPaths(graph, listTwo.get(k));
                for (int j = 0; j < graph.vertices(); j++) {
                    if (bfsOne.hasPathTo(j) && bfsTwo.hasPathTo(j)) {
                        int sum = bfsOne.getDist(j) + bfsTwo.getDist(j);
                        if (sum < min) {
                            min = sum;
                            tempOne = j;
                        }
                    }
                }
            }
        }
        int[] result = {min, tempOne};
        return result;
    }


}
