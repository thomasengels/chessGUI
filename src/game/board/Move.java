package game.board;

import game.pieces.Piece;

/**
 * Created by Glenn on 27-10-2015.
 */
public class Move {
    private Tile location;
    private Piece piece;

    public Move(Tile location, Piece piece) {
        this.location = location;
        this.piece = piece;
    }

    public Tile getLocation() {
        return location;
    }

    public Piece getPiece() {
        return piece;
    }
}
