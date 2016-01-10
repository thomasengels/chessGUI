package game.AI;

import game.Game;
import game.board.Board;
import game.board.Move;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Glenn on 15-10-2015.
 */
public class AiPlayer {
    private MiniMax miniMax;
    public void Move(Game game) {
        miniMax = new MiniMax(game.getBoard(), 4);
        System.out.println(new Date());
        Node node = miniMax.calculate(new Node(game.getBoard(), Integer.MIN_VALUE), 4, true);
        game.move(node.getBestMove());
        System.out.println(new Date());
    }
}
