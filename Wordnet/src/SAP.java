import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.List;
import java.util.LinkedList;

public class SAP {
    private final Digraph digraph;
    private final int[] ancestor;

    public SAP(Digraph G) {
        /* To maintain the immutability of this graph, I don't do referential copy
        *  but I will use manual copy
        * */
        digraph = new Digraph(G.V());
        for (int i = 0; i < G.V(); i++) {
            for (int j : G.adj(i)) {
                digraph.addEdge(i,j);
            }
        }
        ancestor = new int[2];
        ancestor[0] = -1;
        ancestor[1] = -1;
    }

    public int length(int v, int w) {
        BreadthFirstDirectedPaths directedPathsV1 = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths directedPathsV2 = new BreadthFirstDirectedPaths(digraph, w);

        int distanceMin = Integer.MAX_VALUE;

        for (int i = 0; i < digraph.V(); i++) {
            if (directedPathsV1.hasPathTo(i) && directedPathsV2.hasPathTo(i)) {
                int distance = directedPathsV1.distTo(i) + directedPathsV2.distTo(i);
                if (distance < distanceMin) {
                    distanceMin = distance;
                    ancestor[0] = i;
                }
            }
        }

        if (distanceMin == Integer.MAX_VALUE) {
            ancestor[0] = -1;
            return -1;
        }
        return distanceMin;
    }

    public int ancestor(int v, int w) {
        length(v,w);
        return ancestor[0];
    }

    public int length(Iterable<Integer> list1, Iterable<Integer> list2) {
        int minLength = Integer.MAX_VALUE;
        BreadthFirstDirectedPaths directedPathsV1 = new BreadthFirstDirectedPaths(digraph, list1);
        BreadthFirstDirectedPaths directedPathsV2 = new BreadthFirstDirectedPaths(digraph, list2);

        for (int i = 0; i < digraph.V(); i++) {
            if (directedPathsV1.hasPathTo(i) && directedPathsV2.hasPathTo(i)) {
                int distance = directedPathsV1.distTo(i) + directedPathsV2.distTo(i);
                if (distance < minLength) {
                    minLength = distance;
                    ancestor[1] = i;
                }
            }
        }

        if (minLength == Integer.MAX_VALUE) {
            ancestor[1] = -1;
            return -1;
        }
        return minLength;
    }

    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        length(v,w);
        return ancestor[1];
    }

    public static void main(String args[]) {
        In in = new In(args[0]);
        Digraph digraph = new Digraph(in);

        SAP sap = new SAP(digraph);
        int v1 = 3;
        int v2 = 11;
        List<Integer> list1 = new LinkedList<>();
        list1.add(3);
        list1.add(9);
        list1.add(7);
        //list1.add(1);
        List<Integer> list2 = new LinkedList<>();
        list2.add(11);
        list2.add(12);
        list2.add(2);
        //list2.add(6);
        System.out.println(list1 + " " + list2);
        System.out.printf("length = %d, ancestor = %d\n",sap.length(list1,list2),
                            sap.ancestor(list1,list2));
    }
}
