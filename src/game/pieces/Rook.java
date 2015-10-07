package game.pieces;

import game.board.Board;
import game.board.Tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Glenn on 3-10-2015.
 */
public class Rook extends Piece {
    @Override
    public List<Tile> getMoves(Board board) {
        List<Tile> result = new ArrayList<Tile>();
        String column = position.getLocation().substring(0, 1);
        int row = Integer.parseInt(position.getLocation().substring(1, 2));
        result.addAll(checkUp(board, column, row));
        result.addAll(checkDown(board, column, row));
        result.addAll(checkLeft(board, column, row));
        result.addAll(checkRight(board, column, row));
        return result;
    }

    public Rook(Tile position, String color) {
        super(position, color);
    }

    private List<Tile> checkUp(Board board, String column, int row) {
        List<Tile> result = new ArrayList<Tile>();
        for (int i = row + 1; i <= 8; i++) {
            Tile temp = board.getTile(column + i);
            if (temp.getPiece() == null) {
                result.add(temp);
            } else if (!temp.getPiece().getColor().equals(color)){
                result.add(temp);
                break;
            } else {
                break;
            }
        }
        return result;
    }

    private List<Tile> checkDown(Board board, String column, int row) {
        List<Tile> result = new ArrayList<Tile>();
        for (int i = row - 1; i >= 1; i--) {
            Tile temp = board.getTile(column + i);
            if (temp.getPiece() == null) {
                result.add(temp);
            } else if (!temp.getPiece().getColor().equals(color)){
                result.add(temp);
                break;
            } else {
                break;
            }
        }
        return result;
    }

    private List<Tile> checkLeft(Board board, String column, int row) {
        List<Tile> result = new ArrayList<Tile>();
        int columnIndex = Arrays.asList(board.getColumns()).indexOf(column);
        for(int i = columnIndex -1; i >= 1; i--) {
            Tile temp = board.getTile(board.getColumns()[i] + row);
            if (temp.getPiece() == null) {
                result.add(temp);
            } else if (!temp.getPiece().getColor().equals(color)){
                result.add(temp);
                break;
            } else {
                break;
            }
        }
        return result;
    }

    private List<Tile> checkRight(Board board, String column, int row) {
        List<Tile> result = new ArrayList<Tile>();
        int columnIndex = Arrays.asList(board.getColumns()).indexOf(column);
        for(int i = columnIndex +1; i <= 8; i++) {
            Tile temp = board.getTile(board.getColumns()[i] + row);
            if (temp.getPiece() == null) {
                result.add(temp);
            } else if (!temp.getPiece().getColor().equals(color)){
                result.add(temp);
                break;
            } else {
                break;
            }
        }
        return result;
    }
}
