package game.AI;

import game.Game;
import game.board.Board;
import game.pieces.Piece;

import java.util.Arrays;

/**
 * Created by Thomas on 13/10/2015.
 */
public class MaterialValue {
    public int getMaterialValue(Board board) {
        int value = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.getTile(i,j).getPiece() != null && board.getTile(i, j).getPiece().getColor().equals("White")) {
                    value += board.getTile(i, j).getPiece().getValue();
                }
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board.getTile(i,j).getPiece() != null && board.getTile(i, j).getPiece().getColor().equals("Black")) {
                    value -= board.getTile(i, j).getPiece().getValue();
                }
            }
        }

        return value;
    }

    public int getPieceSquareTable(Board board) {
        int highest = 0;
        int lowest = 0;
        int total = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                /*
                if (board.getTile(i,j).getPiece() != null && board.getTile(i, j).getPiece().getClass().getSimpleName().equals(piece)) {
                    if(highest < board.getTile(i,j).getPiece().getPieceSquareValue(i,j)){
                        highest = board.getTile(i,j).getPiece().getPieceSquareValue(i,j);
                    }
                    else if(lowest > board.getTile(i,j).getPiece().getPieceSquareValue(i,j)){
                        lowest = board.getTile(i,j).getPiece().getPieceSquareValue(i,j);
                    }
                    */
                     if(board.getTile(i,j).getPiece() != null) {
                         if (board.getTile(i, j).getPiece().getColor().equals("White")){
                             total += board.getTile(i,j).getPiece().getPieceSquareValue(i,j);
                         }
                         else{
                             total -= board.getTile(i,j).getPiece().getPieceSquareValue(i,j);
                         }
                }



                }
            }
        return total;
    }

    public int getMobility(Board board){
        int nrMoves = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if( board.getTile(i,j).getPiece() != null) {
                    if(board.getTile(i, j).getPiece().getColor().equals("White")){
                        nrMoves += board.getTile(i,j).getPiece().getMoves(board).size();
                    }
                    else{
                        nrMoves -= board.getTile(i,j).getPiece().getMoves(board).size();
                    }
                }
            }
        }

        return nrMoves;
    }

    public double kingSafety(Board board){
        double closestValue = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board.getTile(i,j).getPiece() != null && calculateEuclidicDistance(board, board.getTile(i,j).getPiece()) < closestValue){
                    closestValue = calculateEuclidicDistance(board, board.getTile(i,j).getPiece());
                }
            }
        }

        return closestValue;
    }

    public double calculateEuclidicDistance(Board board, Piece piece){
        int xofKing = 0;
        int yofKing = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board.getTile(i,j).getPiece() != null && board.getTile(i,j).getPiece().getClass().getSimpleName().equals("King")){
                    if(!board.getTile(i,j).getPiece().getColor().equals(piece.getColor())){
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
