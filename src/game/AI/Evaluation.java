package game.AI;

import game.Game;

/**
 * Created by Thomas on 13/10/2015.
 */
public class Evaluation {
    public int getEvaluationValue(Game game){
        MaterialValue mv = new MaterialValue();
        int value = 0;

        value += mv.getMaterialValue(game, true);

        value += mv.getMobility(game, true);

        value += mv.getPieceSquareTable(game, "Pawn");

        return value;
    }
}
