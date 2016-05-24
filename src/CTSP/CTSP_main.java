package CTSP;

import java.io.IOException;
import javax.swing.JOptionPane;

public class CTSP_main {

    public static void main(String[] args) throws IOException {
//        int noc     = Integer.parseInt((String) JOptionPane.showInputDialog(null,"Enter the no. of cities","No. of cities",3,null,null,"280"));
//        int dist     = Integer.parseInt((String) JOptionPane.showInputDialog(null,"Enter facility cover distance","Distance",3,null,null,"50"));
//        int popSize     = Integer.parseInt((String) JOptionPane.showInputDialog(null,"Enter population size","Population",3,null,null,"100"));
        int noc = 280;     //Specific to WORKING PROJECT FILE
        int dist = 50;     //Specific to WORKING PROJECT FILE
        int popSize = 1;   //Specific to WORKING PROJECT FILE

        int[][] coorTable = new COORDINATE().coorArray(noc);
        float[][] distTable = new DIST().distArray(coorTable, noc);

//        new WRITE().delFile();
//        new WRITE().writeFile(distTable, noc);

        int[][] coverTable = new COVER_MATRIX().coverArray(distTable, noc, dist);

        new POPULATION().popArray3Closest(popSize, noc, coverTable, dist, distTable);
    }

}
