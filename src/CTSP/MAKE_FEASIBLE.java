package CTSP;

class MAKE_FEASIBLE {

    public int[][] makeFeasible(int[][] crossPool, int popSize, int noc) {

        for (int i = 0; i < popSize; i++) {
            int[] gene = extractGene(crossPool, i, noc);
            
        }

        return crossPool;
    } // End of makeFeasible function

    private static int[] extractGene(int[][] crossPool, int index, int noc) {
        int[] extractedGene = new int[noc];
        int i = 0;

        for (; crossPool[index][i] != -1; i++) {
            extractedGene[i] = crossPool[index][i];
        }
        extractedGene[i] = crossPool[index][i];

        return extractedGene;
    }   // End of gene extraction for making it feasible
    
    private static boolean isFeasible(int[] gene, int noc){
        int[] covered = new int[noc + 1];
    }
}
