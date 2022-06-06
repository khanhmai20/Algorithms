import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {
    private final WordNet wordnet;
    public Outcast(WordNet wordnet) {
        this.wordnet = wordnet;
    }

    public String outcast(String[] nouns) {
        int[] preSum = new int[nouns.length];
        String outcast = "";
        int maxLength = Integer.MIN_VALUE;

        /*
        * Using Dynamic Programming
        * */
        for (int pointer1 = 0; pointer1 < nouns.length; pointer1++) {
            int distance = preSum[pointer1];
            for (int pointer2 = pointer1 + 1; pointer2 < nouns.length; pointer2++) {
                int addition = wordnet.distance(nouns[pointer1],nouns[pointer2]);
                preSum[pointer2] += addition;
                distance += addition;
            }
            if (distance > maxLength) {
                maxLength = distance;
                outcast = nouns[pointer1];
            }
        }
        return outcast;
    }

    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        Outcast outcast = new Outcast(wordnet);
        String[] nouns = {"Turing", "von_Neumann", "Mickey_Mouse"};

        StdOut.println(outcast.outcast(nouns));
    }
}
