/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.pieces;

/**
 *
 * @author fawad
 */
public class Move {
    public int toRow;
    public int toCol;
    public Piece piece;

    public Move(int toRow, int toCol, Piece piece) {
        this.toRow = toRow;
        this.toCol = toCol;
        this.piece = piece;
    }
    
}
