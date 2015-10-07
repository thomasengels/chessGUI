package game.pieces;

import game.board.Board;
import game.board.Tile;
import game.errors.InvalidMoveException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Glenn on 1-10-2015.
 */
public abstract class Piece {
    protected Tile position;
    protected String color;
    private Image chesspieceImage;
    private ImageView chesspieceImageView;


    public Piece(Tile position, String color) {
        this.color = color;
        this.position = position;
    }

    public void move(Board board, Tile position) throws InvalidMoveException {
        if (getMoves(board).contains(position)) {
            this.position = position;
            position.setPiece(this);
        } else {
            throw new InvalidMoveException("This piece can't move there.");
        }
    }

    public Image getChesspieceImage() {
        return chesspieceImage;
    }

    public void setChesspieceImage(Image chesspieceImage) {
        this.chesspieceImage = chesspieceImage;
        this.chesspieceImageView = new ImageView(chesspieceImage);
    }

    public ImageView getChesspieceImageView() {
        return chesspieceImageView;
    }

    public void setChesspieceImageView(ImageView chesspieceImageView) {
        this.chesspieceImageView = chesspieceImageView;
    }

    abstract List<Tile> getMoves(Board board);

    public String getColor() {
        return color;
    }

    public Tile getPosition() {
        return position;
    }
}

