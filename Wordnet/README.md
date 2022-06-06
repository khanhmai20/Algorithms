# Implementation tips 
1. Be caustious about the immutablity class in java. In here I use keyword final for private field to keep datastructure intact if client method try to tame them. 
2. In the SAP class, I find the common ancestor using queue and trace the adj[] matrix, which will potentially result in O(n^2) loop. Therefore, it's a bad approach. Later, I make some minor modification
3. I re-implement this slightly by just traverse all the vertex of the graph, which is only O(V) in time. 
4. In Outcast class, I use dynamic programming to avoid recompute certain caculations 
