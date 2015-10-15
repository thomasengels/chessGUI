package game.AI;

import game.Game;
import game.pieces.Piece;

import java.util.Arrays;

/**
 * Created by Thomas on 13/10/2015.
 */
public class MaterialValue {
    public int getMaterialValue(Game game, boolean whiteSide) {
        int value = 0;
        if (whiteSide) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (game.getBoard().getTile(i, j).getPiece().getColor().equals("White")) {
                        value += game.getBoard().getTile(i, j).getPiece().getValue();
                    }
                }
            }
        } else{
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (game.getBoard().getTile(i, j).getPiece().getColor().equals("Black")) {
                        value += game.getBoard().getTile(i, j).getPiece().getValue();
                    }
                }
            }
        }

        return value;
    }

    public int getPieceSquareTable(Game game, String piece) {
        int highest = 0;
        int lowest = 0;
        int total = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (game.getBoard().getTile(i, j).getPiece().getClass().getSimpleName().equals(piece)) {
                    if(highest < game.getBoard().getTile(i,j).getPiece().getPieceSquareValue(i,j)){
                        highest = game.getBoard().getTile(i,j).getPiece().getPieceSquareValue(i,j);
                    }
                    else if(lowest > game.getBoard().getTile(i,j).getPiece().getPieceSquareValue(i,j)){
                        lowest = game.getBoard().getTile(i,j).getPiece().getPieceSquareValue(i,j);
                    }

                    total += game.getBoard().getTile(i,j).getPiece().getPieceSquareValue(i,j);;
                }
            }
        }

        return total;
    }

    public int getMobility(Game game, boolean WhiteSide){
        int nrMoves = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                nrMoves += game.getBoard().getTile(i,j).getPiece().getMoves(game.getBoard()).size();
            }
        }

        return nrMoves;
    }

    public double kingSafety(Game game){
        double closestValue = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(calculateEuclidicDistance(game, game.getBoard().getTile(i,j).getPiece()) < closestValue){
                    closestValue = calculateEuclidicDistance(game, game.getBoard().getTile(i,j).getPiece());
                }
            }
        }

        return closestValue;
    }

    public double calculateEuclidicDistance(Game game, Piece piece){
        int xofKing = 0;
        int yofKing = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(game.getBoard().getTile(i,j).getPiece().getClass().getSimpleName().equals("King")){
                    if(!game.getBoard().getTile(i,j).getPiece().getColor().equals(piece.getColor())){
                        xofKing = i;
                        yofKing = j;
                    }
                }
            }
        }

        int xofPiece = convertA1toXY(piece.getPosition().getLocation()).charAt(0);
        int yofPiece = convertA1toXY(piece.getPosition().getLocation()).charAt(1);

        int absValueBase = Math.abs(xofKing - xofPiece);
        int absValueHight = Math.abs(yofKing - yofPiece);


        return Math.hypot(absValueBase, absValueHight);


    }


    public String convertA1toXY(String A1){
        String[] AtoH = {"A","B","C","D","E","F","G","H"};
        return Arrays.asList(AtoH).indexOf(A1.charAt(0)) + String.valueOf(Character.toString((char) Math.abs(A1.charAt(1) - 8)));
    }

}
