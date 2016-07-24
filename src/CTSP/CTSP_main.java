package CTSP;

import java.io.IOException;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class CTSP_main {

    public static void main(String[] args) throws IOException {
//        int noc     = Integer.parseInt((String) JOptionPane.showInputDialog(null,"Enter the no. of cities","No. of cities",3,null,null,"280"));
//        int dist     = Integer.parseInt((String) JOptionPane.showInputDialog(null,"Enter facility cover distance","Distance",3,null,null,"50"));
//        int popSize     = Integer.parseInt((String) JOptionPane.showInputDialog(null,"Enter population size","Population",3,null,null,"100"));
//        float mutateRate = Float.parseFloat((String) JOptionPane.showInputDialog(null, "Enter the mutation rate", "Mutation Rate", 3, null, null, 0.03));
        int noc = 280;     //Specific to WORKING PROJECT FILE
        int dist = 50;     //Specific to WORKING PROJECT FILE
        int popSize = 150;   //Specific to WORKING PROJECT FILE
        float mutateRate = 0.1f;
        int N = popSize / 10; 
        int gen = 0;

        int[][] coorTable = new COORDINATE().coorArray(noc);
        float[][] distTable = new DIST().distArray(coorTable, noc);

        new WRITE().delFile2 ();
//        new WRITE().writeFi le(distTable, noc);
        int[][] coverTable = new COVER_MATRIX().coverArray(distTable, noc, dist);

        System.out.println("+---------------------Initial Population---------------------+");
        int[][] popTable = new POPULATION().popArray3Closest(popSize, noc, coverTable, dist, distTable);
        popTable = new FITNESS().calcFitness(popTable, distTable, noc, popSize);
        
        int[] bestGene = bestGene(popTable, popSize, noc);
        int improveCounter = 0;
        int resets = 0;

        for (; improveCounter < 100; improveCounter++, gen++) {
            int bestDist = bestGene[noc];

            int[][] selectTable = new SELECTION().select(popSize, noc, popTable);

            System.out.println();
            System.out.println("+---------------------Crossover Population---------------------+");
            int[][] crossTable = new CROSSOVER().crossBest(selectTable, popTable, popSize, noc);  // 10% cross with best, 10% random
            crossTable = new CROSSOVER().crossRandom(crossTable, popTable, selectTable, popSize, noc);
            crossTable = new CROSSOVER().fillRestCross(crossTable, selectTable, popSize, noc);    // Rest is filled w/o crossing
            crossTable = new FITNESS().calcFitness(crossTable, distTable, noc, popSize);

            System.out.println();
            System.out.println("+---------------------Feasible Crossover Population---------------------+");
            crossTable = new MAKE_FEASIBLE().makeFeasible(crossTable, coverTable, distTable, popSize, noc);
            crossTable = new FITNESS().calcFitness(crossTable, distTable, noc, popSize);

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
            System.out.println();
            System.out.println("+---------------------Mutated Population---------------------+");
            int[][] mutateTable = new MUTATION().mutate(crossTable, mutateRate, popSize);
            mutateTable = new FITNESS().calcFitness(mutateTable, distTable, noc, popSize);

            System.out.println();
            System.out.println("+---------------------2-Opt L.S. Population---------------------+");
            int[][] _2OptTable = new TWO_OPT().twoOpt(mutateTable, distTable, popSize, N);  // Only N random genes will be local searched
            _2OptTable = new FITNESS().calcFitness(_2OptTable, distTable, noc, popSize);
//            popTable = new FITNESS().calcFitness(_2OptTable, distTable, noc, popSize);
            
            System.out.println();
            System.out.println("+---------------------Drop & Add L.S. Population---------------------+");
            int[][] dropAndAddTable = new DROP_AND_ADD().dropAndAdd(_2OptTable, distTable, coverTable, popSize, N);  // Only N random genes will be local searched
            popTable = new FITNESS().calcFitness(dropAndAddTable, distTable, noc, popSize);

            System.out.println();
            System.out.println("+---------------------Surviving Population---------------------+");
//            int[][] survivorTable = new SUR_SELECT().survivor(popTable, _2OptTable);
//            popTable = new FITNESS().calcFitness(survivorTable, distTable, noc, popSize);
            
            //NOT DOING SURVIVORS SELECION AS THIS RESULTS IN LESS VARIATIONS THEREBY INFITIE LOOP
            
            
            for (int i = 0; i < popSize; i++) {
                for (int j = 0; popTable[i][j] != -1; j++) {
                    System.out.print(popTable[i][j] + " ");
                }
                
                System.out.println("         "+ popTable[i][noc]);
            }
            
            WRITE.writeResult(popTable, popSize, noc, gen);
           
            System.out.println(gen + "    " + improveCounter);
            
            int[] newBestGene = bestGene(popTable, popSize, noc);
            int newBestDist = newBestGene[noc];
            
            if (newBestDist < bestDist) {
                improveCounter = 0;
                resets++;
                System.arraycopy(newBestGene, 0, bestGene, 0, noc + 2);
            }

        }
        System.out.println("Resets: " + resets);
    } // End of main function

    private static int[] bestGene(int[][] Array, int popSize, int noc) {
        int[] bestGene = new int[noc + 2];
        System.arraycopy(Array[0], 0, bestGene, 0, noc + 2);
        int bestFit = bestGene[noc];

        for (int i = 1; i < popSize; i++) {
            if (Array[i][noc] < bestFit) {
                bestFit = Array[i][noc];
                System.arraycopy(Array[i], 0, bestGene, 0, noc + 2);
            }
        }
        return bestGene;
    }
}
