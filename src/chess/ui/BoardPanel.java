package chess.ui;

import chess.core.Bishop;
import chess.core.King;
import chess.core.Knight;
import chess.core.Move;
import chess.core.Pawn;
import chess.core.Piece;
import chess.core.Queen;
import chess.core.Rook;
import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {

    private final int TILE_SIZE = 60;
    private Piece[][] pieces = new Piece[8][8];
    private ArrayList<Move> moves;

    public BoardPanel() {
        try {
            MouseEventHandler mv = new MouseEventHandler(this);
            moves = new ArrayList();

            this.addPieces();
            this.addMouseListener(mv);
            this.addMouseMotionListener(mv);
        } catch (IOException ex) {
            Logger.getLogger(BoardPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addPieces() throws IOException {
        pieces[0][0] = new Rook(0, 0, "black");
        pieces[0][1] = new Knight(0, 1, "black");
        pieces[0][2] = new Bishop(0, 2, "black");
        pieces[0][3] = new Queen(0, 3, "black");
        pieces[0][4] = new King(0, 4, "black");
        pieces[0][5] = new Bishop(0, 5, "black");
        pieces[0][6] = new Knight(0, 6, "black");
        pieces[0][7] = new Rook(0, 7, "black");

        pieces[7][0] = new Rook(7, 0, "white");
        pieces[7][1] = new Knight(7, 1, "white");
        pieces[7][2] = new Bishop(7, 2, "white");
        pieces[7][3] = new Queen(7, 3, "white");
        pieces[7][4] = new King(7, 4, "white");
        pieces[7][5] = new Bishop(7, 5, "white");
        pieces[7][6] = new Knight(7, 6, "white");
        pieces[7][7] = new Rook(7, 7, "white");

        // Add pawns
        for (int i = 1; i < 7; i++) {
            for (int j = 0; j < 8; j++) {
                switch (i) {
                    case 1 -> {
                        pieces[i][j] = new Pawn(i, j, "black");
                    }
                    case 6 -> {
                        pieces[i][j] = new Pawn(i, j, "white");
                    }
                    default ->
                        pieces[i][j] = null;
                }
            }
        }
    }

    public Piece getPiece(int row, int col) {
        return this.pieces[row][col];
    }

    public void makeMove(int fromRow, int fromCol, int toRow, int toCol, Piece piece) {
        this.pieces[toRow][toCol] = piece;
        this.pieces[fromRow][fromCol] = null;
    }

    public void highlightMoves(Piece piece) {
        this.moves = piece.findMoves();
    }
    
    public void clearMoves() {
        this.moves.clear();
    }

    @Override
    public void paintComponent(Graphics g) {
        // Draw 8x8 grid
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Color tileColor = (i + j) % 2 == 0 ? new Color(112, 162, 163) : new Color(177, 228, 185);
                g.setColor(tileColor);
                g.fillRect(j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }

        // Highlight moves (if any)
        for (Move move : moves) {
            int x = move.toCol * TILE_SIZE;
            int y = move.toRow * TILE_SIZE;
            g.setColor(Color.yellow);
            g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
        }
        
        // Draw pieces
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = pieces[i][j];
                if (piece != null) {
                    piece.draw(g);
                }
            }
        }
    }
}
