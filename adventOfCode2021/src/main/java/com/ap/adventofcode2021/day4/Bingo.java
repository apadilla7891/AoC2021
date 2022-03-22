/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ap.adventofcode2021.day4;

/**
 *
 * @author Andy Padilla
 */
public class Bingo {
    String num;
        boolean marked;
        
        Bingo(String num){
            this.num = num;
            marked = false;
        }
        public String getNum() {
            return num;
        }

        public boolean isMarked() {
            return marked;
        }

        public void setMarked() {
            this.marked = true;
        }
    
}
