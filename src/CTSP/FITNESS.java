package CTSP;

class FITNESS {

    public int[][] calcFitness(int[][] popArray, float[][] distArray, int noc, int popSize) {
        for (int i = 0; i < popSize; i++) {  // For filling fitness, iterating popArray
            float sum = 0;
            for (int j = 1; popArray[i][j] != -1; j++) {    // Actual filling of popArray
//                System.out.print(distArray[popArray[i][j]][popArray[i][j - 1]] + " + "); // Debug Only
                sum += distArray[popArray[i][j]][popArray[i][j - 1]];   //Adding distances of popArray element j and j-1
            }
            float sum1 = (float) (Float.parseFloat(new java.text.DecimalFormat("0.000").format(sum)));
            // (up) Changing sum to 3 decimal places, b'coz of some wierd problems in Adding, which gives more than 3 decimal
            // answer on adding 3 decimals, ask from sir or search the net.
            popArray[i][noc] = (int) (sum1*1000); // to change decimal to int for storing in int type array(clever ;-) )
//            System.out.println(popArray[i][noc]); // Debug Only
        }
        return popArray;
    }
}
