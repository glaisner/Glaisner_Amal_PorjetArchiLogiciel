/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piece;

import java.awt.Color;


public class AbstractPiece implements Piece{
    Color baseColor;
    Color lightColor;
    Color darkColor;
    int spawnCol;
    int spawnRow;
    int dimension;
    int rows;
    int cols;
    boolean[][] tiles;


    public AbstractPiece(Color color) {
            this.baseColor = color;
            this.lightColor = color.brighter();
            this.darkColor = color.darker();
            
            this.spawnCol = 5 - (dimension >> 1);
            this.spawnRow = getTopInset(0);
    }


    public Color getBaseColor() {
            return baseColor;
    }


    public Color getLightColor() {
            return lightColor;
    }


    public Color getDarkColor() {
            return darkColor;
    }


    public int getDimension() {
            return dimension;
    }


    public int getSpawnColumn() {
            return spawnCol;
    }


    public int getSpawnRow() {
            return spawnRow;
    }


    public int getRows() {
            return rows;
    }


    public int getCols() {
            return cols;
    }

    public boolean isPiece(int x, int y, int rotation) {
            return tiles[rotation][y * dimension + x];
    }


    public int getLeftInset(int rotation) {

            for(int x = 0; x < dimension; x++) {
                    for(int y = 0; y < dimension; y++) {
                            if(isPiece(x, y, rotation)) {
                                    return x;
                            }
                    }
            }
            return -1;
    }


    public int getRightInset(int rotation) {

            for(int x = dimension - 1; x >= 0; x--) {
                    for(int y = 0; y < dimension; y++) {
                            if(isPiece(x, y, rotation)) {
                                    return dimension - x;
                            }
                    }
            }
            return -1;
    }


    public int getTopInset(int rotation) {

            for(int y = 0; y < dimension; y++) {
                    for(int x = 0; x < dimension; x++) {
                            if(isPiece(x, y, rotation)) {
                                    return y;
                            }
                    }
            }
            return -1;
    }


    public int getBottomInset(int rotation) {

            for(int y = dimension - 1; y >= 0; y--) {
                    for(int x = 0; x < dimension; x++) {
                            if(isPiece(x, y, rotation)) {
                                    return dimension - y;
                            }
                    }
            }
            return -1;
    }
	
}


