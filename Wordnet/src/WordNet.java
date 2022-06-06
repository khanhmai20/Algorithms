import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.LinkedList;
import java.util.Set;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.HashSet;

public class WordNet {
    private final Digraph digraph; // a graph represent a hypernyms set
    private final Map<String, Set<Integer>> map; // a map represent a synsets noun -> vertex
    private final List<String> storage; // a list represent a synset vertex -> set
    private final SAP sap;

    public WordNet(String synsets, String hypernyms) {

        if (synsets == null && hypernyms == null) throw new IllegalArgumentException();
        In inSynset = new In(synsets);
        In inHypernym = new In(hypernyms);
        storage = new LinkedList<>();
        map = new HashMap<>();

        int vertex = 0;
        while (inSynset.hasNextLine()) {
            String line = inSynset.readLine();
            String[] component = line.split(",");
            String[] noun = component[1].split(" ");
            storage.add(component[1]);
            //Traverse the noun inside component
            for (String n : noun) {
                if (!map.containsKey(n)) {
                    Set<Integer> value = new HashSet<>();
                    value.add(Integer.parseInt(component[0])); //adding the id
                    map.put(n,value);
                }else {
                    Set<Integer> value = new HashSet<>();
                    value.add(Integer.parseInt(component[0])); //adding the id
                    value.addAll(map.get(n));
                    map.put(n,value);
                }
            }
            vertex++;
        }

        digraph = new Digraph(vertex);
        while (inHypernym.hasNextLine()) {
            String line = inHypernym.readLine();
            String[] component = line.split(",");
            for (int i = 1; i < component.length; i++) {
                digraph.addEdge(Integer.parseInt(component[0]),Integer.parseInt(component[i]));
            }
        }

        sap = new SAP(digraph);
    }

    public Iterable<String> nouns() {
        return map.keySet();
    }

    public boolean isNoun(String word) {
        if (word == null) throw new IllegalArgumentException();
        return map.containsKey(word);
    }

    public int distance(String nounA, String nounB) {
        if (!map.containsKey(nounA) || !map.containsKey(nounB)) throw new IllegalArgumentException();
        Iterable<Integer> v = map.get(nounA);
        Iterable<Integer> w = map.get(nounB);

        int length = sap.length(v,w);
        return length;
    }

    public String sap(String nounA, String nounB) {
        Iterable<Integer> v = map.get(nounA);
        Iterable<Integer> w = map.get(nounB);
        int ancestor = sap.ancestor(v,w);
        return storage.get(ancestor);
    }

    public static void main(String args[]) {
        WordNet wordNet = new WordNet(args[0],args[1]);

        System.out.println(wordNet.sap("damage","group_action"));
    }
}
