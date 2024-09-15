/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.core;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author fawad
 */
public class Pawn extends Piece {

    private boolean isFirstMove = true;

    public Pawn(int x, int y, String color) throws IOException {
        super(x, y, color, "pawn");
    }

    @Override
    public ArrayList<Move> findMoves() {
        ArrayList<Move> moves = new ArrayList();

        if (isFirstMove) {
            int toRow1 = color.equals("black") ? this.row + 1 : this.row - 1;
            int toRow2 = color.equals("black") ? this.row + 2 : this.row - 2;

            Move m1 = new Move(toRow1, this.col);
            Move m2 = new Move(toRow2, this.col);

            moves.add(m1);
            moves.add(m2);
        } else {
            int toRow1 = color.equals("black") ? this.row + 1 : this.row - 1;
            Move m1 = new Move(toRow1, this.col);
            moves.add(m1);
        }

        return moves;
    }

    @Override
    public void updatePiece(int newRow, int newCol) {
        this.row = newRow;
        this.col = newCol;
        if (this.isFirstMove) {
            this.isFirstMove = false;
        }
    }
;
}
