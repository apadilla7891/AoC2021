
package com.ap.adventofcode2021.day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Andy Padilla
 */
public class GiantSquid {
    public static void main(String[] args) throws FileNotFoundException {
        List<Bingo[][]> input = new ArrayList<Bingo[][]>();
	Bingo[][] card = new Bingo[5][5];
        String s="";
	String[] line;
	File inputFile = new File("dayFourBingo.txt");
	Scanner scanner;
        try{
            scanner = new Scanner(inputFile);
            String currentLine = String.valueOf(scanner.nextLine());
            String[] drawOrder = currentLine.split(",");
            while(scanner.hasNext()){
                scanner.nextLine();
                for(int i = 0; i<card.length;i++){
                    s= String.valueOf(scanner.nextLine().strip());
                    line = s.split("\\s+");
                    for(int j=0; j<line.length;j++){
                        card[i][j] = new Bingo(line[j]);
                    }
                }
                input.add(card);
                card = new Bingo[5][5];
            }
            System.out.println("Data inserted");
            String p1 = partOne(drawOrder,input);
            System.out.println("Part One first winning score: "+ p1);
            String p2 = partTwo(drawOrder,input);
            System.out.println("Part Two last winning score: " + p2);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
	}
    }
     public static String partOne(String[] drawOrder, List<Bingo[][]> bingoCards){
         String drawn;
         String winner = "";
         for(int i = 0; i < drawOrder.length; i++){
             drawn = drawOrder[i];
             
             for(int j = 0; j < bingoCards.size(); j++){
                 Bingo[][] currentCard = bingoCards.get(j);
                 for(int k = 0; k <5; k++){
                     for(int n =0; n <5; n++){
                         if(currentCard[k][n].getNum().equals(drawn)){
                             currentCard[k][n].setMarked();
                         }
                     }
                 }
                 if(checkWinner(currentCard)){
                     return calculateScore(drawn, currentCard);
                 }
             }
             
         }
         return winner;
     }
    
     public static String partTwo(String[] drawOrder, List<Bingo[][]> bingoCards){
         String drawn;
         String winner = "";
         int cardSize = bingoCards.size();
         boolean[] skip = new boolean[cardSize];
         for(int i = 0; i < drawOrder.length; i++){
             drawn = drawOrder[i];
             
             for(int j = 0; j < bingoCards.size(); j++){
                 Bingo[][] currentCard = bingoCards.get(j);
                 for(int k = 0; k <5; k++){
                     for(int n =0; n <5; n++){
                         if(currentCard[k][n].getNum().equals(drawn)){
                             currentCard[k][n].setMarked();
                         }
                     }
                 }
                 if(checkWinner(currentCard) && !skip[j]){
                     skip[j] = true;
                     cardSize--;
                     if(cardSize==0){
                         return calculateScore(drawn, bingoCards.get(j));
                    }
                 }
                 
             }
             
         }
         return winner;
     }
     
    public static boolean checkWinner(Bingo[][] currentCard){
        int rowCount = 0;
        int colCount=0;
        
        //check rows
        for(int k = 0; k <5; k++){
            for(int n =0; n <5; n++){
                if(currentCard[k][n].isMarked()==true){
                    rowCount++;
                }
            }
            if(rowCount == 5){
                return true;
            }
            rowCount=0;
        }
        //check columns
        for(int k = 0; k <5; k++){
            for(int n =0; n <5; n++){
                if(currentCard[n][k].isMarked()==true){
                    colCount++;
                }
            }
            if(colCount == 5){
                return true;
            }
            colCount=0;
        }
        return false;
    }
    
    public static String calculateScore(String drawn, Bingo[][] currentCard){
        String score = "";
        int runningScore= 0;
        for(int i=0; i<currentCard.length; i++){
            for(int j=0; j<currentCard[0].length; j++){
                if(!currentCard[i][j].isMarked()){
                    runningScore += Integer.valueOf(currentCard[i][j].getNum());
                }
            }
        }
        runningScore *= Integer.valueOf(drawn);
        score = String.valueOf(runningScore);
        return score;
    }

        
}
