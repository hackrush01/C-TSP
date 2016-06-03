package CTSP;

class CROSSOVER {

    public int[][] crossBest(int[][] selectPool, int[][] popArray, int popSize, int noc) {
        // <editor-fold defaultstate="collapsed" desc="Variables">
        int[][] crossPool = new int[popSize][noc + 2];
        int bestGene_fit = Integer.MAX_VALUE;
        int bestGene_index = -1;
        int bestGene_length = 0;
        int crossPoint = 0;  // Outside of for loop as it will be genrated every 2nd time
        int parent2 = 0; // Outside of for loop as it will be genrated every 2nd time
        int parent2Gene_length = 0; // Outside of for loop as it will be genrated every 2nd time
        int crossGene_length;
        int noOfGenes = popSize / 2;  // Change MANUALLY for variation, make similar changes to below function crossRandom
        // </editor-fold>

        // For finding index of the best gene, only from selected genes
        for (int i = 0; i < popSize; i++) {
            if (selectPool[i][noc] < bestGene_fit) {
                bestGene_fit = selectPool[i][noc];
                bestGene_index = selectPool[i][noc + 1];
            }
        }

        // Finding the tour length of the best gene
        for (int i = 0; popArray[bestGene_index][i] != -1; i++) {
            bestGene_length = i;
        }

//        System.out.println(bestGene_fit + " " + bestGene_index + " " + bestGene_length);    // Debug Only

        // funtion for crossing
        for (int i = 0; i < noOfGenes; i++) {

            // Creating even numbered children(Some kind of programatically made test tube baby !? :-P)
            if (i % 2 == 0) {

                // <editor-fold defaultstate="collapsed" desc="Parent 2 Selection">
                //Random parent 2
                parent2 = selectPool[(int) (Math.random() * popSize)][noc + 1]; //Random integer(A) is selected from 0 to popSize
//              System.out.println(parent2);  // Debug Only                     //and index stored in selecPop@(noc+1) at A is parent2

                //parent 2 not same as parent 1
                for (; parent2 == bestGene_index;) {
                    parent2 = selectPool[(int) (Math.random() * popSize)][noc + 1];
//                  System.out.println(parent2);  // Debug Only
                }

//                System.out.println(parent2); // Debug Only
                // </editor-fold>

                // Calculating length of parent2 gene for crossover(will be done on the basis of smaller of two parents)
                // <editor-fold>
                for (int l = 0; popArray[parent2][l] != -1; l++) {
                    parent2Gene_length = l;
                }

                if (bestGene_length < parent2Gene_length) {
                    crossGene_length = bestGene_length;
                } else {
                    crossGene_length = parent2Gene_length;
                }
                // </editor-fold>

                // Random crossover point
                crossPoint = (int) (Math.random() * crossGene_length);
//                System.out.println(crossPoint); // Debug Only
                int j = 0;

                // Filling the crossPool till the crossPoint with bestGene 
                for (; j < crossPoint; j++) {
                    crossPool[i][j] = popArray[bestGene_index][j];
//                    System.out.print(crossPool[i][j] + " ");  // Debug Only
                }

                int crossPoolFillPoint = j; // crossPool will start to get filled from here

                // Filling the crossPool after the crossPoint upto parent2 not = (-1)
                for (; popArray[parent2][j] != -1; j++) {
                    int flag = 0;   // Flag; to check if the element of parent2 to be put in crossPool is repeated
                    for (int k = 0; k < crossPoint; k++) {      // Loop to check for repeated elements
                        if (popArray[parent2][j] == crossPool[i][k]) {
                            flag = 1;       // If element matches previous element flag is set to 1 and the loop is broken
                            //System.out.print(" FLAG "); // Strictly for debugging
                            break;
                        }
                    }

                    if (flag == 0) {    // However if flag is 0, the element(from parent2) is put in crossPool
                        crossPool[i][crossPoolFillPoint] = popArray[parent2][j];
//                        System.out.print(crossPool[i][crossPoolFillPoint] + " ");  // Debug Only
                        crossPoolFillPoint++;       // Increasing cross fill point on successful insertion
                    }
                }
                crossPool[i][crossPoolFillPoint] = -1;
            } else {    // For odd i
                int j = 0;

                // Filling the crossPool till the crossPoint with parent2 
                for (; j < crossPoint; j++) {
                    crossPool[i][j] = popArray[parent2][j];
//                    System.out.print(crossPool[i][j] + " ");  // Debug Only
                }

                int crossPoolFillPoint = j; // crossPool will start to get filled from here

                // Filling the crossPool after the crossPoint upto best_parent not = (-1)
                for (; popArray[bestGene_index][j] != -1; j++) {
                    int flag = 0;   // Flag to check if the element of bestGene_index to be put in crossPool is repeated
                    for (int k = 0; k < crossPoint; k++) {      // Loop to check for repeated elements
                        if (popArray[bestGene_index][j] == crossPool[i][k]) {
                            flag = 1;       // If element matches previous element flag is set to 1 and the loop is broken
                            //System.out.print(" FLAG "); // Strictly for debugging
                            break;
                        }
                    }

                    if (flag == 0) {    // However if flag is 0, the element(from bestGene_index) is put in crossPool
                        crossPool[i][crossPoolFillPoint] = popArray[bestGene_index][j];
//                        System.out.print(crossPool[i][crossPoolFillPoint] + " ");  // Debug Only
                        crossPoolFillPoint++;       // Increasing cross fill point on successful insertion
                    }
                }
                crossPool[i][crossPoolFillPoint] = -1;
            }
//            System.out.println(); // Debug Only
        }
        return crossPool;
    } // End of function for creating crossPool from best genes;

    public int[][] crossRandom(int[][] crossPool, int[][] popArray, int[][] selectPool, int popSize, int noc) {
        // <editor-fold defaultstate="collapsed" desc="Variables">
        int crossPoint = 0;  // Outside of for loop as it will be genrated every 2nd time
        int parent1 = 0; // Outside of for loop as it will be genrated every 2nd time
        int parent1Gene_length = 0; // Outside of for loop as it will be genrated every 2nd time
        int parent2 = 0; // Outside of for loop as it will be genrated every 2nd time
        int parent2Gene_length = 0; // Outside of for loop as it will be genrated every 2nd time
        int crossGene_length = 0;
        int startIndex = popSize / 2; // (=noOfGenes) From previous function crossBest, change MANUALLY for variation
        // </editor-fold>
        // funtion for crossing
        for (int i = startIndex; i < popSize; i++) {

            // Creating even numbered children(Some kind of programatically made test tube baby !? :-P)
            if (i % 2 == 0) {

                // <editor-fold defaultstate="collapsed" desc="Parent 1 & 2 Selection">
                parent1 = selectPool[(int) (Math.random() * popSize)][noc + 1]; //Random parent 2
                parent2 = selectPool[(int) (Math.random() * popSize)][noc + 1];
//              System.out.println(parent2);  // Debug Only

                //parent 2 not same as parent 1
                for (; parent2 == parent1;) {
                    parent2 = selectPool[(int) (Math.random() * popSize)][noc + 1];
//                  System.out.println(parent2);  // Debug Only
                }

//                System.out.println(parent1 +" "+parent2); // Debug Only
                // </editor-fold>

                // Calculating length of parent2 gene for crossover(will be done on the basis of smaller of two parents)
                // <editor-fold>
                for (int l = 0; popArray[parent2][l] != -1; l++) {
                    parent2Gene_length = l;
                }
                
                for (int l = 0; popArray[parent1][l] != -1; l++) {
                    parent1Gene_length = l;
                }

                if (parent1Gene_length < parent2Gene_length) {
                    crossGene_length = parent1Gene_length;
                } else {
                    crossGene_length = parent2Gene_length;
                }
                // </editor-fold>

                // Random crossover point
                crossPoint = (int) (Math.random() * crossGene_length);
//                System.out.println(crossPoint); // Debug Only
                int j = 0;

                // Filling the crossPool till the crossPoint with parent1 
                for (; j < crossPoint; j++) {
                    crossPool[i][j] = popArray[parent1][j];
//                    System.out.print(crossPool[i][j] + " ");  // Debug Only
                }

                int crossPoolFillPoint = j; // crossPool will start to get filled from here

                // Filling the crossPool after the crossPoint upto parent2 not = (-1)
                for (; popArray[parent2][j] != -1; j++) {
                    int flag = 0;   // Flag; to check if the element of parent2 to be put in crossPool is repeated
                    for (int k = 0; k < crossPoint; k++) {      // Loop to check for repeated elements
                        if (popArray[parent2][j] == crossPool[i][k]) {
                            flag = 1;       // If element matches previous element flag is set to 1 and the loop is broken
                            //System.out.print(" FLAG "); // Strictly for debugging
                            break;
                        }
                    }

                    if (flag == 0) {    // However if flag is 0, the element(from parent2) is put in crossPool
                        crossPool[i][crossPoolFillPoint] = popArray[parent2][j];
//                        System.out.print(crossPool[i][crossPoolFillPoint] + " ");  // Debug Only
                        crossPoolFillPoint++;       // Increasing cross fill point on successful insertion
                    }
                }
                crossPool[i][crossPoolFillPoint] = -1;
            } else {    // For odd i
                int j = 0;

                // Filling the crossPool till the crossPoint with parent2 
                for (; j < crossPoint; j++) {
                    crossPool[i][j] = popArray[parent2][j];
//                    System.out.print(crossPool[i][j] + " ");  // Debug Only
                }

                int crossPoolFillPoint = j; // crossPool will start to get filled from here

                // Filling the crossPool after the crossPoint upto parent1 not = (-1)
                for (; popArray[parent1][j] != -1; j++) {
                    int flag = 0;   // Flag to check if the element of parent1 to be put in crossPool is repeated
                    for (int k = 0; k < crossPoint; k++) {      // Loop to check for repeated elements
                        if (popArray[parent1][j] == crossPool[i][k]) {
                            flag = 1;       // If element matches previous element flag is set to 1 and the loop is broken
                            //System.out.print(" FLAG "); // Strictly for debugging
                            break;
                        }
                    }

                    if (flag == 0) {    // However if flag is 0, the element(from parent1) is put in crossPool
                        crossPool[i][crossPoolFillPoint] = popArray[parent1][j];
//                        System.out.print(crossPool[i][crossPoolFillPoint] + " ");  // Debug Only
                        crossPoolFillPoint++;       // Increasing cross fill point on successful insertion
                    }
                }
                crossPool[i][crossPoolFillPoint] = -1;
            }
//            System.out.println(); // Debug Only
        }
        return crossPool;
    }
}
