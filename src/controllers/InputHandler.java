/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import models.pieces.Move;
import models.pieces.Piece;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author fawad
 */
public class InputHandler extends MouseAdapter {
    private Piece piece;
    private int fromRow;
    private int fromCol;
    private int toRow;
    private int toCol;
    private final BoardController boardController;

    public InputHandler(BoardController controller) {
        this.piece = null;
        this.boardController = controller;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
        int row = me.getY() / 60;
        int col = me.getX() / 60;
        
        System.out.println(row + " x " + col);

//        Piece pieceExists = this.boardController.getPiece(row, col);
//        if (pieceExists == null) {
//            return;
//        }
//
//        this.piece = pieceExists;
//        this.fromRow = row;
//        this.fromCol = col;
//        this.boardController.highlightMoves(piece);

    }

    @Override
    public void mouseDragged(MouseEvent me) {
        int row = me.getY() - 60 / 2;
        int col = me.getX() - 60 / 2;
        System.out.println("dragging");

//        if (this.piece != null) {
//            this.piece.setX(col);
//            this.piece.setY(row);
//            this.boardController.repaint();
//        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        this.toRow = me.getY() / 60;
        this.toCol = me.getX() / 60;
        
        System.out.println(toRow + " x " + toCol);

//        Move move = new Move(toRow, toCol, piece);

//        if (this.piece != null) {
//            // If move is valid, then update the board and change X and Y positions
//            // of the piece along with its row and column
//            if (this.boardController.isValidMove(move)) {
//                this.boardController.makeMove(fromRow, fromCol, toRow, toCol, piece);
//                this.piece.setX(toCol * 60);
//                this.piece.setY(toRow * 60);
//                this.piece.updatePiece(toRow, toCol);
//            } else {
//                // Otherwise, move the piece back to it's original position
//                this.piece.setX(fromCol * 60);
//                this.piece.setY(fromRow * 60);
//            }
//            this.boardController.clearMoves();
//            this.boardController.repaint();
//        }
//
//        this.piece = null;
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

}
