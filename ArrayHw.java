/** 
 * @author Kashif Nabhan
 * @since 11/28/2021 
 * @version 1.0 
 * 
 * Description: This program intends to take an data file filled with famous
 * mathematicians and their numerical grades to four tests, and calculate
 * their averages, mode, median, and mean. Then we are going to drop
 * the lowest test score for each. To create this table of calculations, we 
 * first need to create six arrays, one to store the name of the mathematician
 * ,the second to store test1's score, the third to store test2's score, the 
 * fourth to store test3's score, the fifth to store test4's score, and the 
 * averages to store the averages. 
 *            This project starts with the package, explaining the name of 
 * the project down in camelcase. Next we need to import the 
 * java.util.Scanner class which imports and unites all the methods in the 
 * Scanner class from the library to this weather project. The main idea of 
 * this class is for the system to import the Scanner class, which will be 
 * necessary later for the computer to scan a data file,  holding the values 
 * of all the mathematicians names and their test scores for each exam. 
 * The same will be done by importing the java.io.PrintStream class which
 * connects the methods in the PrintStream class from the library to this 
 * array project. This class will allow us to export the output results of 
 * our project to a output.txt of our choice, named "output.txt. Lastly we
 * are also going to import the java.io.file class which allows us to import
 * a new data file and scan it using the Scanner class.
 *      The main method in this project acts almost like a port, and we will
 * have to invoke all our methods that we created later. Once the code from 
 * these our methods are linked to our main method, we have a full product 
 * that will print our results to our chosen output.txt file. 
 *      We also need to create other methods to calculate standard deviation,
 * mode, mean, and the medians for each of the exams.  Also, we need a method
 * that will sort the medians as well as one that will drop the lowest score,
 * denoted by an XXX. The data will be printed once with the names, test1, 
 * test2, test4, and the average numerical values for each of the 
 * corresponding names. Then the second time these values are printed the
 * averages will be order from largest to smallest, this will also end up
 * altering the order the names and test scores are in. Then the third time 
 * these values are printed the same as the second time. Then lastly the 
 * fourth time these values are printed,  show the averages with the lowest 
 * score dropped from largest to lowest. 
 *   
 * 
 */ 
package arrayhw; 

import java.io.PrintStream;
import java.util.Scanner;
import java.io.File;

 

public class ArrayHw { 
    
    public static void main(String[] args) throws Exception { 
        PrintStream ps = new PrintStream("output.txt"); 
        
        double meanTest1, meanTest2, meanTest3, meanTest4; 
     
        
        final int NUMBEROFSTUDENTS = 11; 
        
        int count; 
        
        double standardDevTest1, standardDevTest2, standardDevTest3, 
                standardDevTest4; 
        
        int modeTest1, modeTest2, modeTest3, modeTest4; 
        
        double medTest1, medTest2, medTest3, medTest4;
     
        String[] fullName = new String[NUMBEROFSTUDENTS]; 
        int[] test1 = new int[NUMBEROFSTUDENTS]; 
        int[] test2 = new int[NUMBEROFSTUDENTS]; 
        int[] test3 = new int[NUMBEROFSTUDENTS]; 
        int[] test4 = new int[NUMBEROFSTUDENTS]; 
        double[] averages = new double[NUMBEROFSTUDENTS]; 
        
        count = populateArray(fullName, test1, test2, test3, test4); 
        averages = calcAvg(count, test1, test2, test3, test4, averages); 
        printTable(ps, count, fullName, test1, test2, test3, test4, averages); 
        sort(averages, fullName, test1, test2, test3, test4, count); 
        printTable(ps, count, fullName, test1, test2, test3, test4, averages); 
        
        meanTest1 = mean(test1, count); 
        meanTest2 = mean(test2, count); 
        meanTest3 = mean(test3, count); 
        meanTest4 = mean(test4, count); 
        
        String[] nameArr = new String[NUMBEROFSTUDENTS]; 
        int[] test1Arr = new int[NUMBEROFSTUDENTS]; 
        int[] test2Arr = new int[NUMBEROFSTUDENTS]; 
        int[] test3Arr = new int[NUMBEROFSTUDENTS]; 
        int[] test4Arr = new int[NUMBEROFSTUDENTS]; 
        
        count = populateArray(nameArr, test1Arr, test2Arr, test3Arr, 
                test4Arr); 
        
        sortMed(test1Arr, count); 
        sortMed(test2Arr, count); 
        sortMed(test3Arr, count); 
        sortMed(test4Arr, count); 
        
        medTest1 = med(test1Arr, count); 
        medTest2 = med(test2Arr, count); 
        medTest3 = med(test3Arr, count); 
        medTest4 = med(test4Arr, count); 
        
        modeTest1 = mode(test1, count); 
        modeTest2 = mode(test2, count); 
        modeTest3 = mode(test3, count); 
        modeTest4 = mode(test4, count); 
        
        standardDevTest1 = sd(test1, count); 
        standardDevTest2 = sd(test2, count); 
        standardDevTest3 = sd(test3, count); 
        standardDevTest4 = sd(test4, count); 
        
        ps.println();
        
        
        printTable(ps, count, fullName, test1, test2, test3, test4, averages); 
        
        dropLowest(ps, count, fullName, test1, test2, test3, test4, 
                averages); 
        
        ps.println();
        ps.println("Tests\tMean\tMedian\tMode\tSd"); 
        ps.println();
        
        ps.printf("Test 1\t%.3f\t%.1f\t%d\t%.3f\n", meanTest1, medTest1, 
                modeTest1, standardDevTest1); 
        ps.printf("Test 2\t%.3f\t%.1f\t%d\t%.3f\n", meanTest2, medTest2, 
                modeTest2, standardDevTest2); 
        ps.printf("Test 3\t%.3f\t%.1f\t%d\t%.3f\n", meanTest3, medTest3, 
                modeTest3, standardDevTest3); 
        ps.printf("Test 4\t%.3f\t%.1f\t%d\t%.3f\n\n", meanTest4, 
                medTest4, modeTest4, standardDevTest4); 
    } 
    
    public static int populateArray(String[] fullName, int[] test1, int[] 
            test2, 
            int[] test3, int[] test4) throws Exception { 
        Scanner sc = new Scanner(new File("Mathematicianstest.txt")); 
        
        String firstName, lastName; 
        int count = 0; 
        
        while (sc.hasNext()) { 
            firstName = sc.next(); 
            lastName = sc.next(); 
            fullName[count] = firstName + " " + lastName; 
            test1[count] = sc.nextInt(); 
            test2[count] = sc.nextInt(); 
            test3[count] = sc.nextInt(); 
            test4[count] = sc.nextInt(); 
            count++; 
        } 
        return count; 
    } 
    
    public static double[] calcAvg(int n, int[] test1, int[] test2, 
            int[] test3, int[] test4, double[] averages) { 
        double sum, average; 
        
        for (int i = 0; i < n; i++) { 
            averages[i] = (test1[i] + test2[i] + test3[i] + test4[i]) / 4.0; 
        } 
        return averages; 
    } 
    
    public static void printTable(PrintStream ps, int k, String[] fullName, 
            int[] test1, int[] test2, int[] test3, int[] test4, 
            double[] averages) throws Exception { 
        
        ps.println("\nNames\t\t\tTest1\tTest2\tTest3\tTest4\tAverages\n" 
                 + "____\t\t\t_____\t_____\t_____\t_____\t_______\n"); 
        
        for (int i = 0; i < k; i++) { 
            ps.printf("%18s\t%d\t%d\t%d\t%d\t%.2f\t\n", fullName[i], test1[i], 
                test2[i], test3[i], test4[i], averages[i]); 
        } 
        for (int i = 0; i < 36; i++) { 
            ; 
        } 
    } 
    
    public static void sort(double[] averages, String[] fullName, int[] test1, 
            int[] test2, int[] test3, int[] test4, int r) { 
        double hold1;
        int i, pass, hold3, hold4, hold5, hold6;
        boolean switched = true; 
        String hold2; 
        
        for (pass = 0; pass < r - 1 && switched; pass++) { 
            
            switched = false; 
            
            for (i = 0; i < r - pass - 1; i++) { 
                if (averages[i] < averages[i + 1]) { 
                    switched = true; 
                    hold1 = averages[i]; 
                    averages[i] = averages[i + 1]; 
                    averages[i + 1] = hold1; 
                    
                    hold2 = fullName[i]; 
                    fullName[i] = fullName[i + 1]; 
                    fullName[i + 1] = hold2; 
                    
                    hold3 = test1[i]; 
                    test1[i] = test1[i + 1]; 
                    test1[i + 1] = hold3; 
                    
                    hold4 = test2[i]; 
                    test2[i] = test2[i + 1]; 
                    test2[i + 1] = hold4; 
                    
                    hold5 = test3[i]; 
                    test3[i] = test3[i + 1]; 
                    test3[i + 1] = hold5; 
                    
                    hold6 = test4[i]; 
                    test4[i] = test4[i + 1]; 
                    test4[i + 1] = hold6; 
                } 
            } 
        } 
    } 
    
        public static double sd(int[] g, int u) throws Exception { 
        double mean, B, sd; 
        B=0;
        
        mean = mean(g, u); 
        
        for(int i = 0; i < u; i++) { 
            B += Math.pow(g[i] - mean, 2); 
        } 
        
        sd = Math.sqrt(B / u); 
        
        return sd; 
    } 
    
    public static double mean(int[] g, int u) { 
        double sum, mean; 
        sum=0;
        
        for (int i = 0; i < u; i++) { 
            sum = sum + g[i]; 
        } 
        mean = (1.0 / u) * sum; 
        return mean; 
    } 
    
    public static void dropLowest(PrintStream ps, int c, 
            String[] fullName, int[] test1, int[] test2, int[] test3, 
            int[] test4, double[] averages) throws Exception { 
        int low = 0; 
        
        ps.println();
        ps.println("Names\t\t\tTest1\tTest2\tTest3\tTest4\tAverages\n" 
                 + "____\t\t\t_____\t_____\t_____\t_____\t_______\n"); 
        
        for (int i = 0; i < c; i++) { 
            if (test1[i] <= test2[i] && test1[i] <= test3[i] && 
                    test1[i] <= test4[i]) { 
                
                low = test1[i]; 
                averages[i] = (test2[i] + test3[i] + test4[i]) / 3.0; 
                ps.printf("%18s\tXXX\t%d\t%d\t%d\t%.2f\t\n", fullName[i], 
                        test2[i], test3[i], test4[i], averages[i]); 
            } 
            
            if (test2[i] <= test1[i] && test2[i] <= test3[i] && 
                    test2[i] <= test4[i]) { 
                low = test2[i]; 
                averages[i] = (test1[i] + test3[i] + test4[i]) / 3.0; 
                ps.printf("%18s\t%d\tXXX\t%d\t%d\t%.2f\t\n", fullName[i], 
                        test1[i], test3[i], test4[i], averages[i]); 
            } 
            
            if (test3[i] <= test1[i] && test3[i] <= test2[i] && 
                    test3[i] <= test4[i]) { 
                low = test3[i]; 
                averages[i] = (test1[i] + test2[i] + test4[i]) / 3.0; 
                ps.printf("%18s\t%d\t%d\tXXX\t%d\t%.2f\t\n", fullName[i], 
                        test1[i], test2[i], test4[i], averages[i]); 
            } 
            
            if (test4[i] <= test1[i] && test4[i] <= test2[i] && 
                    test4[i] <= test3[i]) { 
                low = test4[i]; 
                averages[i] = (test1[i] + test2[i] + test3[i]) / 3.0; 
                ps.printf("%18s\t%d\t%d\t%d\tXXX\t%.2f\t\n", fullName[i], 
                        test1[i], test2[i], test3[i], averages[i]); 
            } 
        } 
    }   
    public static int mode(int[] w, int l) { 
	int largest = 0; 
        int mode = 0; 
	
        for (int i = 0; i < l; i++) { 
            int	count = 0; 
            
            for (int a = 0; a < l; a++) { 
		if (w[a] == w[i]) { 
                    count++; 
                } 
		if (count > largest) { 
                    mode = w[i]; 
                    largest = count; 
		} 
            } 
	} 
        return mode; 
    } 
   
    public static double med(int[] s, int e) throws Exception { 
        sortMed(s, e); 
        
        double median; 
        
	if (e % 2 == 0) { 
            int indexK = (e - 1) / 2; 
            int indexM = e / 2; 
            median = (s[indexK] + s[indexM]) / 2; 
	} else { 
            int index = (e - 1) / 2; 
            median = s[index]; 
	} 
	return median; 
    } 
    public static void sortMed(int[] s, int e) { 
        int i, pass, hold1; 
        boolean switched = true; 
        
        for (pass = 0; pass < e - 1; pass++) { 
            switched = false; 
            for (i = 0; i < e - pass - 1; i++) { 
                if (s[i] > s[i + 1]) { 
                    switched = true; 
                    hold1 = s[i]; 
                    s[i] = s[i + 1]; 
                    s[i + 1] = hold1; 
                } 
            } 
        } 
    } 
    
} 
