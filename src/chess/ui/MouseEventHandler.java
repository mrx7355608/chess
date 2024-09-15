/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chess.ui;

import chess.core.Piece;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author fawad
 */
public class MouseEventHandler extends MouseAdapter {

    private final BoardPanel board;
    private Piece piece;
    private int fromRow;
    private int fromCol;
    private int toRow;
    private int toCol;

    public MouseEventHandler(BoardPanel board) {
        this.board = board;
        this.piece = null;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
        int row = me.getY() / 60;
        int col = me.getX() / 60;
        
        Piece pieceExists = this.board.getPiece(row, col);
        if (pieceExists == null) return;
        
        this.piece = pieceExists;
        this.fromRow = row;
        this.fromCol = col;
        this.board.highlightMoves(piece);
        
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        int row = me.getY() - 60 / 2;
        int col = me.getX() - 60 / 2;

        if (this.piece != null) {
            this.piece.setX(col);
            this.piece.setY(row);
            this.board.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        this.toRow = me.getY() / 60;
        this.toCol = me.getX() / 60;
        this.board.makeMove(fromRow, fromCol, toRow, toCol, piece);

        if (this.piece != null) {
            this.piece.setX(toCol * 60);
            this.piece.setY(toRow * 60);
            this.board.clearMoves();
            this.piece.updatePiece(toRow, toCol);
            this.board.repaint();
        }

        this.piece = null;
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

}
