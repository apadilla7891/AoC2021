
package com.ap.adventofcode2021.day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Andy Padilla
 */
public class Dive {
    public static void main(String[] args) throws FileNotFoundException {
        String currentLine;
        List<String> actions = new ArrayList<String>();
        Scanner scanner = new Scanner(new File("dayTwoDepths.txt"));
        while(scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            actions.add(currentLine);
        }
        scanner.close();
        int finalDepth = partOne(actions);
        System.out.println("Part one final depth: " + finalDepth);
        
        finalDepth = partTwo(actions);
        System.out.println("Part two final depth: " + finalDepth);
    }
    
    //forward adds to horizontal|| down adds to depth|| up subtracts from depth
    public static int partOne(List<String> actions){
        int horizontal = 0;
        int depth = 0;
        
        for(int i = 0; i < actions.size();i++){
           String[] currentLine = actions.get(i).split(" ");
           int num = Integer.parseInt(currentLine[1]);
           if(currentLine[0].equals("forward")){
               horizontal += num;
           }
           if(currentLine[0].equals("down")){
               depth += num;
           }
           if(currentLine[0].equals("up")){
               depth -= num;
           }
        }
        
        return horizontal * depth;
    }
    
    //adds aim which is multiplies by horizontal during forward
    public static int partTwo(List<String> actions){
        int horizontal = 0;
        int depth = 0;
        int aim = 0;
        for(int i = 0; i < actions.size();i++){
           String[] currentLine = actions.get(i).split(" ");
           int num = Integer.parseInt(currentLine[1]);
           if(currentLine[0].equals("forward")){
               horizontal += num;
               depth += (num*aim);
           }
           if(currentLine[0].equals("down")){
               aim += num;
           }
           if(currentLine[0].equals("up")){
               aim -= num;
           }
        }
        
        return horizontal * depth;
    }
}
