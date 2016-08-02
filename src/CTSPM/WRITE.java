package CTSPM;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

class WRITE {

    public void writeFile(float[][] distArray, int noc) {
        try {
            File file = new File("Results\\pathMatrix280.tsp"); // Creating file for storing path for other applications
            // Will not be used for this program

            if (!file.exists()) {               // Creates the specified file if already not there
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file, true);  // writing,  keyword true is for appending
            PrintWriter out = new PrintWriter(fw);       // Reqd by code

            for (int i = 0; i < noc; i++) {                  // Actually printed material
                for (int j = 0; j < noc; j++) {
                    out.print(distArray[i][j] + " ");
                }
                out.println();
            }

            out.close();  // Reqd by code
            fw.close();   // ------"-----
        } catch (IOException ex) {
            Logger.getLogger(WRITE.class.getName()).log(Level.SEVERE, null, ex);
        }
    } // End of function write

    public void delFile1() { //Self explanatory
        File file = new File("Results/pathMatrix280.tsp");

        if (file.exists()) {
            file.delete();
        }
    } // End of function delFile
    
    public void delFile2() { //Self explanatory
        File file = new File("Results\\Matlab Report Files\\ctspreport280.txt");

        if (file.exists()) {
            file.delete();
        }
    } // End of function delFile
    
    static void writeResult(int[][] popTable, int popSize, int noc, int gen){
        
        float max = max(popTable, popSize, noc)/1000;
        float min = min(popTable, popSize, noc)/1000;
        float avg = avg(max, min);
        
        try {
            File file = new File("Results\\Matlab Report Files\\ctspreport" + noc + ".txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file, true);
            PrintWriter out = new PrintWriter(fw);
            out.print(gen + "             " + max + "                  " + avg + "                   " + min);
            out.println();
//            System.out.println(gen + "             " + max + "                  " + avg + "                   " + min + "\n");
            out.close();
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    } // End of function write result
    
    private static int max(int[][] popTable, int popSize, int noc) {
        int max;

        max = popTable[0][noc];
        for (int i = 1; i < popSize; i++) {
            if (max < popTable[i][noc]) {
                max = popTable[i][noc];
            }
        }

        return max;
    } // End of function max

    private static int min(int[][] popTable, int popSize, int noc) {
        int min;

        min = popTable[0][noc];
        for (int i = 1; i < popSize; i++) {
            if (min > popTable[i][noc]) {
                min = popTable[i][noc];
            }
        }

        return min;
    } // End of function min

    private static float avg(float max, float min) {
        return (max + min) / 2;
    } // End of function avg
}
