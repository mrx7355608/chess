/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.pieces;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author fawad
 */
public class Queen extends Piece {

    public Queen(int x, int y, String color) throws IOException {
        super(x, y, color, "queen");
    }

    @Override
    public ArrayList<Move> findMoves(Piece[][] board) {
        ArrayList<Move> moves = new ArrayList();

        this.calc(col, row, board, moves, "top");
        this.calc(col, row, board, moves, "left");
        this.calc(col, row, board, moves, "right");
        this.calc(col, row, board, moves, "down");
        
        this.calc(col, row, board, moves, "upper-left");
        this.calc(col, row, board, moves, "upper-right");
        this.calc(col, row, board, moves, "lower-left");
        this.calc(col, row, board, moves, "lower-right");
        

        return moves;
    }

    public void calc(int col, int row, Piece[][] board, ArrayList<Move> moves, String direction) {
        switch (direction) {
            case "top" ->
                row -= 1;

            case "left" ->
                col -= 1;

            case "right" ->
                col += 1;

            case "down" ->
                row += 1;

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

            default ->
                row -= 1;
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
