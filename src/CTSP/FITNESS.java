package CTSP;

class FITNESS {

    public int[][] calcFitness(int[][] Array, float[][] distArray, int noc, int popSize) {
        int max = 0;

        for (int i = 0; i < popSize; i++) {  // For filling fitness, iterating popArray
            float sum = 0;
            for (int j = 1; Array[i][j] != -1; j++) {    // Actual filling of popArray
//                System.out.print(distArray[popArray[i][j]][popArray[i][j - 1]] + " + "); // Debug Only
                sum += distArray[Array[i][j]][Array[i][j - 1]] * 1000;   //Adding distances of popArray
                // element j and j-1. and multiplying by 1000 to remove the decimal
            }

            if (sum > max) {        // Finding max of all the fitness
                max = (int) sum;
            }

            Array[i][noc] = (int) (sum); // to change decimal to int for storing in int type array(clever ;-) )
//            System.out.println(i + " " + Array[i][noc]); // Debug Only
        }

        for (int i = 0; i < popSize; i++) {   // For putting max-fitness, for roulette whel selection
            Array[i][noc + 1] = max - Array[i][noc];
//            System.out.println(popArray[i][noc + 1]); // Debug Only
        }

        return Array;
    } // End of calculateFitness function

    public static int calcFitness(int[] Array, float[][] distArray) {
        float fitness = 0;
        
        for (int i = 1; Array[i] != -1; i++) {
            fitness += distArray[Array[i]][Array[i - 1]] * 1000;
        }
        
        return (int) fitness;
    }
}
