package CTSP;

import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;

public class CTSP_main1 {

    public static void main(String[] args) throws IOException {
//       
        int noc = 280;    
        int dist = 50;     
        int popSize = 150;   
        float mutateRate = 0.1f;
        int N = popSize / 10; 
        int gen = 0;
        int depot = -1;
        
        int[][] coorTable = new COORDINATE().coorArray(noc);
        float[][] distTable = new DIST().distArray(coorTable, noc);

        new WRITE().delFile2 ();
//        new WRITE().writeFi le(distTable, noc);
        
        System.out.println("FInding the Depot, Facuility and Customers");
        ArrayList<Integer> rand_list = new ArrayList<Integer>();
         int no_facility = (noc*30)/100;// setting the number iof facility
         
         int no_customer = noc - (no_facility+1);//setting the number of customers
       
          for (int i=0; i<noc; i++) {
	       rand_list.add(new Integer(i)); //creatimg random list
	  }
         Collections.shuffle(rand_list); // creating random numbers 0 to noc
         depot = rand_list.get(0); //setting depot vertex
         System.out.println("Depot is "+depot);
         System.out.println("Number of facilities are "+no_facility);
         System.out.println("Number of customers are "+no_customer);
         int[] facility =new int[no_facility]; //settiong facility array
	 for (int i=0; i<no_facility; i++) { //inserting facility points
        	facility[i] = rand_list.get(i+1);
	 }
        int[] customer = new int[no_customer]; //setting number of customers
        int count_cust = no_facility+1; 
       	for(int i = 0;i<no_customer;i++) //inserting customers into array
        {
         //System.out.println("customer list "+count_cust);
	 customer[i]=rand_list.get(count_cust);
         count_cust =count_cust +1;
        }
       /* System.out.println("The depot is vertex number "+depot+" and the coordinates are "+coorTable[depot][0]+","+coorTable[depot][1]);
        System.out.println("The facility points are ");
        for(int i = 0;i < no_facility;i++)
        {
            System.out.print("  "+facility[i]);
        }
        System.out.println(" ");
        System.out.println("The Customers are ");
        for(int i = 0;i < no_customer;i++)
        {
            System.out.print("  "+customer[i]);
        }
        */
        
        float[][] facility_cust_dist = new DIST1().distFacCustArray(coorTable,depot,facility, customer, noc);
        float[][] facility_facility_dist = new DIST1().distFacCustArray(coorTable, facility);
        
       /* System.out.println("The distance between facility and customer is");
        for(int i = 0;i < no_facility;i++)
        {
            for(int j = 0;j < no_customer;j++)
            {
             System.out.println( facility[i]+" and "+customer[j]+" is "+facility_cust_dist[i][j]);
            }
        }
        */
        int[][] initialized_table = new POPULATION1().intialize(facility,customer, noc, facility_cust_dist,facility_facility_dist);
       
    }
    
}
    
       /* 
        
        int[][] coverTable = new COVER_MATRIX().coverArray(distTable, noc, dist);

        System.out.println("+---------------------Initial Population---------------------+");
        int[][] popTable = new POPULATION().popArray3Closest(popSize, noc, coverTable, dist, distTable);
        popTable = new FITNESS().calcFitness(popTable, distTable, noc, popSize);
        
        int[] bestGene = bestGene(popTable, popSize, noc);

    } // End of main function
*/
    