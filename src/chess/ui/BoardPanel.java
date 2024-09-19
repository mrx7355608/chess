package chess.ui;

import controllers.InputHandler;
import models.pieces.Bishop;
import models.pieces.King;
import models.pieces.Knight;
import models.pieces.Move;
import models.pieces.Pawn;
import models.pieces.Piece;
import models.pieces.Queen;
import models.pieces.Rook;
import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {

    private final int TILE_SIZE = 60;
    private final Piece[][] pieces;
    private ArrayList<Move> moves;

    private static BoardPanel instance;

    public static BoardPanel getInstance(Piece[][] pieces) {
        if (instance == null) {
            instance = new BoardPanel(pieces);
        }
        return instance;
    }

    private BoardPanel(Piece[][] pieces) {
        InputHandler mv = new InputHandler();
        moves = new ArrayList();
        
        this.pieces = pieces;
        this.addMouseListener(mv);
        this.addMouseMotionListener(mv);

    }

    public Piece getPiece(int row, int col) {
        return this.pieces[row][col];
    }

    

    public void highlightMoves(Piece piece) {
        this.moves = piece.findMoves();
    }

    public void clearMoves() {
        this.moves.clear();
    }

    private boolean containsMove(Move move) {
        for (Move m : moves) {
            if (m.toRow == move.toRow && m.toCol == move.toCol) {
                return true;
            }
        }

        return false;
    }
}
