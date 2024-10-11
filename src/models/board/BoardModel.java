package models.board;

import java.io.IOException;
import java.util.ArrayList;
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

    private int[] whiteKingPosition = new int[2]; // [row, col] for white king
    private int[] blackKingPosition = new int[2]; // [row, col] for black king
    private boolean isBlackInCheck = false;
    private boolean isWhiteInCheck = false;
    private ArrayList<Piece> whitePieces = new ArrayList();
    private ArrayList<Piece> blackPieces = new ArrayList();

    public BoardModel() {
        try {
            this.setupBoard();
        } catch (IOException ex) {
            System.out.println("[ERROR] " + ex.getMessage());
        }
    }

    private void setupBoard() throws IOException {
        // Create pieces
        Piece blackRook1 = new Rook(0, 0, "black");
        Piece blackKnight1 = new Knight(0, 1, "black");
        Piece blackBishop1 = new Bishop(0, 2, "black");
        Piece blackQueen = new Queen(0, 3, "black");
        Piece blackKing = new King(0, 4, "black");
        Piece blackBishop2 = new Bishop(0, 5, "black");
        Piece blackKnight2 = new Knight(0, 6, "black");
        Piece blackRook2 = new Rook(0, 7, "black");

        Piece whiteRook1 = new Rook(7, 0, "white");
        Piece whiteKnight1 = new Knight(7, 1, "white");
        Piece whiteBishop1 = new Bishop(7, 2, "white");
        Piece whiteQueen = new Queen(7, 3, "white");
        Piece whiteKing = new King(7, 4, "white");
        Piece whiteBishop2 = new Bishop(7, 5, "white");
        Piece whiteKnight2 = new Knight(7, 6, "white");
        Piece whiteRook2 = new Rook(7, 7, "white");

        // Set pieces on board
        board[0][0] = blackRook1;
        board[0][1] = blackKnight1;
        board[0][2] = blackBishop1;
        board[0][3] = blackQueen;
        board[0][4] = blackKing;
        board[0][5] = blackBishop2;
        board[0][6] = blackKnight2;
        board[0][7] = blackRook2;

        board[7][0] = whiteRook1;
        board[7][1] = whiteKnight1;
        board[7][2] = whiteBishop1;
        board[7][3] = whiteQueen;
        board[7][4] = whiteKing;
        board[7][5] = whiteBishop2;
        board[7][6] = whiteKnight2;
        board[7][7] = whiteRook2;

        // Store pieces in their specific arrays so that it's easy
        // to retrieve them in detectCheck() method logic
        blackPieces.add(blackRook2);
        blackPieces.add(blackRook2);
        blackPieces.add(blackRook2);
        blackPieces.add(blackRook2);
        blackPieces.add(blackRook2);
        blackPieces.add(blackRook2);
        blackPieces.add(blackRook2);
        blackPieces.add(blackRook2);

        whitePieces.add(whiteRook1);
        whitePieces.add(whiteKnight1);
        whitePieces.add(whiteBishop1);
        whitePieces.add(whiteQueen);
        whitePieces.add(whiteKing);
        whitePieces.add(whiteBishop2);
        whitePieces.add(whiteKnight2);
        whitePieces.add(whiteRook2);

        // Store black king's initial position
        blackKingPosition[0] = 0;
        blackKingPosition[1] = 4;

        // Store white king's initial position
        whiteKingPosition[0] = 7;
        whiteKingPosition[1] = 4;

        // Add pawns
        for (int row = 1; row < 7; row++) {

            // Using switch case to add pawns on specific rows, according to
            // how they are arranged on chess board
            switch (row) {
                case 1 -> {
                    for (int col = 0; col < 8; col++) {
                        Piece pawn = new Pawn(row, col, "black");
                        board[row][col] = pawn;
                        blackPieces.add(pawn);
                    }
                }

                case 6 -> {
                    for (int col = 0; col < 8; col++) {
                        Piece pawn = new Pawn(row, col, "white");
                        board[row][col] = pawn;
                        whitePieces.add(pawn);
                    }
                }

                default -> {
                    for (int col = 0; col < 8; col++) {
                        board[row][col] = null;
                    }
                }

            }

        }
    }

    public void makeMove(int fromRow, int fromCol, int toRow, int toCol, Piece piece) {
        board[toRow][toCol] = piece;
        board[fromRow][fromCol] = null;

        // Remove captured piece from its array
        Piece capturedPiece = board[fromRow][fromCol];
        if (capturedPiece != null) {
            if (capturedPiece.color.equals("white")) {
                whitePieces.remove(capturedPiece);
            } else {
                blackPieces.remove(capturedPiece);
            }
        }

        // If the piece is a king, update the corresponding king's position
        if (piece instanceof King) {
            if (piece.color.equals("white")) {
                whiteKingPosition[0] = toRow;
                whiteKingPosition[1] = toCol;
            } else {
                blackKingPosition[0] = toRow;
                blackKingPosition[1] = toCol;
            }
        }
    }

    private boolean containsMove(Move move, ArrayList<Move> moves) {
        for (Move m : moves) {
            if (m.toRow == move.toRow && m.toCol == move.toCol) {
                return true;
            }
        }

        return false;
    }

    public boolean isValidMove(Move move) {
        // 1. If the selected move is not in the "legalMoves" array then
        // return false.
        ArrayList<Move> legalMoves = move.piece.findMoves(board);
        if (this.containsMove(move, legalMoves) == false) {
            return false;
        }

        // 2. Get the piece at move's toRow, toCol position
        Piece piece = board[move.toRow][move.toCol];

        // 3. If the position does not have any piece, return true
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

    public int[] getWhiteKingPosition() {
        return whiteKingPosition;
    }

    public int[] getBlackKingPosition() {
        return blackKingPosition;
    }

    public void setIsBlackInCheck(boolean isBlackInCheck) {
        this.isBlackInCheck = isBlackInCheck;
    }

    public void setIsWhiteInCheck(boolean isWhiteInCheck) {
        this.isWhiteInCheck = isWhiteInCheck;
    }

    public boolean isBlackInCheck() {
        return isBlackInCheck;
    }

    public boolean isWhiteInCheck() {
        return isWhiteInCheck;
    }

    public ArrayList<Piece> getWhitePieces() {
        return whitePieces;
    }

    public ArrayList<Piece> getBlackPieces() {
        return blackPieces;
    }

}
