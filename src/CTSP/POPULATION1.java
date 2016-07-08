/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTSP;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;

/**
 *
 * @author Siba : This is used to instantiate and for an initial population
 */

public class POPULATION1 {

    int[][] intialize(int[] facility, int[] customers, int depot, float[][] facility_cust_dist, float[][] facility_facility_dist) {
        int popSize = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Enter population size", "Population", 3, null, null, "100"));
        int[][] intializzed_table = new int[popSize][facility.length];
        int[][] coveredCust = findCoveredCustomer(facility, customers, facility_cust_dist, facility_facility_dist);
        int[][] sorted_facility = sort_facility(facility, facility_facility_dist);
        for (int i = 0; i < popSize; i++) {

            int randStartFacility = (int) (Math.random() * facility.length);
            System.out.println("\n The " + i + "th chromosome on the random point " + randStartFacility + " is \n");
            for (int j = 0; j < facility.length; j++) {
                intializzed_table[i][j] = facility[sorted_facility[randStartFacility][j]];
                System.out.print(" - " + intializzed_table[i][j]);
            }
            System.out.println(" ");
        }
        return intializzed_table;
    }

    int[][] sort_facility(int[] facility, float[][] facility_facility_dist) {
        int[][] sortedFacility = new int[facility.length][facility.length];

        for (int i = 0; i < facility.length; i++) {
            int[] coveredStatus = new int[facility.length];

            System.out.println("First facility point is " + i + " and the facility is " + facility[i]);
            for (int l = 0; l < facility.length; l++) {
                coveredStatus[l] = 0;
            }
            coveredStatus[i] = 1;
            for (int j = 1; j < facility.length; j++) {
                sortedFacility[i][0] = i;
                float min = 10000;
                int min_point = j;
                for (int k = 0; k < facility.length; k++) {
                    if (i == k || coveredStatus[k] == 1) {
                        // System.out.println("Continue dropped in "+k);
                        continue;
                    } else {
                        if (facility_facility_dist[i][k] < min) {
                            min = facility_facility_dist[i][k];
                            // System.out.println("min_point is "+k);
                            min_point = k;
                        }
                    }
                }
                //System.out.println("min point after k is "+min_point);
                sortedFacility[i][j] = min_point;
                coveredStatus[min_point] = 1;

            }
        }

        for (int i = 0; i < facility.length; i++) {
            System.out.println("\npoints are\n");
            for (int j = 0; j < facility.length; j++) {
                System.out.print("  " + sortedFacility[i][j]);
            }
            System.out.println("\nfacilities for " + i + " are\n");
            for (int k = 0; k < facility.length; k++) {
                System.out.print("  " + facility[sortedFacility[i][k]]);

            }
        }

        return sortedFacility;
    }

    //To find the number of customers covered by facilities within a range of radius dist

    int[][] findCoveredCustomer(int[] facility, int[] customers, float[][] facility_cust_dist, float[][] facility_facility_dist) {
        int dist = 50;
        int[][] coveredCust = new int[facility.length][facility.length];
        int counter;
        for (int i = 0; i < facility.length; i++) {
            counter = 0;
            for (int j = 0; j < customers.length; j++) {      // Iterating coverArray (actual filling and condition check)
                if (facility_cust_dist[i][j] <= dist) {     // Condition check for distance and INCLUDING itself
                    coveredCust[i][counter] = j;         // Giving city INDEX
                    counter++;
                }
            }
            coveredCust[i][counter] = -1;
        }
        int k = 0;
        for (int i = 0; i < facility.length; i++) {
            k = 0;
            // System.out.println("The customers covered by "+facility[i]);
            while (coveredCust[i][k] != -1) {
                //System.out.print(" "+coveredCust[i][k]);
                k++;
            }
            // System.out.println(" ");
        }
        return coveredCust;
    }

    /*int findRand(int[] facility)
     {
     int facility_status[] =new int[facility.length];
     int[] newFacility_stack = new int[facility.length];
     ArrayList<Integer> rand_list = new ArrayList<Integer>();
         
     for (int i=0; i<facility.length; i++) {
     rand_list.add(new Integer(facility[i])); //creatimg random list
     }
     Collections.shuffle(rand_list); // creating random numbers 0 to noc
       
     return rand_list.get(0);
     }
     */
}
