package game;

import game.board.Board;
import game.board.Tile;
import game.errors.InvalidMoveException;
import game.pieces.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Glenn on 6-10-2015.
 */
public class Game {
    private Board board;
    private List<Piece> whitePieces;
    private List<Piece> blackPieces;

    public Game() {
        board = new Board();
        whitePieces = new ArrayList<Piece>();
        blackPieces = new ArrayList<Piece>();
        for(int i = 0; i < 8; i++) {
            Tile whitePosition = board.getTile(board.getColumns()[i] + 2);
            Piece wPawn = new Pawn(whitePosition, "White");
            whitePosition.setPiece(wPawn);
            board.addPiece(wPawn);

            Tile blackPosition = board.getTile(board.getColumns()[i] + 7);
            Piece bPawn = new Pawn(blackPosition, "Black");
            blackPosition.setPiece(bPawn);
            board.addPiece(bPawn);
            Tile wTemp = board.getTile(board.getColumns()[i] + 1);
            Tile bTemp = board.getTile(board.getColumns()[i] + 8);
            Piece wPiece = null;
            Piece bPiece = null;
            if(i == 0 || i == 7) {
                wPiece = new Rook(wTemp, "White");
                bPiece = new Rook(bTemp, "Black");
            } else if(i == 1 || i == 6) {
                wPiece = new Knight(wTemp, "White");
                bPiece = new Knight(bTemp, "Black");
            } else if(i == 2 || i == 5) {
                wPiece = new Bishop(wTemp, "White");
                bPiece = new Bishop(bTemp, "Black");
            } else if (i == 3) {
                wPiece = new Queen(wTemp, "White");
                bPiece = new Queen(bTemp, "Black");
            } else {
                wPiece = new King(wTemp, "White");
                bPiece = new King(bTemp, "Black");
            }

            wTemp.setPiece(wPiece);
            board.addPiece(wPiece);
            board.addPiece(bPiece);
            bTemp.setPiece(bPiece);
        }
    }


    public Board getBoard() {
        return board;
    }

    public boolean move(Piece piece, Tile tile) {
        Piece temp = tile.getPiece();
        try {
            piece.move(board, tile);
        } catch (InvalidMoveException e) {
            return false;
        } finally {
            board.removePieces();
        }
        return true;
    }
}
