package GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by thomas on 1/10/2015.
 */
public class Tile {
    private int[] location;
    private String chesspiece;
    private Image chesspieceImage;
    private Image chessboardTileImage;
    private ImageView checkboardTileImageView;
    private ImageView chesspieceImageView;

    public Tile(int[] location, String chesspiece) {
        this.location = location;
        this.chesspiece = chesspiece;
        setChesspiece(this.chesspiece);
    }

    public Tile(){
        chesspieceImageView = null;
        chesspieceImage = null;
    }

    public String getChesspiece() {
        return chesspiece;
    }

    public void setChesspiece(String chesspiece) {
        this.chesspiece = chesspiece;
        setChesspieceImage(new Image(getClass().getResourceAsStream(chesspiece + ".png")));
    }

    public Image getChesspieceImage() {
        return chesspieceImage;
    }

    public void setChesspieceImage(Image chesspieceImage) {
        this.chesspieceImage = chesspieceImage;
        this.chesspieceImageView = new ImageView(chesspieceImage);
    }

    public Image getChessboardTileImage() {
        return chessboardTileImage;
    }

    public void setChessboardTileImage(String chessboardTileImage) {
        this.chessboardTileImage = new Image(getClass().getResourceAsStream(chessboardTileImage + ".jpg"));
        setCheckboardTileImageView(new ImageView(this.chessboardTileImage));
    }

    public ImageView getCheckboardTileImageView() {
        return checkboardTileImageView;
    }

    public void setCheckboardTileImageView(ImageView checkboardTileImageView) {
        this.checkboardTileImageView = checkboardTileImageView;
    }

    public ImageView getChesspieceImageView() {
        return chesspieceImageView;
    }

    public void setChesspieceImageView(ImageView chesspieceImageView) {
        this.chesspieceImageView = chesspieceImageView;
        setChesspieceImage(this.chesspieceImageView.getImage());
    }

    public int[] getLocation() {
        return location;
    }

    public void setLocation(int x, int y) {
        this.location = new int[]{x, y};
    }

    public void removeChessPiece(){
        this.chesspiece = null;
        this.chesspieceImageView = null;
        this.chesspieceImage = null;
    }

    /*
    public void setChesspiece(String chesspiece) {
        if(chesspiece.contains("chessboard")){
            chesspieceImage = new Image(getClass().getResourceAsStream(chesspiece + ".jpg"));
        }
        else{
            chesspieceImage = new Image(getClass().getResourceAsStream(chesspiece + ".png"));
        }

        checkboardTile = new ImageView(chesspieceImage);
        this.chesspiece = chesspiece;
    }
    */
}
