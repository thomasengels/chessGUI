package GUI;

import game.Game;
import game.errors.InvalidMoveException;
import game.pieces.*;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Created by thomas on 29/09/2015.
 */
public class SetupBoard{
    private BorderPane totalplayboard = new BorderPane();
    private GridPane checkboardPane = new GridPane();
    private VBox lostPieces = new VBox();
    private boolean inRepositionState = false;
    private Piece pieceForReposition;
    private Game game;

    public void setup(Game game){
        this.game = game;
        checkboardPane.setPadding(new Insets(1, 1, 1, 1));
        checkboardPane.setVgap(1);
        checkboardPane.setHgap(1);
        fillChecks();
        placePieces();
        checkboardPane.autosize();
        totalplayboard.setCenter(checkboardPane);
        totalplayboard.setLeft(lostPieces);
    }

    public void placePieces(){
        String[] order = {"rook","knight","bishop","queen","king","bishop","knight","rook"};
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 2; j++){
                game.getBoard().getTile(convertXYtoA1(i,j)).getPiece().setChesspieceImage(new Image(getClass().getResourceAsStream(
                        game.getBoard().getTile(convertXYtoA1(i,j)).getPiece().getClass().getSimpleName() +
                                game.getBoard().getTile(convertXYtoA1(i,j)).getPiece().getColor() + ".png")));
                game.getBoard().getTile(convertXYtoA1(i,j)).getPiece().getChesspieceImageView().setOnMouseClicked(e -> {
                    pieceForReposition = game.getBoard().getTile((int) e.getSceneX()/45, (int) e.getSceneY()/45).getPiece();
                    inRepositionState = true;
                    checkboardPane.getChildren().remove(e.getSource());
                });

                checkboardPane.setConstraints(game.getBoard().getTile(convertXYtoA1(i,j)).getPiece().getChesspieceImageView(), i, j);
                checkboardPane.getChildren().add(game.getBoard().getTile(convertXYtoA1(i,j)).getPiece().getChesspieceImageView());

            }
        }
        for(int i = 0; i < 8; i++){
            for(int j = 6; j < 8; j++){
                game.getBoard().getTile(convertXYtoA1(i,j)).getPiece().setChesspieceImage(new Image(getClass().getResourceAsStream(
                        game.getBoard().getTile(convertXYtoA1(i,j)).getPiece().getClass().getSimpleName() +
                                game.getBoard().getTile(convertXYtoA1(i,j)).getPiece().getColor() + ".png")));
                game.getBoard().getTile(convertXYtoA1(i,j)).getPiece().getChesspieceImageView().setOnMouseClicked(e -> {
                    pieceForReposition = game.getBoard().getTile((int) e.getSceneX()/45, (int) e.getSceneY()/45).getPiece();
                    inRepositionState = true;
                    checkboardPane.getChildren().remove(e.getSource());
                });
                checkboardPane.setConstraints(game.getBoard().getTile(convertXYtoA1(i,j)).getPiece().getChesspieceImageView(), i, j);
                checkboardPane.getChildren().add(game.getBoard().getTile(convertXYtoA1(i,j)).getPiece().getChesspieceImageView());

            }
        }

    }

    private void fillChecks(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if((i + 1)%2 != 0){
                    if((j + 1)%2 != 0){
                        game.getBoard().getTile(i, j).setChessboardTileImage("chessboardWhite");
                    }
                    else{
                        game.getBoard().getTile(i,j).setChessboardTileImage("chessboardGrey");
                    }
                }
                else{
                    if((j + 1)%2 != 0){
                        game.getBoard().getTile(i,j).setChessboardTileImage("chessboardGrey");
                    }
                    else{
                        game.getBoard().getTile(i,j).setChessboardTileImage("chessboardWhite");
                    }
                }


                game.getBoard().getTile(i,j).getChessboardTileImageView().setFitHeight(45);
                game.getBoard().getTile(i,j).getChessboardTileImageView().setFitWidth(45);
                game.getBoard().getTile(i,j).getChessboardTileImageView().autosize();
                game.getBoard().getTile(i, j).getChessboardTileImageView().setOnMouseClicked(e -> {
                    if (inRepositionState) {
                        try {
                            pieceForReposition.move(game.getBoard(), game.getBoard().getTile((int) e.getSceneX() / 45, (int) e.getSceneY() / 45));
                        } catch (InvalidMoveException e1) {
                            e1.printStackTrace();
                        }

                        checkboardPane.setConstraints(pieceForReposition.getChesspieceImageView(), ((int) e.getSceneX()) / 45, ((int) e.getSceneY()) / 45);
                        System.out.println(((int) e.getY()) / 8 + " " + ((int) e.getX()) / 8);
                        checkboardPane.getChildren().addAll(pieceForReposition.getChesspieceImageView());

                        inRepositionState = false;
                    }
                });

                checkboardPane.setConstraints(game.getBoard().getTile(i,j).getChessboardTileImageView(), i, j);

                checkboardPane.getChildren().add(game.getBoard().getTile(i,j).getChessboardTileImageView());
            }
        }
    }

    public BorderPane getTotalplayboard(){
        return totalplayboard;
    }

    public double[] getDimensions(){
        double[]  dimensions =  {checkboardPane.getWidth(),checkboardPane.getHeight()} ;
        return dimensions;
    }
    public String convertXYtoA1(int x, int y){
        String[] AtoH = {"A","B","C","D","E","F","G","H"};
        return AtoH[x] + Math.abs(y - 8);
    }

}
