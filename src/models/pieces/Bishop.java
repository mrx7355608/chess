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
public class Bishop extends Piece {

    public Bishop(int x, int y, String color) throws IOException {
        super(x, y, color, "bishop");
    }

    @Override
    public ArrayList<Move> findMoves(Piece[][] board) {
        ArrayList<Move> moves = new ArrayList();

        // Make moves for upper left diagonal
        int nextCol = this.col;
        for (int i = this.row - 1; i >= 0; i--) {
            nextCol -= 1;

            // 1. If move goes out of board, ignore it
            if (this.isOutsideTheBoard(nextCol)) {
                continue;
            }

            // 2. If there is no piece at this new position, add it in moves
            Piece p = board[i][nextCol];
            if (p == null) {
                moves.add(new Move(i, nextCol, this));
                continue;

                // 3. If their is a piece at this new position and it is an
                // enemy piece, then add it in move and break this loop,because
                // on encountering an enemy piece you can only make move uptil
                // that piece (capture) and cannot go further than that
            } else if (this.isEnemyPiece(board, i, nextCol)) {
                moves.add(new Move(i, nextCol, this));
                break;
            }

            // 4. Now, at this line of code, if the tile is neither empty, nor
            // it contains enemy piece, there's only one possibility that, it contains
            // ally piece i.e the piece having same color as current piece. So, 
            // break the loop as, we cannot proceed further according to chess
            // rules
            break;
        }

        // Make moves for upper right diagonal
        nextCol = this.col;
        for (int i = this.row - 1; i >= 0; i--) {
            nextCol += 1;

            if (this.isOutsideTheBoard(nextCol)) {
                continue;
            }

            Piece p = board[i][nextCol];
            if (p == null) {
                moves.add(new Move(i, nextCol, this));
                continue;
            } else if (this.isEnemyPiece(board, i, nextCol)) {
                System.out.println("Enemy");
                moves.add(new Move(i, nextCol, this));
                break;
            }

            break;
        }

        // Make moves for lower left diagonal
        nextCol = this.col;
        for (int i = this.row + 1; i < 8; i++) {
            nextCol -= 1;
            if (this.isOutsideTheBoard(nextCol)) {
                continue;
            }

            Piece p = board[i][nextCol];
            if (p == null) {
                moves.add(new Move(i, nextCol, this));
                continue;
            } else if (this.isEnemyPiece(board, i, nextCol)) {
                moves.add(new Move(i, nextCol, this));
                break;
            }

            break;
        }

        // Make moves for lower right diagonal
        nextCol = this.col;
        for (int i = this.row + 1; i < 8; i++) {
            nextCol += 1;

            if (this.isOutsideTheBoard(nextCol)) {
                continue;
            }

            Piece p = board[i][nextCol];
            if (p == null) {
                moves.add(new Move(i, nextCol, this));
                continue;
            } else if (this.isEnemyPiece(board, i, nextCol)) {
                moves.add(new Move(i, nextCol, this));
                break;
            }

            break;
        }

        return moves;
    }
}
