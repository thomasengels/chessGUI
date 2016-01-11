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
    private final short[] KnightTable = new short[]
            {
                    -50,-40,-30,-30,-30,-30,-40,-50,
                    -40,-20,  0,  0,  0,  0,-20,-40,
                    -30,  0, 10, 15, 15, 10,  0,-30,
                    -30,  5, 15, 20, 20, 15,  5,-30,
                    -30,  0, 15, 20, 20, 15,  0,-30,
                    -30,  5, 10, 15, 15, 10,  5,-30,
                    -40,-20,  0,  5,  5,  0,-20,-40,
                    -50,-40,-20,-30,-30,-20,-40,-50,
            };

    public int getPieceSquareValue(int x, int y){
        int index = (x * (y - 1)) + y;

        return KnightTable[index];
    }
    private int value = 30;

    public int getValue(){
        return value;
    }

    public Knight(Tile position, String color) {
        super(position, color);
    }

    @Override
    public List<Tile> getMoves(Board board) {
        List<Tile> result = new ArrayList<Tile>();
        String column = position.getLocation().substring(0, 1);
        int columnIndex = Arrays.asList(board.getColumns()).indexOf(column);
        int row = Integer.parseInt(position.getLocation().substring(1, 2));
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
