package game.pieces;

import game.board.Board;
import game.board.Tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Glenn on 1-10-2015.
 */
public class Pawn extends Piece {


    public Pawn(Tile position, String color) {
        super(position, color);
    }

    @Override
    public List<Tile> getMoves(Board board) {
        List<Tile> result = new ArrayList<Tile>();
        Tile[] possibleTiles = getPossibleTiles(board);
        for(Tile tile : possibleTiles) {
            if(position.compareColumn(tile) == 0) {
                if(tile.getPiece() == null) {
                    result.add(tile);
                }
            } else if(tile.getPiece() != null && !tile.getPiece().getColor().equals(color)) {
                result.add(tile);
            }
        }
        return result;
    }

    private Tile[] getPossibleTiles(Board board) {
        String[] columns = board.getColumns();
        int modifier = color.equals("White") ? 1 : -1;
        String column = position.toString().substring(0, 1);
        int columnIndex = Arrays.asList(columns).indexOf(column);
        int row = Integer.parseInt(position.toString().substring(1, 1));
        String tilesString = "";
        if ((row == 2 && color.equals("White")) || (row == 7 && color.equals("Black"))) {
            tilesString = column + (row + (modifier * 2)) + ",";
        }
        tilesString += column + (row+modifier) + ",";
        switch (columnIndex) {
            case 0:
                tilesString += columns[columnIndex + 1] + (row+modifier)+",";
                break;
            case 7:
                tilesString += columns[columnIndex - 1] + (row+modifier) + ",";
                break;
            default:
                tilesString += columns[columnIndex + 1] + (row+modifier)+",";
                tilesString += columns[columnIndex - 1] + (row+modifier) + ",";
        }
        return board.getTiles(tilesString.split(","));
    }
}

