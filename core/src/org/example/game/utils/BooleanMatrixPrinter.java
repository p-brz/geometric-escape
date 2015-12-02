/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.game.utils;

/**
 *
 * @author alisonbnt
 */
public class BooleanMatrixPrinter {
    
    public void printMatrix(boolean[][] matrix){
        int width = matrix.length;
        int height = matrix[0].length;
        
        int active = 0;
        for(int x = 0; x < width; x++){
            String line = "";
            for(int y = height - 1; y > -1; y--){
                if(matrix[x][y]){
                    line += "# ";
                    active++;
                }else{
                    line += ". ";
                }
            }
            System.out.println(line);
        }
    }
    
}
