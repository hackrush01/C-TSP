package CTSP;

class COVER_MATRIX {

    public int[][] coverArray(float[][] distArray, int noc, int dist) {
        int[][] coverArray = new int[noc][noc];

        for (int i = 0; i < noc; i++) {     // For filling coverArray with citites within given distance(iterating distArray)
            int counter = 3;     // Counter from 3, so as to fill 3 closest cities before it
//            System.out.print(i+" "); //Debug Only
            for (int j = 0; j < noc; j++) {      // Iterating coverArray (actual filling and condition check)
                if (distArray[i][j] <= dist) {     // Condition check for distance and INCLUDING itself
                    coverArray[i][counter] = j;         // Giving city INDEX
//                    System.out.print(coverArray[i][counter] + "  "); // Debug only
                    counter++;
                }
            }

            coverArray[i][counter] = -1;      // To specify end
//            System.out.print(coverArray[i][counter] + "  " + (counter - 3)); // Debug only
//            System.out.println(); // Debug only
        }
        return coverArray;
    } // End of function coverArray

    public int[][] closestCity(float[][] distArray, int noc, int dist, int i, int[] covered, int[][] coverArray, int coverMax) {

        int counter = 0;  // Taking three closest city

        for (int coverdist = dist + 1; counter < 3 && coverdist <= coverMax; coverdist++) {  // Count till 3 and increasing distance
//                System.out.print(coverdist-1 + "-" + coverdist + "->"); // Debug Only
            for (int j = 0; j < noc && counter < 3; j++) {  //Filling coverArray only first three places
                if (covered[j + 1] == 0) {
                    if ((distArray[i][j] <= coverdist) && (distArray[i][j] > coverdist - 1)) {
                        coverArray[i][counter] = j;  // Taking city INDEX instead of number
//                        System.out.print(coverArray[i][counter] + "    "); // Debug Only
                        counter++;
                    }
                }
            }
        }
//        System.out.println();  // Debug only
        PrintArr_Debug.printarr(coverArray, i);  // Debug only
        return coverArray;
    } // End of function closestCity

    public int nextClosest(int[] covered, int dist, float[][] distArray, int i, int noc) {
        int cityIndex = 0, counter = 0;

        for (int coverdist = dist + 1; counter == 0; coverdist++) {
            for (int j = 0; j < noc; j++) {
                if (covered[j] == 0) {
                    if ((distArray[i][j] <= coverdist) && (distArray[i][j] > coverdist - 1)) {
                        cityIndex = j;
                    }
                }
            }
        }
        return cityIndex;
    }
}
