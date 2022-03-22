
package com.ap.adventofcode2021.day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Andy Padilla
 */
public class SonarSweep {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("dayOneDepths.txt"));
        int count = 0;
        List<Integer> depths = new ArrayList<Integer>();
        while(scanner.hasNextInt()){
            int next = scanner.nextInt();
            depths.add(next);
        }
        scanner.close();
        count = partOne(depths);
        System.out.println("Part 1 Total: " + count);
        count = partTwo(depths);
        System.out.println("Part 2 Total: " + count);
    }
    
    //count the number of times a depth measurement increases from the previous measurement.
    public static int partOne(List<Integer> nums){
        int count = 0;
        for(int i = 1; i < nums.size(); i ++){
            if(nums.get(i-1)< nums.get(i)){
                count++;
            }
        }
        return count;
    }
    
    //count the number of times the sum of measurements in this three-measurement sliding window increases from the previous sum
    public static int partTwo(List<Integer> nums){
        int count = 0;
        for(int i = 0; i < nums.size()-3; i ++){
            int windowOne = nums.get(i)+ nums.get(i+1) + nums.get(i+2);
            int windowTwo = nums.get(i+1)+ nums.get(i+2) + nums.get(i+3);
            if(windowOne < windowTwo){
                count++;
            }
        }
        return count;
    }
}
