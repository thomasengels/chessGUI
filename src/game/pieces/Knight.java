package game.pieces;

import game.board.Board;
import game.board.Tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Glenn on 6-10-2015.
 */
public class Knight extends Piece {

    public Knight(Tile position, String color) {
        super(position, color);
    }

    @Override
    List<Tile> getMoves(Board board) {
        List<Tile> result = new ArrayList<Tile>();
        String column = position.getLocation().substring(0, 1);
        int columnIndex = Arrays.asList(board.getColumns()).indexOf(column);
        int row = Integer.parseInt(position.getLocation().substring(1, 1));
        if(row + 1 != 9) {
            if(columnIndex + 2 < 8) {
                Tile temp = board.getTile(board.getColumns()[columnIndex + 2] + (row + 1));
                if(temp.getPiece() == null || !temp.getPiece().getColor().equals(color)) {
                    result.add(temp);
                }
            }
            if(columnIndex - 2 >= 0) {
                Tile temp = board.getTile(board.getColumns()[columnIndex - 2] + (row + 1));
                if(temp.getPiece() == null || !temp.getPiece().getColor().equals(color)) {
                    result.add(temp);
                }
            }
        }
        if (row + 2 < 9) {
            if(columnIndex + 1 < 8) {
                Tile temp = board.getTile(board.getColumns()[columnIndex + 1] + (row + 2));
                if(temp.getPiece() == null || !temp.getPiece().getColor().equals(color)) {
                    result.add(temp);
                }
            }
            if(columnIndex - 1 >= 0) {
                Tile temp = board.getTile(board.getColumns()[columnIndex - 1] + (row + 2));
                if(temp.getPiece() == null || !temp.getPiece().getColor().equals(color)) {
                    result.add(temp);
                }
            }
        }
        if(row - 1 > 0) {
            if(columnIndex + 2 < 8) {
                Tile temp = board.getTile(board.getColumns()[columnIndex + 2] + (row - 1));
                if(temp.getPiece() == null || !temp.getPiece().getColor().equals(color)) {
                    result.add(temp);
                }
            }
            if(columnIndex - 2 >= 0) {
                Tile temp = board.getTile(board.getColumns()[columnIndex - 2] + (row - 1));
                if(temp.getPiece() == null || !temp.getPiece().getColor().equals(color)) {
                    result.add(temp);
                }
            }
        }
        if(row - 2 > 0) {
            if(columnIndex + 1 < 8) {
                Tile temp = board.getTile(board.getColumns()[columnIndex + 1] + (row - 2));
                if(temp.getPiece() == null || !temp.getPiece().getColor().equals(color)) {
                    result.add(temp);
                }
            }
            if(columnIndex - 1 >= 0) {
                Tile temp = board.getTile(board.getColumns()[columnIndex - 1] + (row - 2));
                if(temp.getPiece() == null || !temp.getPiece().getColor().equals(color)) {
                    result.add(temp);
                }
            }
        }
        return result;
    }
}
