package CTSP;

class POPULATION {

    public int[][] popArrayRand(int popSize, int noc, int[][] coverArray) {
        int[][] popArray = new int[popSize][noc + 2];

        for (int i = 0; i < popSize; i++) {
            int[] covered = new int[noc + 1];

            for (int j = 0; covered[0] < noc; j++) {
                popArray[i][j] = (int) (Math.random() * noc);

                int Prize = covered[0];

                for (int k = 0; k < j;) {
                    if (popArray[i][k] != popArray[i][j]) {
                        k++;
                    } else {
                        popArray[i][j] = (int) (Math.random() * noc);
                        k = 0;
                    }
                }

                for (int k = 3; coverArray[popArray[i][j]][k] != -1; k++) {
                    covered[coverArray[popArray[i][j]][k] + 1]++;
                    if (covered[coverArray[popArray[i][j]][k] + 1] == 1) {
                        covered[0]++;
                    }
                }
                if (covered[0] == Prize) {
                    j--;
                } else {
//                    System.out.print(popArray[i][j] + " "); // Debug Only
//                    PrintArr_Debug.printarr(covered);
                }
            }
//            System.out.println();   // Debug Only
        }
        return popArray;
    } // End of function popArrayRand

    public int[][] popArray3Closest(int popSize, int noc, int[][] coverArray, int dist, float[][] distArray) {
        int[][] popArray = new int[popSize][noc + 2];

        for (int i = 0; i < popSize; i++) {     //filling popArray, iterating popArray
            int[] covered = new int[noc + 1];   // for checking uncovered and covered cities
            int j = 1;  // To make it available to this loop
            popArray[i][0] = (int) (Math.random() * noc);   // Depot, 2014 Research paper

            for (int k = 3; coverArray[popArray[i][0]][k] != -1; k++) {     //* Filling covered array, k=3 as coverArray starts from 3 *//
                covered[coverArray[popArray[i][0]][k] + 1]++;               //* coverArray is index based, so +1 to convert            *//
                if (covered[coverArray[popArray[i][0]][k] + 1] == 1) {      //* to number for "covered[]" (number based)               *//
                    covered[0]++;                                           // collected prize
                }
            }
//            System.out.println("Pop1 " + popArray[i][0] + " " + covered[0]);  // Debug only
            System.out.print(popArray[i][0] + " ");
//            PrintArr_Debug.printarr(covered, noc); // Debug only

            int coverMax = (int) Math.ceil(new DIST().maxDist(distArray, noc)); // Max distance of cities, for prize 278 & 279

            for (; covered[0] < noc; j++) {    // actual filling, finding correct facility
                new COVER_MATRIX().closestCity(distArray, noc, dist, popArray[i][j - 1], covered, coverArray, coverMax); // Finding next 3 closest cities
                int pos = (int) (Math.random() * 3); // selecting out of 3 closest
                popArray[i][j] = coverArray[popArray[i][j - 1]][pos];   //next element of popArray
//                System.out.println(popArray[i][j-1]); // Debug only

                int Prize = covered[0];  // prize for unrepeated covered[0]

                for (int k = 0; k < j;) {                          // Checking for duplicates
                    if (popArray[i][k] != popArray[i][j]) {
                        k++;
                    } else {
                        popArray[i][j] = (int) (Math.random() * noc);
                        k = 0;
                    }
                }

                for (int k = 3; coverArray[popArray[i][j]][k] != -1; k++) {     // updating coverd[]
                    covered[coverArray[popArray[i][j]][k] + 1]++;
                    if (covered[coverArray[popArray[i][j]][k] + 1] == 1) {
                        covered[0]++;
                    }
                }
                if (covered[0] == Prize) {
                    j--;                        // if repeated then trying again
                } //                else {
                //                      System.out.println("Pop" + (j + 1) + " " + popArray[i][j] + " " + covered[0]); // Debug Only
                //                      PrintArr_Debug.printarr(covered);
                //                } // Debug condition for printing populuation with pop no. and prize at end
                else {
                    System.out.print(popArray[i][j] + " "); // Debug only
                } // Debug only, for printing the population

            }
            popArray[i][j] = -1;
            System.out.println(popArray[i][j]);   // Debug Only
        }
        return popArray;
    }
}
