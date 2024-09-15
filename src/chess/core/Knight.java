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
public class Knight extends Piece {

    public Knight(int x, int y, String color) throws IOException {
        super(x, y, color, "knight");
    }

    @Override
    public ArrayList<Move> findMoves() {
        ArrayList<Move> moves = new ArrayList();

        int[] rowIndices = {2, 1, -2, -1};

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                int newRow = rowIndices[i];
                
                // Subtract and Add rowIndices[i] in current row of the knight
                // because he can move in all directions
                // NOTE: for left and right moves, I am using columns, see below.
                int toRow = this.row + newRow;
                
                // For each row, the knight can move either 1 column to the left
                // or one column to the right, if he is moving 2 rows up, then he
                // can only move one column, make an L shape. Similarly, if the
                // knight is moving one row up or down, he can move 2 columns
                // making the L shape.
                int toCol1 = newRow == 2 || newRow == -2 ? this.col - 1 : this.col - 2;
                int toCol2 = newRow == 2 || newRow == -2 ? this.col + 1 : this.col + 2;

                moves.add(new Move(toRow, toCol1));
                moves.add(new Move(toRow, toCol2));
            }
        }
        
        for (Move move : moves) {
            if (move.toRow < 0 || move.toRow > 7) {
                moves.remove(move);
            }
        }

        return moves;
    }
}
