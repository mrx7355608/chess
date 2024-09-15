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
public class Rook extends Piece {

    public Rook(int x, int y, String color) throws IOException {
        super(x, y, color, "rook");
    }

    @Override
    public ArrayList<Move> findMoves() {
        ArrayList<Move> moves = new ArrayList();

        for (int i = 0; i < 8; i++) {
            if (i == this.row) continue;
            moves.add(new Move(i, this.col, this));
        }
        
        for (int i = 0; i < 8; i++) {
            if (i == this.col) continue;
            moves.add(new Move(this.row, i, this));
        }
        
        return moves;
    }
}
