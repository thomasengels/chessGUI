package game.AI;

import game.Game;
import game.board.Board;

/**
 * Created by Glenn on 15-10-2015.
 */
public class AiPlayer {
    private MiniMax miniMax;
    public void Move(Game game) {
        miniMax = new MiniMax(game.getBoard(), 3);
        Node node = miniMax.calculate(new Node(game.getBoard(), Integer.MIN_VALUE), 3, true);
        System.out.printf("node");
    }

    public Node createNodes(Board board, int depth) {
        return new Node(board, depth);
    }
}
