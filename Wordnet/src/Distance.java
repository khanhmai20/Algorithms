import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.Stack;

import java.util.*;
import java.util.Queue;

public class Distance {
    public static void main(String args[]) {
        In in = new In(args[0]);
        Digraph digraph = new Digraph(in);

        List<Integer> list1 = new LinkedList<>();
        list1.add(3);
        list1.add(12);
        list1.add(7);
        List<Integer> list2 = new LinkedList<>();
        list2.add(11);
        list2.add(9);
        list2.add(2);

        int distanceMin = Integer.MAX_VALUE;
        int sca = 0;
        BreadthFirstDirectedPaths directedPathsV1 = new BreadthFirstDirectedPaths(digraph, list1);
        BreadthFirstDirectedPaths directedPathsV2 = new BreadthFirstDirectedPaths(digraph, list2);

        List<Integer> ancestor = new LinkedList<>();
        Queue<Integer> queue = new LinkedList<Integer>();

        for (int v : list1) {
            queue.add(v);
            while (!queue.isEmpty()) {
                int vertex = queue.poll();
                if (!ancestor.contains(vertex)) {
                    for (int i : digraph.adj(vertex)) queue.add(i);     //trace ancestor
                    ancestor.add(vertex);
                }
            }
        }
        System.out.println(ancestor);
        for (int vertex : ancestor) {
            if (directedPathsV2.hasPathTo(vertex)) {
                int distance = directedPathsV1.distTo(vertex) + directedPathsV2.distTo(vertex);
                if (distance < distanceMin) {
                    distanceMin = distance;
                    sca = vertex;
                }
            }
        }

        System.out.println(list1);
        System.out.println(list2);
        System.out.println(distanceMin);
        System.out.println(sca);
    }
}
