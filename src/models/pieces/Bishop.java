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
            if (nextCol < 0) {
                continue;
            }

            // 2. If there is no piece at this new position, add it in moves
            Piece p = board[i][nextCol];
            if (p == null) {
                moves.add(new Move(i, nextCol, this));
            } else {
                // 3. If their is a piece at this new position and it is an
                // enemy piece, then add it in move and break this loop,because
                // on encountering a piece you cannot go further than that
                if (p.color.equals(this.color) == false) {
                    moves.add(new Move(i, nextCol, this));
                }
                break;
            }
        }

        // Make moves for upper right diagonal
        nextCol = this.col;
        for (int i = this.row - 1; i >= 0; i--) {
            nextCol += 1;
            if (nextCol > 7) {
                continue;
            }

            Piece p = board[i][nextCol];
            if (p == null) {
                moves.add(new Move(i, nextCol, this));
            } else {
                if (p.color.equals(this.color) == false) {
                    moves.add(new Move(i, nextCol, this));
                }
                break;
            }
        }

        // Make moves for lower left diagonal
        nextCol = this.col;
        for (int i = this.row + 1; i < 8; i++) {
            nextCol -= 1;
            if (nextCol < 0) {
                continue;
            }

            Piece p = board[i][nextCol];
            if (p == null) {
                moves.add(new Move(i, nextCol, this));
            } else {
                if (p.color.equals(this.color) == false) {
                    moves.add(new Move(i, nextCol, this));
                }
                break;
            }
        }

        // Make moves for lower right diagonal
        nextCol = this.col;
        for (int i = this.row + 1; i < 8; i++) {
            nextCol += 1;
            if (nextCol > 7) {
                continue;
            }

            Piece p = board[i][nextCol];
            if (p == null) {
                moves.add(new Move(i, nextCol, this));
            } else {
                if (p.color.equals(this.color) == false) {
                    moves.add(new Move(i, nextCol, this));
                }
                break;
            }
        }

        return moves;
    }
}
