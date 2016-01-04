package game.board;

import game.pieces.*;

import javax.rmi.CORBA.Tie;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Glenn on 1-10-2015.
 */
public class Board implements Cloneable{
    private Tile[][] tiles;
    private String[] columns;
    private List<Piece> pieces;


    public List<Piece> getPieces() {
        return pieces;
    }

    public void addPiece(Piece piece) {
        pieces.add(piece);
    }

    public void removePieces() {
        for (Iterator<Piece> it = pieces.iterator(); it.hasNext();) {
            Piece temp = it.next();
            if(temp.getPosition() == null) {
                System.out.printf("removed %s", temp);
                it.remove();
            }
        }
    }

    public Board(Board board) {
        this();
        for(Tile[] tileArray : tiles) {
            for(Tile tile : tileArray) {
                Piece piece = board.getTile(tile.getLocation()).getPiece();
                if(piece != null) {
                    Piece newPiece;
                    if(piece instanceof Pawn) {
                       newPiece = new Pawn(tile, piece.getColor());
                    } else if(piece instanceof Rook) {
                        newPiece = new Rook(tile, piece.getColor());
                    } else if( piece instanceof Knight) {
                        newPiece = new Knight(tile, piece.getColor());
                    } else if(piece instanceof Bishop) {
                        newPiece = new Bishop(tile, piece.getColor());
                    } else if(piece instanceof Queen) {
                        newPiece = new Queen(tile, piece.getColor());
                    } else {
                        newPiece = new King(tile, piece.getColor());
                    }
                    tile.setPiece(newPiece);
                    pieces.add(newPiece);
                }
            }
        }
    }

    public Board() {
        pieces = new ArrayList<>();
        tiles = new Tile[8][8];
        columns = new String[] {"A", "B", "C", "D", "E", "F", "G", "H"};
        for(int i = 0; i < 8 ; i++) {
            for(int j=0; j < 8; j++) {
                tiles[7-i][j] = new Tile(columns[j]+  (i + 1));
            }
        }
    }

    public Tile getTile(String position) {
        Tile result = null;
        for(Tile[] items : tiles) {
            for(Tile tile : items) {
                if(tile.toString().trim().equals(position)) {
                    result = tile;
                    break;
                }
            }
        }
        return result;
    }

    public Tile getTile(int x, int y) {
        return tiles[y][x];
    }



    public Tile[] getTiles(String[] positions) {
        Tile[] result = new Tile[positions.length];
        int counter = 0;
        for(String string : positions) {
            result[counter] = getTile(string);
            counter++;
        }
        return result;
    }

    public String[] getColumns() {
        return columns;
    }

    @Override
    public String toString() {
        String result = "";
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                result += tiles[i][j];
            }
            result += "\n";
        }
        return result;
    }

    public String getMove(Board board) {
        String from = "";
        String to = "";
        for(Tile[] tileArray : tiles) {
            for(Tile tile : tileArray) {
                Tile tempTile = board.getTile(tile.getLocation());
                if((tile.getPiece() == null && tempTile.getPiece() != null) || (tile.getPiece() != null && tempTile.getPiece() != null && !tempTile.getPiece().getColor().equals(tile.getPiece().getColor()))) {
                    to = tempTile.getLocation();
                } else if(tile.getPiece() != null && tempTile.getPiece() == null) {
                    from = tile.getLocation();
                }
            }
        }
        return from + "," + to;
    }
}
