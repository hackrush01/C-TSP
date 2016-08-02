package CTSPM;

class TWO_OPT {

    /**
     * {@code
     * repeat until no improvement is made {
     * start_again:
     * best_distance = calculateTotalDistance(existing_route)
     *  for (i = 0; i < number of nodes eligible to be swapped - 1; i++) {
     *     for (k = i + 1; k < number of nodes eligible to be swapped; k++) {
     *         new_route = 2optSwap(existing_route, i, k)
     *         new_distance = calculateTotalDistance(new_route)
     *         if (new_distance < best_distance) {
     *             existing_route = new_route
     *             goto start_again
     *         }
     *     }
     * }
     * }
     */
    public int[][] twoOpt(int[][] _2OptPool, float[][] distArray, int popSize, int N) {
        for (int pop = 0; pop < N; pop++) {              // N is the of genes to be two opted

            int improvCounter = 0;
            int index_LocalSearch = (int) (Math.random() * popSize);
            int[] gene = extractGene(_2OptPool, index_LocalSearch, distArray.length); // length of dist Array = noc
//            System.out.println(Arrays.toString(gene));
            int last_i = lastGeneIndex(gene);

            for (; improvCounter < 20; improvCounter++) {
                int bestDist = FITNESS.calcFitness(gene, distArray);

                for (int i = 1; i < last_i - 1; i++) {

                    for (int k = i + 1; k < last_i; k++) {
                        int[] new_route = twoOptSwap(gene, i, k);
                        new_route[last_i] = -1;
//                        System.out.println(Arrays.toString(new_route));
                        int newDist = FITNESS.calcFitness(new_route, distArray);

                        if (newDist < bestDist) {
                            improvCounter = 0;
                            System.arraycopy(new_route, 0, gene, 0, gene.length);
//                            System.out.println(Arrays.toString(gene));
                            bestDist = newDist;
                        }
                    }
                }
            }
            for (int i = 0; gene[i] != -1; i++) {
                _2OptPool[index_LocalSearch][i] = gene[i];
                System.out.print(_2OptPool[index_LocalSearch][i] + " ");
            }
            System.out.println();
        }
        return _2OptPool;
    } // End of Function twoOpt

    /**
     * {@code
     * 2optSwap(gene, i, k){
     * 1. take route[1] to route[i-1] and add them in order to new_route
     * 2. take route[i] to route[k] and add them in reverse order to new_route
     * 3. take route[k+1] to end and add them in order to new_route
     * return gene[];
     * }
     * }
     *
     * @param gene
     * @param i
     * @param k
     * @return gene[]
     */
    public int[] twoOptSwap(int[] gene, int i, int k) {
        int[] new_route = new int[gene.length];
        for (int a = 0; a < i; a++) {
            new_route[a] = gene[a];
        }
        for (int a = k, fill = i; a >= i; a--, fill++) {
            new_route[fill] = gene[a];
        }
        for (int a = k + 1; gene[a] != -1; a++) {
            new_route[a] = gene[a];
        }
        return new_route;
    } // End of 2 opt swapping funcion

    private static int[] extractGene(int[][] _2OptPool, int index, int noc) {
        // Method to EXTRACT gene one-by-one and make it feasible
        int[] extractedGene = new int[noc];
        int i = 0;

        for (; _2OptPool[index][i] != -1; i++) {
            extractedGene[i] = _2OptPool[index][i];
        }
        extractedGene[i] = _2OptPool[index][i];

        return extractedGene;
    }   // End of gene extraction for 2 opt

    @SuppressWarnings("empty-statement")
    private static int lastGeneIndex(int[] gene) {
        int index = 0;
        for (; gene[index] != -1; index++);
        return index;
    } // End of last gene index finding function
}
