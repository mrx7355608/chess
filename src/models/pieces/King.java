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
public class King extends Piece {

    public King(int x, int y, String color) throws IOException {
        super(x, y, color, "king");
    }

    @Override
    public ArrayList<Move> findMoves(Piece[][] board) {
        ArrayList<Move> moves = new ArrayList();

        // Top 3 tiles
        moves.add(new Move(this.row - 1, this.col, this));
        moves.add(new Move(this.row - 1, this.col - 1, this));
        moves.add(new Move(this.row - 1, this.col + 1, this));

        // Bottom 3 tiles
        moves.add(new Move(this.row + 1, this.col, this));
        moves.add(new Move(this.row + 1, this.col - 1, this));
        moves.add(new Move(this.row + 1, this.col + 1, this));

        // Left and Right tiles
        moves.add(new Move(this.row, this.col - 1, this));
        moves.add(new Move(this.row, this.col + 1, this));

        // Remove moves if they are out of the chess board
        Iterator<Move> iterator = moves.iterator();
        while (iterator.hasNext()) {
            Move move = iterator.next();
            if (this.isOutsideTheBoard(move.toRow) || this.isOutsideTheBoard(move.toCol)) {
                iterator.remove();
            } else if (board[move.toRow][move.toCol] != null 
                    && this.isEnemyPiece(board, move.toRow, move.toCol) == false) {
                iterator.remove();
            }
        }

        return moves;
    }
}
