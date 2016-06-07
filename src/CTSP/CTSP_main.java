package CTSP;

import java.io.IOException;
import javax.swing.JOptionPane;

public class CTSP_main {

    public static void main(String[] args) throws IOException {
//        int noc     = Integer.parseInt((String) JOptionPane.showInputDialog(null,"Enter the no. of cities","No. of cities",3,null,null,"280"));
//        int dist     = Integer.parseInt((String) JOptionPane.showInputDialog(null,"Enter facility cover distance","Distance",3,null,null,"50"));
//        int popSize     = Integer.parseInt((String) JOptionPane.showInputDialog(null,"Enter population size","Population",3,null,null,"100"));
//        float crossRate = Float.parseFloat((String) JOptionPane.showInputDialog(null, "Enter the crossover rate", "Crossover Rate", 3, null, null, 0.8));
        int noc = 280;     //Specific to WORKING PROJECT FILE
        int dist = 50;     //Specific to WORKING PROJECT FILE
        int popSize = 150;   //Specific to WORKING PROJECT FILE

        int[][] coorTable   = new COORDINATE().coorArray(noc);
        float[][] distTable = new DIST().distArray(coorTable, noc);

//        new WRITE().delFile();
//        new WRITE().writeFile(distTable, noc);
        
        int[][] coverTable  = new COVER_MATRIX().coverArray(distTable, noc, dist);
        
        System.out.println("----------------------Initial Population----------------------");
        int[][] popTable    = new POPULATION().popArray3Closest(popSize, noc, coverTable, dist, distTable);
        popTable            = new FITNESS().calcFitness(popTable, distTable, noc, popSize);
        
        int[][] selectTable = new SELECTION().select(popSize, noc, popTable);
        
        System.out.println();
        System.out.println("----------------------Crossover Population----------------------");
        int[][] crossTable  = new CROSSOVER().crossBest(selectTable, popTable, popSize, noc);  // 50% cross with best, rest random
        crossTable          = new CROSSOVER().crossRandom(crossTable, popTable, selectTable, popSize, noc);
        crossTable          = new FITNESS().calcFitness(crossTable, distTable, noc, popSize);
        
        System.out.println();
        System.out.println("----------------------Feasible Crossover Population----------------------");
        crossTable          = new MAKE_FEASIBLE().makeFeasible(crossTable, coverTable, distTable, popSize, noc);
        crossTable          = new FITNESS().calcFitness(crossTable, distTable, noc, popSize);
        
        // <editor-fold defaultstate="collapsed" desc="Printing popFit crossFit and min">
        //System.out.println("");
        //int min1 = popTable[0][noc];
        //for(int i=0;i<popSize;i++){
        //    System.out.println(popTable[i][noc]+" "+popTable[i][noc+1]+" ");
        //    if(popTable[i][noc] < min1)
        //        min1 = popTable[i][noc];
        //}
        //
        //System.out.println("");
        //int min = crossTable[0][noc];
        //for(int i=0;i<popSize;i++){
        //    System.out.println(crossTable[i][noc]+" "+crossTable[i][noc+1]+" ");
        //    if(crossTable[i][noc] < min)
        //        min = crossTable[i][noc];
        //}
        //System.out.println("");
        //System.out.println(min1+" "+min);
        // </editor-fold>
    }

}
