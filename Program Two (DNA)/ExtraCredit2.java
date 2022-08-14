/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extracredit2;

import java.util.Scanner;
import java.io.File;
import java.io.PrintStream;

public class ExtraCredit2 {

   
    public static void main(String[] args) {
    final int MAXSLOTS = 1000;
        //defining how many maximum size of arrays
        int count = 0;
        String RNAStrand, codonX, aminoAcidX = null, proteinChain = null;
        //These strings will be defined later 
        //aminoAcidX and proteinChain are undefined hence the name null
        boolean isValid;
        //checks if RNA strand is valid 
        
        Scanner sc1 = new Scanner(new File("RNAStrand.txt"));
        //Scanner object will read in from RNA strand file 
        
        String[] codon = new String[MAXSLOTS];
        //string array is created for codon
       
        String[] aminoAcid = new String[MAXSLOTS];
        //Sting array is created for amino acids 
        
        count = readCodonTable(codon, aminoAcid);
        //Count will equal the method of the readCodonTable which will read from a table seeing what amino acid a codon pairs to 
        
        sort(codon, aminoAcid, count);
        //This sort method simply sorts the arrays 
        
        RNAStrand = sc1.next();
        //reading in next part of scanning 
        
        isValid = isValidRNA(RNAStrand);
        //checking if the RNA Strand is valid 
        
        for (int i = 0; i < RNAStrand.length()-3; i++) {
            //for loop exists for from the start ignoring the last three tokens in the RNA Strand
            
            for (int n = i + 3; n < RNAStrand.length()-3;) {
                //For loop for codons in groups of three 
                codonX = RNAStrand.substring(i, n);
                
                //CodonX be starts with AUG
                if (codonX.equals("AUG")) {
                    codonX = codonLookup(codonX, codon, aminoAcid, count);
                    System.out.println(codonX);
                    //when to stop 
                    while (!codonX.equals("STOP")) {
                        i = n;
                        n = i + 3;
                        if(n>RNAStrand.length()-3) break;
                        
                        codonX = RNAStrand.substring(i, n);
                        codonX = codonLookup(codonX, codon, aminoAcid, count);
                        
                        //Codon looks up which amino acid the condon corresponds to
                        if (codonX.equals("START")) {
                            codonX = "M";
                            System.out.println(codonX);
                        } else {
                            System.out.println(codonX);

                        }
                    }

                }
                break;
            }

        }
    }
    //This method is reading in the codon table that pairs the codon to the amino acid 
    public static int readCodonTable(String[] codon, String[] aminoAcid) throws
            Exception {
        Scanner sc = new Scanner(new File("Table2.txt"));
        int count = 0;
        while (sc.hasNext()) {
            codon[count] = sc.next();
            aminoAcid[count] = sc.next();
            count++;
        }
        return count;
    }
    
    //This method sorts codon array and the aminoAcid array
    public static void sort(String[] codon, String[] aminoAcid, int count) {
        String holdC, holdA;
        boolean switched = true;
        for (int pass = 0; pass < count - 1 && switched; pass++) {
            switched = false;
            for (int j = 0; j < count - pass - 1; j++) {
                char c1a, c1b, c2a, c2b, c3a, c3b;
                c1a = codon[j].charAt(0);
                c1b = codon[j + 1].charAt(0);
                c2a = codon[j].charAt(1);
                c2b = codon[j + 1].charAt(1);
                c3a = codon[j].charAt(2);
                c3b = codon[j + 1].charAt(2);
                if (c1a > c1b) {

                    switched = true;
                    holdC = codon[j];
                    codon[j] = codon[j + 1];
                    codon[j + 1] = holdC;
                    holdA = aminoAcid[j];
                    aminoAcid[j] = aminoAcid[j + 1];
                    aminoAcid[j + 1] = holdA;

                }
                if (c1a == c1b && c2a > c2b) {
                    switched = true;
                    holdC = codon[j];
                    codon[j] = codon[j + 1];
                    codon[j + 1] = holdC;
                    holdA = aminoAcid[j];
                    aminoAcid[j] = aminoAcid[j + 1];
                    aminoAcid[j + 1] = holdA;

                }
                if (c1a == c1b && c2a == c2b && c3a > c3b) {
                    switched = true;
                    holdC = codon[j];
                    codon[j] = codon[j + 1];
                    codon[j + 1] = holdC;
                    holdA = aminoAcid[j];
                    aminoAcid[j] = aminoAcid[j + 1];
                    aminoAcid[j + 1] = holdA;
                }

            }
        }

    }
    //This method will ensure what input that the RNA can recognize 
    //Four characters A for the nitrogenous base adenine, C for cytosine, G for guanine, and U for Uracil
    public static boolean isValidRNA(String RNAStrand) {
        char c = 0;
        for (int n = 0; n < RNAStrand.length(); n++) {
            c = RNAStrand.charAt(n);
        }
        if (c == 'A' || c == 'C' || c == 'G' || c == 'U') {

        } else {
            return false;
        }
        return true;

    }
    
    //This method will look up the codon in table 2 and see which amino acid it corresponds to 
    public static String codonLookup(String codonX, String[] codon, String[] 
            aminoAcid, int count) {
        for (int i = 0; i < count; i++) {
            if (codon[i].equals(codonX)) {
                return aminoAcid[i];
            }
        }
      return "New Protein";
    }

}

