package game.board;

import game.pieces.Piece;

/**
 * Created by Glenn on 27-10-2015.
 */
public class Move {
    private Tile location;
    private Piece piece;
    private String oldLocation;

    public static Move newInstance(Move previousMove){
        return new Move(previousMove.getLocation(),previousMove.getPiece());
    }

    public Move(Tile location, Piece piece) {
        this.location = location;
        this.piece = piece;
        try{
            this.oldLocation = piece.getPreviousPosition();
        }catch (NullPointerException ex){
            System.out.print(this.toString());
        }


        //System.out.println(this.oldLocation);
    }

    public Tile getLocation() {
        return location;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setOldLocation(String oldLocation) {
        this.oldLocation = oldLocation;
    }

    public String getOldLocation() {
        return oldLocation;
    }
}
