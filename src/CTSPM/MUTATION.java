package CTSPM;

import java.util.Random;

class MUTATION {

    public int[][] mutate(int[][] crossPool, float mutateProb, int popSize) {
        for (int i = 0; i < popSize; i++) {
            float rand_prob = (float) new Random().nextDouble();

            if (rand_prob < mutateProb) {
                // System.out.println("Mark "+i); // Debug Only
                int lastPos = lastGeneIndex(crossPool, i);

                int mutatePt1 = new Random().nextInt(lastPos);
                int mutatePt2 = new Random().nextInt(lastPos);

                for (; mutatePt2 == mutatePt1;) {
                    mutatePt2 = new Random().nextInt(lastPos);
                }

                int temp = crossPool[i][mutatePt1];
                crossPool[i][mutatePt1] = crossPool[i][mutatePt2];
                crossPool[i][mutatePt2] = temp;
            }

            for (int j = 0; crossPool[i][j] != -1; j++) {
                System.out.print(crossPool[i][j] + " ");
            }
            System.out.println();
        }
        return crossPool;
    } // End of mutation function

    @SuppressWarnings("empty-statement")
    private static int lastGeneIndex(int[][] crossPool, int i) {
        int index = 0;
        for (; crossPool[i][index] != -1; index++);
        return index;
    } // End of finding last gene function
}
