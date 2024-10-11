/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.ArrayList;
import models.board.BoardModel;
import models.pieces.King;
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

    // FIX ME: write a better logic
    public boolean isKingInCheck(String color) {
        // 1. Find king coordinates
        int kingRow = 0;
        int kingCol = 0;

        Piece[][] board = this.boardModel.getBoard();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j];
                if (piece != null && piece.color.equals(color) && piece.getClass() == King.class) {
                    kingRow = i;
                    kingCol = j;
                }
            }
        }
        
        System.out.println("King coordinates found");
        
        // 2. Check if moves of any enemy piece collides with King's position
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j];
                
                if (piece == null) continue;
                
                // Ignore ally pieces, as they cannot trigger a check on their
                // own king
                if (piece.color.equals(color)) continue;
                
                // Next, find moves of the piece
                ArrayList<Move> moves = piece.findMoves(board);
                
                // If, a move overlaps king's coordinates, return true because
                // this is a check
                for (Move move : moves) {
                    if (move.toRow == kingRow && move.toCol == kingCol) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }

    public void displayCheck(String color) {
        // 1. Find king coordinates
        int kingRow = 0;
        int kingCol = 0;

        Piece[][] board = this.boardModel.getBoard();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j];
                if (piece != null && piece.color.equals(color) && piece.getClass() == King.class) {
                    kingRow = i;
                    kingCol = j;
                }
            }
        }
        
        this.boardView.setCheckCoordinates(kingRow, kingCol);
    }
}
