package game.pieces;

import game.board.Board;
import game.board.Tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Glenn on 6-10-2015.
 */
public class Queen extends Piece {
    private final Short[][] QueenTable = {
            {-20, -10, -10, -5, -5, -10, -10, -20},
            {-10, 0, 0, 0, 0, 0, 0, -10},
            {-10, 0, 5, 5, 5, 5, 0, -10},
            {-5, 0, 5, 5, 5, 5, 0, -5},
            {0, 0, 5, 5, 5, 5, 0, -5},
            {-10, 5, 5, 5, 5, 5, 0, -10},
            {-10, 0, 5, 0, 0, 0, 0, -10},
            {-20, -10, -10, -5, -5, -10, -10, -20}
    };

    public int getPieceSquareValue(int x, int y){
        return QueenTable[x][y];
    }
    private int value = 90;

    public int getValue(){
        return value;
    }
    public Queen(Tile position, String color) {
        super(position, color);
    }

    @Override
    public List<Tile> getMoves(Board board) {
        List<Tile> result = new ArrayList<Tile>();
        String column = position.getLocation().substring(0, 1);
        int row = Integer.parseInt(position.getLocation().substring(1, 2));
        result.addAll(checkLeftUp(board, column, row));
        result.addAll(checkLeftDown(board, column, row));
        result.addAll(checkRightDown(board, column, row));
        result.addAll(checkRightUP(board, column, row));
        result.addAll(checkUp(board, column, row));
        result.addAll(checkDown(board, column, row));
        result.addAll(checkLeft(board, column, row));
        result.addAll(checkRight(board, column, row));
        return result;
    }

    private List<Tile> checkLeftUp(Board board, String column, int row) {
        List<Tile> result = new ArrayList<Tile>();
        int columnIndex = Arrays.asList(board.getColumns()).indexOf(column);
        for(int i = 1; i <= 7; i++) {
            if(columnIndex - i >= 0  && row + i <= 8) {
                Tile temp = board.getTile(board.getColumns()[columnIndex - i] + (row +i));
                if (temp.getPiece() == null) {
                    result.add(temp);
                } else if (!temp.getPiece().getColor().equals(color)){
                    result.add(temp);
                    break;
                } else {
                    break;
                }
            }
        }
        return result;
    }

    private List<Tile> checkLeftDown(Board board, String column, int row) {
        List<Tile> result = new ArrayList<Tile>();
        int columnIndex = Arrays.asList(board.getColumns()).indexOf(column);
        for(int i = 1; i <= 7; i++) {
            if(columnIndex - i >= 0  && row - i >= 1) {
                Tile temp = board.getTile(board.getColumns()[columnIndex - i] + (row -i));
                if (temp.getPiece() == null) {
                    result.add(temp);
                } else if (!temp.getPiece().getColor().equals(color)){
                    result.add(temp);
                    break;
                } else {
                    break;
                }
            }
        }
        return result;
    }

    private List<Tile> checkRightUP(Board board, String column, int row) {
        List<Tile> result = new ArrayList<Tile>();
        int columnIndex = Arrays.asList(board.getColumns()).indexOf(column);
        for(int i = 1; i <= 7; i++) {
            if(columnIndex + i <= 7  && row + i <= 8) {
                Tile temp = board.getTile(board.getColumns()[columnIndex + i] + (row +i));
                if (temp.getPiece() == null) {
                    result.add(temp);
                } else if (!temp.getPiece().getColor().equals(color)){
                    result.add(temp);
                    break;
                } else {
                    break;
                }
            }
        }
        return result;
    }

    private List<Tile> checkRightDown(Board board, String column, int row) {
        List<Tile> result = new ArrayList<Tile>();
        int columnIndex = Arrays.asList(board.getColumns()).indexOf(column);
        for(int i = 1; i <= 7; i++) {
            if(columnIndex + i <= 7  && row - i >= 1) {
                Tile temp = board.getTile(board.getColumns()[columnIndex + i] + (row -i));
                if (temp.getPiece() == null) {
                    result.add(temp);
                } else if (!temp.getPiece().getColor().equals(color)){
                    result.add(temp);
                    break;
                } else {
                    break;
                }
            }
        }
        return result;
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
        for(int i = columnIndex -1; i >= 0; i--) {
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
        for(int i = columnIndex +1; i <= 7; i++) {
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
