/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Client {
    public static void main(String[] args) {
        String size = args[0];
        StdOut.print(size);
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            StdOut.print(s);
        }
    }
}
