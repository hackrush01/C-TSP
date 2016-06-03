package CTSP;

import java.util.Random;

class SELECTION {

    public int[][] select(int popSize, int noc, int[][] popArray) {   // Roulette selection
        int[][] selectPool = new int[popSize][noc + 2];  // Init selectPool
        long sum_fit = 0;  // sum of max-fitness

        for (int i = 0; i < popSize; i++) {   // Loop for adding max-fitness of 
            sum_fit += popArray[i][noc + 1];
        }

        for (int i = 0; i < popSize; i++) {     // Roulette wheel selection(request sir to explain)
            long rand_num = (long) ((new Random().nextDouble()) * sum_fit);  // taking random number (uniformaly distributed)
            int index = -1; // index Init for erroneous case (which never arises btw)

            for (int j = 0; j < popSize; j++) {     // Logic of roulette selection, finding index for filling selectPool
                rand_num -= popArray[j][noc + 1];   // rand_num = rand_num - (max-fitness), decreasing count by weights(maybe)
                if (rand_num <= 0) {        // If rand_num <=0 
                    index = j;              // then that gene is selected
                    break;
                }
            }
//            System.out.print(index + " "+popArray[index][noc]+" "+popArray[index][noc+1]+" "); // Debug Only

            for (int j = 0; j < noc + 1; j++) {     // Copying gene and fitness@noc to selectPool
                selectPool[i][j] = popArray[index][j];
//                System.out.print(selectPool[i][j]+" "); // Debug Only
            }
            selectPool[i][noc + 1] = index; // Adding index of gene to the end for selection for crossing
            System.out.print(selectPool[i][noc + 1]+" "); // Debug only
            System.out.println(); // Debug Only
        }

        return selectPool;
    }
}
