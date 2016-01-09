package game.AI;

import game.Game;
import game.board.Move;

import java.util.Date;
import java.util.Observable;

/**
 * Created by Glenn on 15-10-2015.
 */
public class AiPlayer extends Observable {
    private MiniMax miniMax;
    private String move;
    public void Move(Game game) {
        System.out.println("Ai has started");
        miniMax = new MiniMax(game.getBoard(), 3);
        System.out.println(new Date());
        Node node = miniMax.calculate(new Node(game.getBoard(), Integer.MIN_VALUE), 3, true);
        String[] locations = node.getBestMove().split(",");
        game.getBoard().setLastMove(game.getBoard().getTile(locations[1]),game.getBoard().getTile(locations[0]).getPiece());
        move = node.getBestMove();


        setChanged();
        notifyObservers(this);


        game.move(move);
    }

    public Move getMove(Game game){
        return game.getBoard().getLastMove();
    }
}
