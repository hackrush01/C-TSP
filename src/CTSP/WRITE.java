package CTSP;

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

    public void delFile() { //Self explanatory
        File file = new File("Results/pathMatrix280.tsp");

        if (file.exists()) {
            file.delete();
        }
    } // End of function delFile
}
