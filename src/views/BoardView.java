/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import controllers.InputHandler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import models.board.BoardModel;
import models.pieces.Move;
import models.pieces.Piece;

/**
 *
 * @author fawad
 */
public class BoardView extends JPanel {

    private final int TILE_SIZE = 60;
    private final BoardModel boardModel;
    private ArrayList<Move> highlightMoves = new ArrayList();

    public BoardView(BoardModel model) {
        this.boardModel = model;
    }

    public void addMouseListener(InputHandler inputHandler) {
        super.addMouseListener(inputHandler);
        super.addMouseMotionListener(inputHandler);
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
        if (highlightMoves.isEmpty() == false) {
            for (Move move : highlightMoves) {
                int x = move.toCol * TILE_SIZE;
                int y = move.toRow * TILE_SIZE;
                g.setColor(new Color(230, 230, 0, 170));
                g.fillOval(x, y, TILE_SIZE, TILE_SIZE);
            }
        }

        // Draw pieces
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = this.boardModel.getPiece(i, j);
                if (piece != null) {
                    piece.draw(g);
                }
            }
        }
        
        // Highlight check
        if (this.boardModel.isBlackInCheck()) {
            int[] kingPos = this.boardModel.getBlackKingPosition();
            int x = kingPos[1] * TILE_SIZE;
            int y = kingPos[0] * TILE_SIZE;
            g.setColor(new Color(255,0,0,150));
            g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
        } else if (this.boardModel.isWhiteInCheck()) {
            int[] kingPos = this.boardModel.getWhiteKingPosition();
            int x = kingPos[1] * TILE_SIZE;
            int y = kingPos[0] * TILE_SIZE;
            g.setColor(new Color(255,0,0,150));
            g.fillRect(x, y, TILE_SIZE, TILE_SIZE);
        }
    }

    public void setHighlightMoves(ArrayList<Move> highlightMoves) {
        this.highlightMoves = highlightMoves;
    }

}
