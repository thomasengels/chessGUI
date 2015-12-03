package game.AI;

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
public class Node {
    private Board board;
    private int value;
    private List<Move> moves;

    public Node(Node node) {
        this(node.board, node.value, node.getMoves());
        node.getMoves().addAll(moves);
    }

    public List<Move> getMoves() {
        return moves;
    }

    public Node(Board board, int value, List<Move> moves) {
        this.board = new Board(board);
        this.value = value;
        this.moves = moves;
    }

    public void addMove(Move move) {
        moves.add(move);
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Board getBoard() {
        return board;
    }
}
