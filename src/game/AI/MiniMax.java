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
    private int alpha;
    private int beta;
    private int counter = 0;
    private List<Move> moves;

    public int getCounter() {
        return counter;
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

    public Node startAlphaBeta(Node state, int depth){
        alpha = Integer.MIN_VALUE;
        beta = Integer.MAX_VALUE;
        moves = new ArrayList<>();
        Node n = alphaBetaMax(state, depth);
        n.setMoves(moves);
        return n;
    }

    private Node alphaBetaMax(Node state, int depth) {
        counter++;
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
                    Node node = alphaBetaMin(temp,depth - 1);
                    if(bestValue.getValue() < node.getValue()) {
                        bestValue = node;
                        addMove(depth, move);
                    }
                    if(node.getValue() >= beta) {
                        return node;
                    }
                    if (node.getValue() > alpha) {
                        alpha = node.getValue();
                    }

                    //bestValue = bestValue.getValue() < node.getValue() ? node : bestValue;
                }
            }
        }
        return bestValue;
    }

    private Node alphaBetaMin(Node state, int depth) {
        counter++;
        if (depth == 0) {
            state.setValue(ev.getEvaluationValue(state));
            return state;
        }
        Node bestValue = new Node(state.getBoard(), beta);
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
                    Node node = alphaBetaMax(temp, depth - 1);
                    if(bestValue.getValue() > node.getValue()) {
                        bestValue = node;
                        addMove(depth, move);
                    }
                    if(node.getValue() <= alpha) {
                        return node;
                    }
                    if(node.getValue() < beta) {
                        beta = node.getValue();
                    }
                }
            }
        }
        return bestValue;
    }

    public void addMove(int index, Move move) {
        try{
            moves.set(index - 1, move);
        } catch (Exception e) {
            moves.add(index - 1, move);
        }
    }
}

