package CTSPM;

import java.util.ArrayList;

/**
 *
 * @author Amit-Gaming
 */
public class DROP_AND_ADD {

    public int[][] dropAndAdd(int[][] dropAddPool, float[][] distArray, int[][] coverTable, int popSize,
            int N, ArrayList facilities, ArrayList customers) {

        int noc = distArray.length;
        int prize = (int) (customers.size() * 0.8);  // 80% of no. of customers

        for (int pop = 0; pop < N; pop++) {     // only 'N' genes will be Local Searched
            int index_LocalSearch = (int) (Math.random() * popSize);       // index of gene which will be local searched
            int[] gene = extractGene(dropAddPool, index_LocalSearch, noc); // length of dist Array = noc
            int last_i = lastGeneIndex(gene);                              // index of -1

            for (int i = 1; i < last_i; i++) {  // i = 1, so as not to drop the depot
                int bestFit = FITNESS.calcFitness(gene, distArray);
                int bestFac = gene[i];
                int extractedFac = gene[i];  // Facility to DROP
                int currentCover[] = coverAfterExtraction(gene, coverTable, extractedFac, noc);  // Finding cover matrix afer removing a fac
                ArrayList<Integer> uncovered = new ArrayList<>();   // ArrayList of uncovered facilities

                for (int j = 0; j < noc; j++) {     
                    if (currentCover[j] == 0 && facilities.contains(j - 1)) {
                        uncovered.add(j - 1); // Adding INDEX(not number) of uncovered facilities to uncovered ArrayList
                    }
                }

                uncovered.remove(new Integer(extractedFac));  // Removing extracted fac from the ArrayList
                int uncoveredSize = uncovered.size();         // getting size of ArrayList

                for (int j = 0; j < uncoveredSize; j++) {     // ADDING part
                    gene[i] = uncovered.get(j);               // Replacing Dropped fac with uncovered
                    int covered[] = cover(gene, coverTable, noc);   // New covered array

                    int currentFitness = FITNESS.calcFitness(gene, distArray);
                    if (currentFitness < bestFit && covered[0] >= prize) {      // Only updates if prize is satisfied and it has better fitness
                        bestFit = currentFitness;
                        bestFac = gene[i];
                    }

                }
                gene[i] = bestFac;  // Updates with the bestFac
            }
            for (int j = 0; gene[j] != -1; j++) {       // Updates the dropAndAdd pool
                dropAddPool[index_LocalSearch][j] = gene[j];
            }
        }
        return dropAddPool;
    } // End of function dropAndAdd

    private static int[] coverAfterExtraction(int[] gene, int[][] coverArray, int facToIgnore, int noc) {
        // Method to check if the extracted gene is already feasible or not, which happens when collected prize
        // stored in covered[0] is equal to no. of cities
        int[] covered = new int[noc + 1];

        for (int i = 0; gene[i] != -1; i++) {
            if (gene[i] == facToIgnore) {       // If the Extraced Fac is encountered then the function doesn't compute it's covered cities
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
