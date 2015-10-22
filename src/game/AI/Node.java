package game.AI;

import game.board.Board;
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

    public Node(Node node) {
        this(node.board, node.value);
    }

    public Node(Board board, int value) {
        this.board = new Board(board);
        this.value = value;
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
