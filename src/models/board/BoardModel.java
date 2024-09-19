
package models.board;

import java.io.IOException;
import models.pieces.Bishop;
import models.pieces.King;
import models.pieces.Knight;
import models.pieces.Move;
import models.pieces.Pawn;
import models.pieces.Piece;
import models.pieces.Queen;
import models.pieces.Rook;


public class BoardModel {
    private static final Piece[][] board = new Piece[8][8];
    
    public BoardModel() {
        try {
            this.setupBoard();
        } catch (IOException ex) {
            System.out.println("[ERROR] " + ex.getMessage());
        }
    }
    
    private void setupBoard() throws IOException {
        board[0][0] = new Rook(0, 0, "black");
        board[0][1] = new Knight(0, 1, "black");
        board[0][2] = new Bishop(0, 2, "black");
        board[0][3] = new Queen(0, 3, "black");
        board[0][4] = new King(0, 4, "black");
        board[0][5] = new Bishop(0, 5, "black");
        board[0][6] = new Knight(0, 6, "black");
        board[0][7] = new Rook(0, 7, "black");

        board[7][0] = new Rook(7, 0, "white");
        board[7][1] = new Knight(7, 1, "white");
        board[7][2] = new Bishop(7, 2, "white");
        board[7][3] = new Queen(7, 3, "white");
        board[7][4] = new King(7, 4, "white");
        board[7][5] = new Bishop(7, 5, "white");
        board[7][6] = new Knight(7, 6, "white");
        board[7][7] = new Rook(7, 7, "white");

        // Add pawns
        for (int i = 1; i < 7; i++) {
            for (int j = 0; j < 8; j++) {
                switch (i) {
                    case 1 -> {
                        board[i][j] = new Pawn(i, j, "black");
                    }
                    case 6 -> {
                        board[i][j] = new Pawn(i, j, "white");
                    }
                    default ->
                        board[i][j] = null;
                }
            }
        }
    }
    
    public void makeMove(int fromRow, int fromCol, int toRow, int toCol, Piece piece) {
        board[toRow][toCol] = piece;
        board[fromRow][fromCol] = null;
    }
    
    public boolean isValidMove(Move move) {
        // 1. If the selected move is not in the "moves" array (which is calculated
        // by the piece class) then return false.
//        if (!this.containsMove(move)) {
//            return false;
//        }

        // 2. If the move is in the array, then get the piece at that position
        Piece piece = board[move.toRow][move.toCol];

        // 3. If the position does not have any piece, then return true
        if (piece == null) {
            return true;
        } // 4. If there's a piece, then check if it's enemy piece or not.
        // If it's enemy piece return true.
        else if (piece.color.equals(move.piece.color) == false) {
            return true;
        }

        // 5. Otherwise, return false
        return false;
    }
    
    public Piece[][] getBoard() {
        return board;
    }
    
    public Piece getPiece(int row, int col) {
        return board[row][col];
    }
}
