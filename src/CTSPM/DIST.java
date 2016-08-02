package CTSPM;

class DIST {

    public float[][] distArray(int[][] coorTable, int noc) {
        float[][] distArray = new float[noc][noc];
        
        // Distance form all cities to all cities
        for (int i = 0; i < noc; i++) {
            int x1 = coorTable[i][0];
            int y1 = coorTable[i][1];

            for (int j = i; j < noc; j++) {
                int x2 = coorTable[j][0];
                int y2 = coorTable[j][1];

                float dist = (float) Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));  // Distance formula
//                dist = Float.parseFloat(new java.text.DecimalFormat("0.000").format(dist));   // Upto 3 decimal places
                dist = ((int) (dist*1000))/1000f;   // Upto 3 decimal places, the above one takes a lot more time
                distArray[i][j] = dist;     //  Setting both consecutively
                distArray[j][i] = dist;     //  -------------"------------

//                System.out.print(distArray[i][j]+ " ");  // Debug only
            }

//            System.out.println(); // Debug only
        }

        return distArray;
    }

    public float maxDist(float[][] distTable, int noc) {
        float maxDist = 0;
        for (int i = 0; i < noc; i++) {
            for (int j = 0; j < noc; j++) {
                if (distTable[i][j] >= maxDist) {
                    maxDist = distTable[i][j];
                }
            }
        }
        return maxDist;
    }
}
