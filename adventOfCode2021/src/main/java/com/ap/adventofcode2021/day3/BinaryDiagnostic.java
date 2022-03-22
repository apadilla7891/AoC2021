
package com.ap.adventofcode2021.day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Andy Padilla
 */
public class BinaryDiagnostic {
    public static void main(String[] args) throws FileNotFoundException {
        String currentLine;
        List<String> data = new ArrayList<String>();
        List<String> data2 = new ArrayList<String>();
        Scanner scanner = new Scanner(new File("dayThreeReport.txt"));
        while(scanner.hasNextLine()){
            currentLine = scanner.nextLine();
            data.add(currentLine);
            data2.add(currentLine);
        }
        scanner.close();
        String gamma = findGamma(data);
        String epsilon = findEpsilon(gamma);
        int powerConsumption = findPowerConsumption(gamma, epsilon);
        System.out.println("Part one power consuption: " + powerConsumption);
        String oxyRating = findOxygen(data);
        String co2Rating = findCO2(data2);
        int lifeSupport = findPowerConsumption(oxyRating, co2Rating);
        System.out.println("Part two life support: " + lifeSupport);
    }
    //part one
    public static String findGamma(List<String> data){
        int numSize = data.get(0).length();
        int inputs = data.size();
        int[] oneCount = new int[numSize];
        int[] zeroCount = new int[numSize];
        for(int i = 0; i < inputs; i++){
            String current = data.get(i);
            for(int j=0;j<numSize;j++){
                int digit = Character.getNumericValue(current.charAt(j));
                if(digit == 1){
                    int temp = oneCount[j];
                    temp++;
                    oneCount[j] = temp;
                }
            }
        }
        String sequence = "";
        for(int j = 0; j<numSize; j++){
            if(oneCount[j] > (inputs - oneCount[j])){
                sequence+="1";
            }else{
                sequence+="0";
            }
            
        }
        return sequence;
    }
    public static String findEpsilon(String gamma){
        String sequence = "";
        for(int i =0; i < gamma.length();i++){
            Character c1 = gamma.charAt(i);
            Character c2 = '1';
            if(c1.equals(c2)){
                sequence +="0";
            }else{
                sequence += "1";
            }
        }
        return sequence;
    }
    
    public static int findPowerConsumption(String gamma, String epsilon){
        int gam = Integer.parseInt(gamma, 2);
        int ep = Integer.parseInt(epsilon, 2);
        return gam * ep;
    }
    
    //part two
    public static String findOxygen(List<String> data){
        String oxyGenRating = "";
        List<String> oxyData = new ArrayList<String>();
        oxyData = data;
        mainLoop:
        for(int i = 0; i< oxyData.get(0).length();i++){
            int oneCount = 0;
            int zeroCount = 0;
            for(int j = 0; j< oxyData.size(); j++){
                if(oxyData.get(j).charAt(i)=='1'){
                    oneCount++;
                }else{
                    zeroCount++;
                }
            }
            if(zeroCount> oneCount){
                for(int k = 0; k<oxyData.size(); k++){
                    if(oxyData.get(k).charAt(i) =='1'){
                        oxyData.remove(k);
                        k--;
                    }
                    if(oxyData.size()==1){
                        oxyGenRating = oxyData.get(0);
                        break mainLoop;
                    }
                }
            }else{
                for(int k = 0; k<oxyData.size(); k++){
                    if(oxyData.get(k).charAt(i) =='0'){
                        oxyData.remove(k);
                        k--;
                    }
                    if(oxyData.size()==1){
                        oxyGenRating = oxyData.get(0);
                        break mainLoop;
                    }
                }
            }
            
        }
        return oxyGenRating;
    }
    
    public static String findCO2(List<String> data){
        String co2GenRating = "";
        List<String> scrubData = new ArrayList<String>();
        scrubData = data;
        mainLoop:
        for(int i = 0; i< scrubData.get(0).length();i++){
            int oneCount = 0;
            int zeroCount = 0;
            for(int j = 0; j< scrubData.size(); j++){
                if(scrubData.get(j).charAt(i)=='1'){
                    oneCount++;
                }else{
                    zeroCount++;
                }
            }
            if(oneCount< zeroCount){
                for(int k = 0; k<scrubData.size(); k++){
                    if(scrubData.get(k).charAt(i) =='0'){
                        scrubData.remove(k);
                        k--;
                    }
                    if(scrubData.size()==1){
                        co2GenRating = scrubData.get(0);
                        break mainLoop;
                    }
                }
            }else{
                for(int k = 0; k<scrubData.size(); k++){
                    if(scrubData.get(k).charAt(i) =='1'){
                        scrubData.remove(k);
                        k--;
                    }
                    if(scrubData.size()==1){
                        co2GenRating = scrubData.get(0);
                        break mainLoop;
                    }
                }
            }
            
        }
        return co2GenRating;
    }
}
