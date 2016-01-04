package game.AI;

import game.board.Board;
import game.board.Move;
import game.board.Tile;
import game.errors.InvalidMoveException;
import game.pieces.Piece;

import java.lang.reflect.InvocationTargetException;
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
        this(node.board, node.value);
        moves = node.getMoves();
    }


    public Node(Board board, int value) {
        this.board = new Board(board);
        this.value = value;
        moves = new ArrayList<>();
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

    public List<Move> getMoves() {
        return moves;
    }

    public void addMove(int index, Move move) {
        try{
            moves.set(index - 1, move);
        } catch (Exception e) {
            moves.add(index - 1, move);
        }
    }

    public String getBestMove() {
        Move move = moves.get(0);
        return move.getOldLocation() + "," + move.getLocation().getLocation();
    }
}
