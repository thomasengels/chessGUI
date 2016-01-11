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
    private final short[][] PawnTable = new short[][]
            {
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {50, 50, 50, 50, 50, 50, 50, 50},
                    {10, 10, 20, 30, 30, 20, 10, 10},
                    {5, 5, 10, 27, 27, 10, 5, 5},
                    {0, 0, 0, 25, 25, 0, 0, 0},
                    {5, -5, -10, 0, 0, -10, -5, 5},
                    {5, 10, 10, -25, -25, 10, 10, 5},
                    {0, 0, 0, 0, 0, 0, 0, 0}
            };

    public int getPieceSquareValue(int x, int y) {

        return PawnTable[x][y];
    }

    private int value = 10;

    public int getValue() {
        return value;
    }

    public Pawn(Tile position, String color) {
        super(position, color);
    }

    @Override
    public List<Tile> getMoves(Board board) {
        List<Tile> result = new ArrayList<Tile>();
        Tile[] possibleTiles = getPossibleTiles(board);
        for (Tile tile : possibleTiles) {
            if (tile != null) {
                if (position.compareColumn(tile) == 0) {
                    if (tile.getPiece() == null) {
                        if (position.compareRow(tile) == 2) {
                            String column = position.toString().substring(0, 1);
                            int row = Integer.parseInt(position.toString().substring(1, 2));
                            column += (row + 1);
                            if (board.getTile(column).getPiece() != null) {
                                result.add(tile);
                            }
                        } else if (position.compareRow(tile) == -2) {
                            String column = position.toString().substring(0, 1);
                            int row = Integer.parseInt(position.toString().substring(1, 2));
                            column += (row - 1);
                            if (board.getTile(column).getPiece() != null) {
                                result.add(tile);
                            }
                        } else {
                            result.add(tile);
                        }
                    }
                } else if (tile.getPiece() != null && !tile.getPiece().getColor().equals(color)) {
                    result.add(tile);
                }
            }
        }
        return result;
    }

    private Tile[] getPossibleTiles(Board board) {
        String[] columns = board.getColumns();
        int modifier = color.equals("White") ? 1 : -1;
        String column = position.toString().substring(0, 1);
        int columnIndex = Arrays.asList(columns).indexOf(column);
        int row = Integer.parseInt(position.toString().substring(1, 2));
        String tilesString = "";
        if ((row == 2 && color.equals("White")) || (row == 7 && color.equals("Black"))) {
            tilesString = column + (row + (modifier * 2)) + ",";
        }
        tilesString += column + (row + modifier) + ",";
        switch (columnIndex) {
            case 0:
                tilesString += columns[columnIndex + 1] + (row + modifier) + ",";
                break;
            case 7:
                tilesString += columns[columnIndex - 1] + (row + modifier) + ",";
                break;
            default:
                tilesString += columns[columnIndex + 1] + (row + modifier) + ",";
                tilesString += columns[columnIndex - 1] + (row + modifier) + ",";
        }
        return board.getTiles(tilesString.split(","));
    }
}

