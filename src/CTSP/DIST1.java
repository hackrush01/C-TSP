package CTSP;

class DIST1 {

    public float[][] distFacCustArray(int[][] coorTable, int depot, int[] facility, int[] customer, int noc) {
        float[][] distArray = new float[facility.length][customer.length];

        
        //For finding distance of customers form facilities
        for (int i = 0; i < facility.length; i++) { // rows of distArray
            int x1 = coorTable[facility[i]][0];
            int y1 = coorTable[facility[i]][1];

            for (int j = 0; j < customer.length; j++) {  // Cols of distArray
                int x2 = coorTable[customer[j]][0];
                int y2 = coorTable[customer[j]][1];
                
                // Distance of all customers from all facilities....
                float dist = (float) Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));  // Distance formula
//                dist = Float.parseFloat(new java.text.DecimalFormat("0.000").format(dist));   // Upto 3 decimal places
                dist = ((int) (dist * 1000)) / 1000f;   // Upto 3 decimal places, the above one takes a lot more time
                distArray[i][j] = dist;     //  Setting both consecutively
                //distArray[j][i] = dist;     //  -------------"------------

                //System.out.print(" The distance between the facility "+facility[i]+" and "+customer[j]+" is "+distArray[i][j]);  // Debug only
            }

//            System.out.println(); // Debug only
        }
        return distArray;
    }

    public float[][] distFacCustArray(int[][] coorTable, int[] facility) {
        float[][] distArray = new float[facility.length][facility.length];

        // Distance from all facilities to all facilities
        for (int i = 0; i < facility.length; i++) {
            int x1 = coorTable[facility[i]][0];
            int y1 = coorTable[facility[i]][1];

            for (int j = i + 1; j < facility.length; j++) {
                int x2 = coorTable[facility[j]][0];
                int y2 = coorTable[facility[j]][1];

                float dist = (float) Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));  // Distance formula
//                dist = Float.parseFloat(new java.text.DecimalFormat("0.000").format(dist));   // Upto 3 decimal places
                dist = ((int) (dist * 1000)) / 1000f;   // Upto 3 decimal places, the above one takes a lot more time
                distArray[i][j] = dist;     //  Setting both consecutively
                distArray[j][i] = dist;     //  -------------"------------

                //System.out.print(" The distance between the facility "+facility[i]+" and "+customer[j]+" is "+distArray[i][j]);  // Debug only
            }

//            System.out.println(); // Debug only
        }
        return distArray;
    }
}
/* public float maxDist(float[][] distTable, int noc) {
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
 }*/
