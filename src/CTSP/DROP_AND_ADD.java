package CTSP;

import java.util.ArrayList;

/**
 *
 * @author Amit-Gaming
 */
public class DROP_AND_ADD {

    public int[][] dropAndAdd(int[][] dropAddPool, float[][] distArray, int[][] coverTable, int popSize, int N) {
        int noc = distArray.length;
        for (int pop = 0; pop < N; pop++) {
            int index_LocalSearch = (int) (Math.random() * popSize);
            int[] gene = extractGene(dropAddPool, index_LocalSearch, noc); // length of dist Array = noc
            int last_i = lastGeneIndex(gene);

            for (int i = 0; i < last_i; i++) {
                int bestFit = FITNESS.calcFitness(gene, distArray);
                int bestFac = gene[i];
                int extractedFac = gene[i];
                int currentCover[] = coverAfterExtraction(gene, coverTable, extractedFac, noc);
                ArrayList<Integer> uncovered = new ArrayList<>();

                for (int j = 0; j < noc; j++) {
                    if (currentCover[j] == 0) {
                        uncovered.add(j - 1); // Adding INDEX(not number) of uncovered cities to uncovered ArrayList
                    }
                }

                uncovered.remove(new Integer(extractedFac));
                int uncoveredSize = uncovered.size();

                for (int j = 0; j < uncoveredSize; j++) {
                    gene[i] = uncovered.get(j);
                    int covered[] = cover(gene, coverTable, noc);
                    if (covered[0] != noc) {
                        gene[i] = -2;
                    } else {
                        if (FITNESS.calcFitness(gene, distArray) < bestFit) {
                            bestFit = FITNESS.calcFitness(gene, distArray);
                            bestFac = gene[i];
                        }
                    }
                    
                    if (gene[i] == -2) gene[i] = bestFac;
                }
            }
        }
        return dropAddPool;
    } // End of function dropAndAdd

    private static int[] coverAfterExtraction(int[] gene, int[][] coverArray, int facToIgnore, int noc) {
        // Method to check if the extracted gene is already feasible or not, which happens when collected prize
        // stored in covered[0] is equal to no. of cities
        int[] covered = new int[noc + 1];

        for (int i = 0; gene[i] != -1; i++) {
            if (gene[i] == facToIgnore) {
                continue;
            }

            for (int j = 3; coverArray[gene[i]][j] != -1; j++) {
                covered[coverArray[gene[i]][j] + 1]++;
                if (covered[coverArray[gene[i]][j] + 1] == 1) {  // coverArray is index based and covered is number based
                    covered[0]++;                                //as 0th pos stores collected prize
                }
            }
        }
        return covered;
    } // End of function coverAfterExtraction

    private static int[] cover(int[] gene, int[][] coverArray, int noc) {
        // Method to check if the extracted gene is already feasible or not, which happens when collected prize
        // stored in covered[0] is equal to no. of cities
        int[] covered = new int[noc + 1];

        for (int i = 0; gene[i] != -1; i++) {
            for (int j = 3; coverArray[gene[i]][j] != -1; j++) {
                covered[coverArray[gene[i]][j] + 1]++;
                if (covered[coverArray[gene[i]][j] + 1] == 1) {  // coverArray is index based and covered in number based
                    covered[0]++;                                //as 0th pos stores collected prize
                }
            }
        }
        return covered;
    } // End of function cover

    private static int[] extractGene(int[][] dropAddPool, int index, int noc) {
        // Method to EXTRACT gene one-by-one and make it feasible
        int[] extractedGene = new int[noc];
        int i = 0;

        for (; dropAddPool[index][i] != -1; i++) {
            extractedGene[i] = dropAddPool[index][i];
        }
        extractedGene[i] = dropAddPool[index][i];

        return extractedGene;
    }   // End of gene extraction for drop and add

    @SuppressWarnings("empty-statement")
    private static int lastGeneIndex(int[] gene) {
        int index = 0;
        for (; gene[index] != -1; index++);
        return index;
    } // End of last gene index finding function
}
