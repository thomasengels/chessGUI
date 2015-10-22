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
            Node bestvalue = new Node(state.getBoard(), Integer.MIN_VALUE);
            for(Piece piece: state.getBoard().getPieces()) {
                Node temp = new Node(state);
                Board board = temp.getBoard();
                if(piece.getColor().equals("Black")) {
                    List<Tile> moves = piece.getMoves(board);
                    for (Tile tile : piece.getMoves(board)) {
                        try {
                            piece.move(board, tile);
                        } catch (InvalidMoveException e) {
                            e.printStackTrace();
                        }
                        Node node = calculate(temp, depth - 1, false);
                        bestvalue = bestvalue.getValue() < node.getValue() ? node : bestvalue;
                    }
                }
            }
            return bestvalue;
        }else {
            Node bestvalue = new Node(state.getBoard(), Integer.MAX_VALUE);
            for(Piece piece: state.getBoard().getPieces()) {
                Node temp = new Node(state);
                Board board = temp.getBoard();
                if (piece.getColor().equals("White")) {
                    for (Tile tile : piece.getMoves(state.getBoard())) {
                        try {
                            piece.move(board, tile);
                        } catch (InvalidMoveException e) {
                            e.printStackTrace();
                        }
                        Node node = calculate(temp, depth - 1, true);
                        bestvalue = bestvalue.getValue() > node.getValue() ? node : bestvalue;
                    }
                }
            }
            return bestvalue;
        }
    }
}

