package game.AI;

import game.Game;
import game.board.Board;
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

    public MiniMax(Board board, int depth) {
        this.node = new Node(board, depth);
    }

    public MiniMax(Node node) {
        this.node = node;
    }

    public Node calculate(Node state, int depth, boolean max) {
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
                        try {
                            tempPiece.move(temp.getBoard(), tempTile);
                        } catch (InvalidMoveException e) {
                            e.printStackTrace();
                        }
                        Node node = calculate(temp, depth - 1, false);
                        bestValue = bestValue.getValue() < node.getValue() ? node : bestValue;
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
                        try {
                            tempPiece.move(temp.getBoard(), tempTile);
                        } catch (InvalidMoveException e) {
                            e.printStackTrace();
                        }
                        Node node = calculate(temp, depth - 1, true);
                        bestValue = bestValue.getValue() > node.getValue() ? node : bestValue;
                    }
                }
            }
            return bestValue;
        }
    }
}

