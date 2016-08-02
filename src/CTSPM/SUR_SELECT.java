package CTSPM;

import java.util.Arrays;
import java.util.Comparator;

class SUR_SELECT {

    @SuppressWarnings("Convert2Lambda")
    public int[][] survivor(int[][] popArray, int[][] _2OptPool) {
        int popSize = popArray.length;
        int noc = popArray[0].length - 2;
        int grpA = (popSize - 1) / 2;
        int grpB = (popSize - 1) * 8 / 10;
        int[][] survivorPool = new int[popSize][noc + 2];

        Arrays.sort(popArray, new Comparator<int[]>() {
            @SuppressWarnings("override")
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[noc], b[noc]);
            }
        });

        Arrays.sort(_2OptPool, new Comparator<int[]>() {
            @SuppressWarnings("override")
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[noc], b[noc]);
            }
        });

        int i = 0;
        for (int j = 0; i < grpA / 2; i++, j++) {
            System.arraycopy(popArray[j], 0, survivorPool[i], 0, noc + 1);
        }

        for (int j = 0; i <= grpA; i++, j++) {
            System.arraycopy(_2OptPool[j], 0, survivorPool[i], 0, noc + 1);
        }

        for (int j = grpA + 1; i < (grpA + grpB) / 2; i++, j++) {
            System.arraycopy(popArray[j], 0, survivorPool[i], 0, noc + 1);
        }

        for (int j = grpA + 1; i <= grpB; i++, j++) {
            System.arraycopy(_2OptPool[j], 0, survivorPool[i], 0, noc + 1);
        }

        for (int j = grpB + 1; i < (grpB + popSize) / 2; i++, j++) {
            System.arraycopy(popArray[j], 0, survivorPool[i], 0, noc + 1);
        }

        for (int j = grpB + 1; i < popSize; i++, j++) {
            System.arraycopy(_2OptPool[j], 0, survivorPool[i], 0, noc + 1);
        }
        
        for (i = 0; i < popSize; i++) {
            for (int j = 0; survivorPool[i][j] != -1; j++) {
                System.out.print(survivorPool[i][j] + " ");
            }
            System.out.println("         "+ survivorPool[i][noc]);
        }

        return survivorPool;
    } // End of survivor function
}
