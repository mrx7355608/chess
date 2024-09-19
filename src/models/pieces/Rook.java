/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.pieces;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author fawad
 */
public class Rook extends Piece {

    public Rook(int x, int y, String color) throws IOException {
        super(x, y, color, "rook");
    }

    @Override
    public ArrayList<Move> findMoves(Piece[][] board) {
        ArrayList<Move> moves = new ArrayList();

        // For top moves
        for (int i = this.row - 1; i >= 0; i--) {
            // If there's a piece of our team blocking the way,
            // ignore the follow up moves
            Piece piece = board[i][this.col];
            if (piece != null && piece.color.equals(this.color)) {
                System.out.println("breaking... " + i);
                break;
            }

            moves.add(new Move(i, this.col, this));
        }

        // For bottom moves
        for (int i = this.row + 1; i < 8; i++) {
            Piece piece = board[i][this.col];
            if (piece != null && piece.color.equals(this.color)) {
                break;
            }

            moves.add(new Move(i, this.col, this));
        }
        
        // For left side moves
        for (int i = this.col - 1; i >= 0; i--) {
            Piece piece = board[i][this.col];
            if (piece != null && piece.color.equals(this.color)) {
                break;
            }
            moves.add(new Move(this.row, i, this));
        }

        // For right side moves
        for (int i = this.col + 1; i < 8; i++) {
            Piece piece = board[i][this.col];
            if (piece != null && piece.color.equals(this.color)) {
                break;
            }
            moves.add(new Move(this.row, i, this));
        }

        return moves;
    }
}
