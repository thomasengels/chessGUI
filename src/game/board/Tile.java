package game.board;

import game.pieces.Piece;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Arrays;

/**
 * Created by Glenn on 1-10-2015.
 */
public class Tile{
    private final String location;
    private Piece piece;
    private Image chessboardTileImage;
    private ImageView chessboardTileImageView;

    public Tile(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int compareRow (Tile tile) {
        int thisRow = Integer.parseInt(location.substring(1, 1));
        int tileRow = Integer.parseInt(tile.getLocation().substring(1, 1));
        return thisRow-tileRow;
    }

    public int compareColumn( Tile tile) {
        String[] columns = new String[]{"A", "B", "C", "D", "E", "F", "G", "H"};
        return Arrays.asList(columns).indexOf(location.substring(0, 1)) - Arrays.asList(columns).indexOf(tile.getLocation().substring(0, 1));
    }

    @Override
    public String toString() {
        return location + " ";
    }

    public void setChessboardTileImage(String chessboardTileImage) {
        this.chessboardTileImage = new Image(getClass().getResourceAsStream(chessboardTileImage + ".jpg"));
        setCheckboardTileImageView(new ImageView(this.chessboardTileImage));
    }

    public ImageView getChessboardTileImageView() {
        return chessboardTileImageView;
    }

    public void setCheckboardTileImageView(ImageView checkboardTileImageView) {
        this.chessboardTileImageView = checkboardTileImageView;
    }

}
