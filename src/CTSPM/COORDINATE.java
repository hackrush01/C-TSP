package CTSPM;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class COORDINATE {

    public int[][] coorArray(int noc) throws IOException {
        int[][] coorArray = new int[noc][2];

        try {
            File file = new File("Results\\Dist Files\\a280.tsp");  // Reading the file by its name
            FileReader fr = new FileReader(file);           // Starting file reader
            BufferedReader br = new BufferedReader(fr);     // Bufferring it ↑

            int j;
            String line;

            for (j = 0; j < 6; j++) {                    // For skipping text lines in starting
                line = br.readLine();
            }

            for (j = 0; !"EOF".equals(line = br.readLine().trim()); j++) {   //Putting in cost array // Last line is always EOF
                String[] chunk = line.split(" +");

                for (int i = 0; i < 2; i++) {
                    coorArray[j][i] = Integer.parseInt(chunk[i + 1]); //Putting in dist array
//                    System.out.print(coorArray[j][i] + "  ");  // Printing for debugging (takes time, so commented)
                }
//                System.out.println(); // Formatting // takes time, so commented
            }

            br.close(); // Required by code
            fr.close(); // Required by code
        } catch (FileNotFoundException ex) {
            Logger.getLogger(COORDINATE.class.getName()).log(Level.SEVERE, null, ex);
        }

        return coorArray;
    } // End of function distMatrix
}
