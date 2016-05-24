package CTSP;

class PrintArr_Debug {

    public static void printarr(Object[][] arr) {
        for (int i = 0; i < 280; i++) {
            for (int j = 0; j < 280; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void printarr(int[][] arr, int printIndex) {
            int j = 0;
            for (; j < 280 && arr[printIndex][j] != -1; j++) {
                System.out.print(arr[printIndex][j] + " ");
            }
            System.out.print(arr[printIndex][j]);
            System.out.println();
    }

    public static void printarr(int[][] arr) {
        for (int i = 0; i < 280; i++) {
            int j = 0;
            for (; j < 280 && arr[i][j] != -1; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.print(arr[i][j]);
            System.out.println();
        }
    }

    public static void printarr(float[][] arr) {
        for (int i = 0; i < 280; i++) {
            int j = 0;
            for (; j < 280 && arr[i][j] != -1; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.print(arr[i][j]);
            System.out.println();
        }
    }

    public static void printarr(int[] arr, int range) {
        for (int i = 0; i < range + 1; i++) {
//            System.out.print(i+' ');  
//            System.out.println();
            System.out.print(arr[i] + "  ");
        }
        System.out.println();
    }
}
