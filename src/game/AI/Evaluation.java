package game.AI;

import game.Game;
import game.board.Board;

/**
 * Created by Thomas on 13/10/2015.
 */
public class Evaluation {
    public int getEvaluationValue(Node node){
        MaterialValue mv = new MaterialValue();
        int value = 0;
        Board game = node.getBoard();
        value += mv.getMaterialValue(game, false);

        //value += mv.getMobility(game, false);

        //value += mv.getPieceSquareTable(game, "Pawn");

        return value;
    }
}
