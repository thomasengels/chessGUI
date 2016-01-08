package game;

import game.AI.AiPlayer;
import game.AI.Evaluation;
import game.board.Board;
import game.board.Move;
import game.board.Tile;
import game.errors.InvalidMoveException;
import game.pieces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by Glenn on 6-10-2015.
 */
public class Game extends Observable {
    private Board board;
    private List<Move> moves;

    public boolean isWhitesTurn() {
        return whitesTurn;
    }

    private boolean whitesTurn;
    private Evaluation ev;
    private AiPlayer aiPlayer;

    public void triggerAiPlayer(){
        aiPlayer.MoveForReal(this);
    }

    public Game() {
        ev = new Evaluation();
        aiPlayer = new AiPlayer();

        whitesTurn = true;
        board = new Board();
        moves = new ArrayList<>();
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

    public List<Move> getMoves() {
        return moves;
    }

    public void move(Move move) {
        try {
            board = move.getPiece().move(board, move.getLocation());
            moves.add(move);
        } catch (InvalidMoveException e) {
            e.printStackTrace();
        } finally {
            board.removePieces();
        }
        /*

        setChanged();
        notifyObservers(this);
        */

    }

    public void move(String move) {
        System.out.println(move);
        String[] locations = move.split(",");
        try{
            board = board.getTile(locations[0]).getPiece().move(board, board.getTile(locations[1]));
        } catch (InvalidMoveException e) {
            e.printStackTrace();
        } finally {
            board.removePieces();
        }
        /*
        setChanged();
        notifyObservers(this);
        */
    }

    public void switchTurns(){
        whitesTurn = !whitesTurn;

        if(!whitesTurn){
            aiPlayer.Move(this);
        }
    }
}
