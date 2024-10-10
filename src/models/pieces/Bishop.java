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
public class Bishop extends Piece {

    public Bishop(int x, int y, String color) throws IOException {
        super(x, y, color, "bishop");
    }

    @Override
    public ArrayList<Move> findMoves(Piece[][] board) {
        ArrayList<Move> moves = new ArrayList();
        
        this.calc(this.col, this.row, board, moves, "upper-left");
        this.calc(this.col, this.row, board, moves, "upper-right");
        this.calc(this.col, this.row, board, moves, "lower-left");
        this.calc(this.col, this.row, board, moves, "lower-right");

        return moves;
    }

    public void calc(int col, int row, Piece[][] board, ArrayList<Move> moves, String direction) {
        // Create new coordinates (row,col) according to direction of the diagonal
        switch (direction) {
            case "upper-left" -> {
                row -= 1;
                col -= 1;
            }
                
            case "upper-right" -> {
                row -= 1;
                col += 1;
            }

            case "lower-left" -> {
                row += 1;
                col -= 1;
            }
                
            case "lower-right" -> {
                row += 1;
                col += 1;
            }
                
            default -> {
                row -= 1;
                col -= 1;
            }
        }
        
        // 1. If either row or col are outside the board, end recursion
        if (this.isOutsideTheBoard(col) || this.isOutsideTheBoard(row)) {
            return;
        }

        // 2. If the tile is empty then, add those coordinates in moves.
        Piece p = board[row][col];
        if (p == null) {
            moves.add(new Move(row, col, this));
            
            // Find other possible moves recursively
            this.calc(col, row, board, moves, direction);
            
        } else if (this.isEnemyPiece(board, row, col)) {
            moves.add(new Move(row, col, this));
        }
    }

}
