package CTSP;

class POPULATION {

    public int[][] popArrayRand(int popSize, int noc, int[][] coverArray) {
        int[][] popArray = new int[popSize][noc];

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
                    covered[coverArray[popArray[i][j]][k]]++;
                    if (covered[coverArray[popArray[i][j]][k]] == 1) {
                        covered[0]++;
                    }
                }
                if (covered[0] == Prize) {
                    j--;
                } else {
                    System.out.print(popArray[i][j] + " "); // Debug Only
//                    PrintArr_Debug.printarr(covered);
                }
            }
            System.out.println();   // Debug Only
        }
        return popArray;
    } // End of function popArrayRand

    public int[][] popArray3Closest(int popSize, int noc, int[][] coverArray, int dist, float[][] distArray) {
        int[][] popArray = new int[popSize][noc];

        for (int i = 0; i < popSize; i++) {
            int[] covered = new int[noc + 1];
            popArray[i][0] = (int) (Math.random() * noc);

            for (int k = 3; coverArray[popArray[i][0]][k] != -1; k++) {
                covered[coverArray[popArray[i][0]][k]]++;
                if (covered[coverArray[popArray[i][0]][k]] == 1) {
                    covered[0]++;
                }
            }
//            System.out.println("Pop1 " + popArray[i][0] + " " + covered[0]);  // Debug only
//            PrintArr_Debug.printarr(covered, noc); // Debug only

            int coverMax = (int) Math.ceil(new DIST().maxDist(distArray, noc));

            for (int j = 1; covered[0] < noc; j++) {
                new COVER_MATRIX().closestCity(distArray, noc, dist, popArray[i][j - 1], covered, coverArray, coverMax);
                int pos = (int) (Math.random() * 3);
                popArray[i][j] = coverArray[popArray[i][j - 1]][pos];
//                System.out.println(popArray[i][j-1]); // Debug only

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
                }
//                else {
//                      System.out.println("Pop" + (j + 1) + " " + popArray[i][j] + " " + covered[0]); // Debug Only
//                      PrintArr_Debug.printarr(covered);
//                } // Debug condition for printing populuation with pop no. and prize at end
                else
                    System.out.print(popArray[i][j]+" "); // Debug only
                
                
            }
            System.out.println();   // Debug Only
        }
        return popArray;
    }
}
