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
        miniMax = new MiniMax(game.getBoard(), 4, game.getMoves());
        System.out.println(new Date());
        Node node = miniMax.calculate(new Node(game.getBoard(), Integer.MIN_VALUE, new ArrayList<Move>()), 3, true);
        System.out.println(new Date());
    }
}
