package GUI;

import com.sun.org.apache.xpath.internal.SourceTree;
import game.AI.Evaluation;
import game.Game;
import game.board.Board;
import game.board.Move;
import game.board.Tile;
import game.errors.InvalidMoveException;
import game.pieces.*;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class SetupBoardRefactor implements Observer{
    private BorderPane totalplayboard = new BorderPane();
    private GridPane checkboardPane = new GridPane();
    private VBox lostPieces = new VBox();
    private boolean inRepositionState = false;
    private Piece pieceForReposition;
    private Game game;
    private Evaluation ev;

    public void setup(Game game){
        ev = new Evaluation();
        this.game = game;
        checkboardPane.setPadding(new Insets(1, 1, 1, 1));
        checkboardPane.setVgap(1);
        checkboardPane.setHgap(1);
        checkboardPane.setGridLinesVisible(true);
        fillChecks();
        placePieces();
        checkboardPane.autosize();
        totalplayboard.setCenter(checkboardPane);
        totalplayboard.setLeft(lostPieces);
    }

    public void placePieces(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 2; j++){
                game.getBoard().getTile(convertXYtoA1(i,j)).getPiece().setChesspieceImage(new Image(getClass().getResourceAsStream(
                        game.getBoard().getTile(convertXYtoA1(i,j)).getPiece().getClass().getSimpleName() +
                                game.getBoard().getTile(convertXYtoA1(i,j)).getPiece().getColor() + ".png")));
                game.getBoard().getTile(convertXYtoA1(i,j)).getPiece().getChesspieceImageView().setOnMouseClicked(e -> {
                    //System.out.println(inRepositionState);
                    if(!inRepositionState) {
                        /*
                        if you have selected a piece, but you change your mind, the suggested move tile color (blue) will be reset to black or white
                         */
                        if (pieceForReposition != null) {
                            repaintBoardInitial(pieceForReposition.getMoves(game.getBoard()));
                        }
                        /*
                        if lostpieces vbox is visible, the extra width of screen has to be taken in account
                        */
                        if(lostPieces.getWidth() > 0){
                            pieceForReposition = game.getBoard().getTile(convertXYtoA1((int) (e.getSceneX() / 45) - 1, (int) e.getSceneY() / 45)).getPiece();
                        }
                        else{
                            pieceForReposition = game.getBoard().getTile(convertXYtoA1((int) e.getSceneX() / 45, (int) e.getSceneY() / 45)).getPiece();
                        }
                        inRepositionState = true;
                        paintPossibleMoves(pieceForReposition.getMoves(game.getBoard()));
                        checkboardPane.getChildren().remove(e.getSource());
                    }
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
                    if(!inRepositionState) {
                        /*
                        if you have selected a piece, but you change your mind, the suggested move tile color (blue) will be reset to black or white
                         */
                        if (pieceForReposition != null) {
                            repaintBoardInitial(pieceForReposition.getMoves(game.getBoard()));
                        }
                        /*
                        if lostpieces vbox is visible, the extra width of screen has to be taken in account
                        */
                        if(lostPieces.getWidth() > 0){
                            pieceForReposition = game.getBoard().getTile(convertXYtoA1((int) (e.getSceneX() / 45) - 1, (int) e.getSceneY() / 45)).getPiece();
                        }
                        else{
                            pieceForReposition = game.getBoard().getTile(convertXYtoA1((int) e.getSceneX() / 45, (int) e.getSceneY() / 45)).getPiece();
                        }

                        inRepositionState = true;
                        paintPossibleMoves(pieceForReposition.getMoves(game.getBoard()));
                        checkboardPane.getChildren().remove(e.getSource());
                        //System.out.println("This is the new evaluation value for white: " + ev.getEvaluationValue(game));
                    }
                });
                checkboardPane.setConstraints(game.getBoard().getTile(convertXYtoA1(i, j)).getPiece().getChesspieceImageView(), i, j);
                checkboardPane.getChildren().add(game.getBoard().getTile(convertXYtoA1(i, j)).getPiece().getChesspieceImageView());

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

                game.getBoard().getTile(i,j).setTilePossibleMoveImage(new Image(getClass().getResourceAsStream("chessboardBlue.jpg")));
                game.getBoard().getTile(i,j).getChessboardTileImageView().setFitHeight(45);
                game.getBoard().getTile(i,j).getChessboardTileImageView().setFitWidth(45);
                game.getBoard().getTile(i,j).getChessboardTileImageView().autosize();
                game.getBoard().getTile(i, j).getChessboardTileImageView().setOnMouseClicked(e -> {
                    Task updateUI = new Task() {
                        @Override
                        protected Object call() throws Exception {
                            Move move = null;
                            if (inRepositionState) {
                                repaintBoardInitial(pieceForReposition.getMoves(game.getBoard()));
                            /*
                              if lostpieces vbox is visible, the extra width of screen has to be taken in account  (int) (e.getSceneX() / 45) - 1
                             */
                                if(lostPieces.getWidth() > 0) {
                                    move = new Move(game.getBoard().getTile(convertXYtoA1((int) (e.getSceneX() / 45) - 1, (int) e.getSceneY() / 45)), pieceForReposition);
                                }
                                else {
                                    move = new Move(game.getBoard().getTile(convertXYtoA1((int) e.getSceneX() / 45, (int) e.getSceneY() / 45)), pieceForReposition);
                                }
                            }
                            inRepositionState = false;

                            return move;
                        }
                    };

                    new Thread(updateUI).run();
                    try {
                        game.move((Move) updateUI.get());
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    } catch (ExecutionException e1) {
                        e1.printStackTrace();
                    }

                });

                checkboardPane.setConstraints(game.getBoard().getTile(i, j).getChessboardTileImageView(), i, j);

                checkboardPane.getChildren().add(game.getBoard().getTile(i, j).getChessboardTileImageView());
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

    public int[] convertA1toXY(String A1){
        Character[] AtoH = {'A','B','C','D','E','F','G','H'};
        return new int[]{Integer.parseInt(String.valueOf(Arrays.asList(AtoH).indexOf(A1.charAt(0)))), Math.abs(8 - Integer.parseInt(String.valueOf(A1.charAt(1))))};
    }

    public void paintPossibleMoves(List<Tile> moves){
        for(Tile tile : moves){
            ((ImageView) checkboardPane.getChildren().get(
                    checkboardPane.getChildren().indexOf(
                            tile.getChessboardTileImageView()))).setImage(
                    tile.getTilePossibleMoveImage());
        }
    }

    public void repaintBoardInitial(List<Tile> moves){
        for(Tile tile : moves){
            ((ImageView) checkboardPane.getChildren().get(
                    checkboardPane.getChildren().indexOf(
                            tile.getChessboardTileImageView()))).setImage(
                    tile.getInitialTileColorImage());
        }
    }

    public boolean checkIfTileOccupid(Tile tile){
        if(game.getBoard().getTile(tile.getLocation()).getPiece() != null){
            return true;
        }
        return false;
    }

    public void takePieceFromBoard(Tile tile){
        lostPieces.getChildren().add(game.getBoard().getTile(tile.getLocation()).getPiece().getChesspieceImageView());
        checkboardPane.getChildren().remove(game.getBoard().getTile(tile.getLocation()).getPiece().getChesspieceImageView());
    }

    @Override
    public void update(Observable o, Object arg) {
        Task updateUI = new Task() {
            @Override
            protected Object call() throws Exception {
                if (!((Game) arg).isWhitesTurn()) {

                    if (checkIfTileOccupid(((Game) arg).getBoard().getLastMove().getLocation())) {
                        takePieceFromBoard(((Game) arg).getBoard().getLastMove().getLocation());
                    }
                    checkboardPane.setConstraints(((Game) arg).getBoard().getLastMove().getPiece().getChesspieceImageView(), convertA1toXY(((Game) arg).getBoard().getLastMove().getLocation().getLocation())[0], convertA1toXY(((Game) arg).getBoard().getLastMove().getLocation().getLocation())[1]);


                    checkboardPane.getChildren().addAll(((Game) arg).getBoard().getLastMove().getPiece().getChesspieceImageView());

                    System.out.println("AI is done");
                } else {
                    if (checkIfTileOccupid(((Game) arg).getBoard().getLastMove().getLocation())) {
                        takePieceFromBoard(((Game) arg).getBoard().getLastMove().getLocation());
                    }

                    checkboardPane.setConstraints(((Game) arg).getBoard().getLastMove().getPiece().getChesspieceImageView(), convertA1toXY(((Game) arg).getBoard().getLastMove().getLocation().getLocation())[0], convertA1toXY(((Game) arg).getBoard().getLastMove().getLocation().getLocation())[1]);


                    checkboardPane.getChildren().addAll(((Game) arg).getBoard().getLastMove().getPiece().getChesspieceImageView());
                }
                return null;
            }
        };



        game = (Game) o;
        game.switchTurns();

    }
}