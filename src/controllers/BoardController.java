/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.ArrayList;
import models.board.BoardModel;
import models.pieces.Move;
import models.pieces.Piece;
import views.BoardView;

/**
 *
 * @author fawad
 */
public class BoardController {
    private final BoardModel boardModel;
    private final BoardView boardView;
    
    public BoardController(BoardModel model, BoardView view) {
        this.boardModel = model;
        this.boardView = view;
    }
    
    public Piece getPiece(int row, int col) {
        return this.boardModel.getPiece(row, col);
    }
    
    public void highlightPieceMoves(int row, int col) {
        Piece piece = this.boardModel.getPiece(row, col);
        if (piece != null) {
            ArrayList<Move> moves = piece.findMoves(this.boardModel.getBoard());
            this.boardView.setHighlightMoves(moves);
            this.boardView.repaint();
        }
    }
    
    public void unHighlightMoves() {
        // Set highlight moves array in BoardView to an empty array
        // to hide the highlighted moves
        this.boardView.setHighlightMoves(new ArrayList());
    }
    
    public boolean isValidMove(Move move) {
        return this.boardModel.isValidMove(move);
    }
    
    public void makeMove(int fromRow, int fromCol, int toRow, int toCol, Piece piece) {
        this.boardModel.makeMove(fromRow, fromCol, toRow, toCol, piece);
    }
    
    public void repaintBoardView() {
        this.boardView.repaint();
    }
    
}
