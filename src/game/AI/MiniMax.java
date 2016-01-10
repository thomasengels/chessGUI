package game.AI;

import game.Game;
import game.board.Board;
import game.board.Move;
import game.board.Tile;
import game.errors.InvalidMoveException;
import game.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Glenn on 15-10-2015.
 */
public class MiniMax {
    private Evaluation ev = new Evaluation();
    private Node node;
    private int counter = 0;

    public int getCounter() {
        return counter;
    }

    public MiniMax(Board board, int depth) {
        this.node = new Node(board, depth);
    }

    public MiniMax(Node node) {
        this.node = node;
    }

    public Node calculate(Node state, int depth, boolean max) {
        counter++;
        if(depth == 0) {
            state.setValue(ev.getEvaluationValue(state));
            return state;
        }
        if (max){
            Node bestValue = new Node(state.getBoard(), Integer.MIN_VALUE);
            Board board = bestValue.getBoard();
            for(Piece piece: board.getPieces()) {
                if(piece.getColor().equals("Black")) {
                    for (Tile tile : piece.getMoves(board)) {
                        Node temp = new Node(state);
                        Tile tempTile = temp.getBoard().getTile(tile.getLocation());
                        Piece tempPiece = temp.getBoard().getTile(piece.getPosition().getLocation()).getPiece();
                        Move move = null;
                        String oldLocation = tempPiece.getPosition().getLocation();
                        try {
                            tempPiece.move(temp.getBoard(), tempTile);
                            move = new Move(tempTile, tempPiece);
                            move.setOldLocation(oldLocation);

                        } catch (InvalidMoveException e) {
                            e.printStackTrace();
                        }
                        Node node = calculate(temp, depth - 1, false);
                        if(bestValue.getValue() < node.getValue()) {
                            bestValue = node;
                            bestValue.addMove(depth, move);
                        }
                        //bestValue = bestValue.getValue() < node.getValue() ? node : bestValue;
                    }
                }
            }
            return bestValue;
        }else {
            Node bestValue = new Node(state.getBoard(), Integer.MAX_VALUE);
            Board board = bestValue.getBoard();
            for(Piece piece: board.getPieces()) {
                if (piece.getColor().equals("White")) {
                    for (Tile tile : piece.getMoves(state.getBoard())) {
                        Node temp = new Node(state);
                        Tile tempTile = temp.getBoard().getTile(tile.getLocation());
                        Piece tempPiece = temp.getBoard().getTile(piece.getPosition().getLocation()).getPiece();
                        Move move = null;
                        String oldLocation = tempPiece.getPosition().getLocation();
                        try {
                            tempPiece.move(temp.getBoard(), tempTile);
                            move = new Move(tempTile, tempPiece);
                            move.setOldLocation(oldLocation);
                        } catch (InvalidMoveException e) {
                            e.printStackTrace();
                        }
                        Node node = calculate(temp, depth - 1, true);
                        if(bestValue.getValue() > node.getValue()) {
                            bestValue = node;
                            bestValue.addMove(depth, move);
                        }
                        //bestValue = bestValue.getValue() > node.getValue() ? node : bestValue;
                    }
                }
            }
            return bestValue;
        }
    }

    public Node alphaBetaMax(Node state, int alpha, int beta, int depth) {
        if (depth == 0) {
            state.setValue(ev.getEvaluationValue(state));
            return state;
        }
        Node bestValue = new Node(state.getBoard(), alpha);
        Board board = bestValue.getBoard();
        for(Piece piece: board.getPieces()) {
            if(piece.getColor().equals("Black")) {
                for (Tile tile : piece.getMoves(board)) {
                    Node temp = new Node(state);
                    Tile tempTile = temp.getBoard().getTile(tile.getLocation());
                    Piece tempPiece = temp.getBoard().getTile(piece.getPosition().getLocation()).getPiece();
                    Move move = null;
                    String oldLocation = tempPiece.getPosition().getLocation();
                    try {
                        tempPiece.move(temp.getBoard(), tempTile);
                        move = new Move(tempTile, tempPiece);
                        move.setOldLocation(oldLocation);

                    } catch (InvalidMoveException e) {
                        e.printStackTrace();
                    }
                    Node node = alphaBetaMin(temp, alpha, beta, depth - 1);
                    if(node.getValue() >= beta) {
                        return node;
                    }

                    //bestValue = bestValue.getValue() < node.getValue() ? node : bestValue;
                }
            }
        }
        return bestValue;
    }

    private Node alphaBetaMin(Node state, int alpha, int beta, int depth) {
        if (depth == 0) {
            state.setValue(ev.getEvaluationValue(state));
            return state;
        }
        Node bestValue = new Node(state.getBoard(), Integer.MAX_VALUE);
        Board board = bestValue.getBoard();
        for(Piece piece: board.getPieces()) {
            if (piece.getColor().equals("White")) {
                for (Tile tile : piece.getMoves(state.getBoard())) {
                    Node temp = new Node(state);
                    Tile tempTile = temp.getBoard().getTile(tile.getLocation());
                    Piece tempPiece = temp.getBoard().getTile(piece.getPosition().getLocation()).getPiece();
                    Move move = null;
                    try {
                        tempPiece.move(temp.getBoard(), tempTile);
                        move = new Move(tempTile, tempPiece);
                    } catch (InvalidMoveException e) {
                        e.printStackTrace();
                    }
                    Node node = calculate(temp, depth - 1, true);
                    if(bestValue.getValue() > node.getValue()) {
                        bestValue = node;
                        bestValue.addMove(depth, move);
                    }
                    bestValue = bestValue.getValue() > node.getValue() ? node : bestValue;
                }
            }
        }
        return bestValue;
    }
}

