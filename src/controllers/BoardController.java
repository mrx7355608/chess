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
    private String turn;

    public BoardController(BoardModel model, BoardView view) {
        this.boardModel = model;
        this.boardView = view;
        this.turn = "white";
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
        
        // Switch turn
        this.turn = this.turn.equals("white") ? "black": "white";
    }

    public void repaintBoardView() {
        this.boardView.repaint();
    }

    public void detectCheck(String color) {
        // An array containing king's position
        // 0 index -> row
        // 1 index -> col
        int[] kingPosition;
        ArrayList<Piece> enemyPieces;

        // 1. Get king coordinates from board model along with it's enemy pieces
        if (color.equals("white")) {
            kingPosition = this.boardModel.getWhiteKingPosition();
            enemyPieces = this.boardModel.getBlackPieces();
        } else {
            kingPosition = this.boardModel.getBlackKingPosition();
            enemyPieces = this.boardModel.getWhitePieces();
        }

        // 2. Check if moves of any enemy piece collides with King's position
        Piece[][] board = this.boardModel.getBoard();
        
        for (Piece enemyPiece : enemyPieces) {

            // Find enemy piece moves
            ArrayList<Move> moves = enemyPiece.findMoves(board);

            // If, a move overlaps king's coordinates, return true because
            // this is a check
            for (Move move : moves) {
                if (move.toRow == kingPosition[0] && move.toCol == kingPosition[1]) {
                    this.boardModel.setIsBlackInCheck(true);
                    return;
                }
            }
            
            this.boardModel.setIsBlackInCheck(false);
        }
    }

    
    public boolean isPlayerTurn(String pieceColor) {
        return pieceColor.equals(this.turn);
    }

}
