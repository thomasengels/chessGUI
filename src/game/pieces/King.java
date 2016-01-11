package game.pieces;

import game.board.Board;
import game.board.Tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Glenn on 6-10-2015.
 */
public class King extends Piece {
    private final short[] KingTable = new short[]
            {
                    -30, -40, -40, -50, -50, -40, -40, -30,
                    -30, -40, -40, -50, -50, -40, -40, -30,
                    -30, -40, -40, -50, -50, -40, -40, -30,
                    -30, -40, -40, -50, -50, -40, -40, -30,
                    -20, -30, -30, -40, -40, -30, -30, -20,
                    -10, -20, -20, -20, -20, -20, -20, -10,
                    20,  20,   0,   0,   0,   0,  20,  20,
                    20,  30,  10,   0,   0,  10,  30,  20
            };

    private final short[] KingTableEndGame = new short[]
            {
                    -50,-40,-30,-20,-20,-30,-40,-50,
                    -30,-20,-10,  0,  0,-10,-20,-30,
                    -30,-10, 20, 30, 30, 20,-10,-30,
                    -30,-10, 30, 40, 40, 30,-10,-30,
                    -30,-10, 30, 40, 40, 30,-10,-30,
                    -30,-10, 20, 30, 30, 20,-10,-30,
                    -30,-30,  0,  0,  0,  0,-30,-30,
                    -50,-30,-30,-30,-30,-30,-30,-50
            };

    public int getPieceSquareValue(int x, int y){
        int index = (x * (y - 1)) + y;

        return KingTableEndGame[index];
    }

    private int value = 1000;

    public int getValue(){
        return value;
    }

    public King(Tile position, String color) {
        super(position, color);
    }

    @Override
    public List<Tile> getMoves(Board board) {
        List<Tile> result = new ArrayList<Tile>();
        String column = position.getLocation().substring(0, 1);
        int columnIndex = Arrays.asList(board.getColumns()).indexOf(column);
        int row = Integer.parseInt(position.getLocation().substring(1, 2));
        if (columnIndex - 1 >= 0) {
            if (row + 1 < 9) {
                Tile temp = board.getTile(board.getColumns()[columnIndex - 1] + (row + 1));
                if (temp.getPiece() == null || !temp.getPiece().getColor().equals(color)) {
                    result.add(temp);
                }
            }
            if (row - 1 > 0) {
                Tile temp = board.getTile(board.getColumns()[columnIndex - 1] + (row - 1));
                if (temp.getPiece() == null || !temp.getPiece().getColor().equals(color)) {
                    result.add(temp);
                }
            }
            Tile temp = board.getTile(board.getColumns()[columnIndex - 1] + (row));
            if (temp.getPiece() == null || !temp.getPiece().getColor().equals(color)) {
                result.add(temp);
            }
        }
        if (columnIndex + 1 < 8) {
            if (row + 1 < 9) {
                Tile temp = board.getTile(board.getColumns()[columnIndex + 1] + (row + 1));
                if (temp.getPiece() == null || !temp.getPiece().getColor().equals(color)) {
                    result.add(temp);
                }
            }
            if (row - 1 > 0) {
                Tile temp = board.getTile(board.getColumns()[columnIndex + 1] + (row - 1));
                if (temp.getPiece() == null || !temp.getPiece().getColor().equals(color)) {
                    result.add(temp);
                }
            }
            Tile temp = board.getTile(board.getColumns()[columnIndex + 1] + (row));
            if (temp.getPiece() == null || !temp.getPiece().getColor().equals(color)) {
                result.add(temp);
            }
        }
        if (row + 1 < 9) {
            Tile temp = board.getTile(board.getColumns()[columnIndex] + (row + 1));
            if (temp.getPiece() == null || !temp.getPiece().getColor().equals(color)) {
                result.add(temp);
            }
        }
        if (row - 1 > 0) {
            Tile temp = board.getTile(board.getColumns()[columnIndex] + (row - 1));
            if (temp.getPiece() == null || !temp.getPiece().getColor().equals(color)) {
                result.add(temp);
            }
        }
        return result;
    }
}
