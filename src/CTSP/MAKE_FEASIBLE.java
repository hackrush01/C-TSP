package CTSP;

import java.util.Arrays;

class MAKE_FEASIBLE {

    public int[][] makeFeasible(int[][] crossPool, int coverArray[][], float[][] distArray, int popSize, int noc) {

        for (int i = 0; i < popSize; i++) {

            int[] gene = extractGene(crossPool, i, noc);

            int[] covered = cover(gene, coverArray, noc);

            for (; covered[0] < noc;) {      // If gene is not feasible, i.e. collected prize(covered[0]) is not = no. of cities
                gene = makeFeasible(gene, covered, distArray, coverArray);
                covered = cover(gene, coverArray, noc);
            }

            int j = 0;
            for (; gene[j] != -1; j++) {
                crossPool[i][j] = gene[j];
                System.out.print(crossPool[i][j] + " ");
            }
            crossPool[i][j] = -1;
            System.out.println();
        }

        return crossPool;
    } // End of makeFeasible function

    private static int[] extractGene(int[][] crossPool, int index, int noc) {
        // Method to EXTRACT gene one-by-one and make it feasible
        int[] extractedGene = new int[noc];
        int i = 0;

        for (; crossPool[index][i] != -1; i++) {
            extractedGene[i] = crossPool[index][i];
        }
        extractedGene[i] = crossPool[index][i];

        return extractedGene;
    }   // End of gene extraction for making it feasible

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

    private static int[] makeFeasible(int[] gene, int[] covered, float[][] distArray, int[][] coverArray) {
        int notCovered = 0;
        int bestCoveringCityIndex = 0;
        int bestPrize = covered[0];
        int lastPos = lastGeneIndex(gene);
        int[] currentCover = new int[covered.length];

        //<editor-fold defaultstate="collapsed" desc="Selecting facility covering maximum cities">
        for (int i = 1; i < covered.length; i++) {

            if (covered[i] == 0) {
                notCovered = i - 1; // Number to index change
                gene[lastPos] = notCovered; // Inserting non covered city to the last, it's position will be changed later(hadbadi qa h?)
                gene[lastPos + 1] = -1;     // Terminating gene with -1
                currentCover = cover(gene, coverArray, covered.length - 1);  // Generating new covered array to compare with old (Array Required???)
                // covered.length(=noc +1) - 1, b'coz later in function cover,
                // array is initialized with noc +1
                // so to negate that + 1, -1 is used
                // System.out.println(Arrays.toString(gene));  // Debug Only
                // System.out.println(Arrays.toString(currentCover));  // Debug Only
            }

            if (currentCover[0] > bestPrize) {
                bestPrize = currentCover[0];
                bestCoveringCityIndex = notCovered;
                //System.out.println("bestCoveringCityIndex " + bestCoveringCityIndex + " bestPrize " + bestPrize); // Debug Only
            }
        }
        //System.out.println("bestCoveringCityIndex " + bestCoveringCityIndex + " bestPrize " + bestPrize);  // Debug Only
        //</editor-fold>

        gene[lastPos] = bestCoveringCityIndex;
        int bestFitness = FITNESS.calcFitness(gene, distArray);
        // System.out.println("fittnes " + bestFitness); // Debug Only
        int bestPos = lastPos;

        //<editor-fold defaultstate="collapsed" desc="Selecting best insertion spot for facility">
        for (int i = lastPos - 1; i >= 0; i--) {
            gene[i + 1] = gene[i];
            gene[i] = bestCoveringCityIndex;

            if (FITNESS.calcFitness(gene, distArray) < bestFitness) {
                bestFitness = FITNESS.calcFitness(gene, distArray);
                bestPos = i;
            }
            // System.out.println("gene " + Arrays.toString(gene)); // Debug Only
            // System.out.println("fitnes " + FITNESS.calcFitness(gene, distArray)); // Debug Only
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Inserting best facility to best position">
        for (int i = 0; i < bestPos; i++) {
            gene[i] = gene[i + 1];
            gene[i + 1] = bestCoveringCityIndex;
        }
        //</editor-fold>
        // System.out.println("best pos " + bestPos + " best gene " + Arrays.toString(gene)); // Debug Only

        return gene;
    } // End of function real makeFeasible

    @SuppressWarnings("empty-statement")
    private static int lastGeneIndex(int[] gene) {
        int index = 0;
        for (; gene[index] != -1; index++);
        return index;
    }
}
