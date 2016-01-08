package game.AI;

import game.Game;
import game.Player;
import game.board.Board;
import game.board.Move;
import game.pieces.Piece;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Glenn on 15-10-2015.
 */
public class AiPlayer{
    private MiniMax miniMax;
    private String move;
    public void Move(Game game) {
        System.out.println("Ai has started");
        miniMax = new MiniMax(game.getBoard(), 4);
        System.out.println(new Date());
        Node node = miniMax.calculate(new Node(game.getBoard(), Integer.MIN_VALUE), 3, true);
        String[] locations = node.getBestMove().split(",");
        game.getBoard().setLastMove(game.getBoard().getTile(locations[1]),game.getBoard().getTile(locations[0]).getPiece());
        move = node.getBestMove();
        game.move(move);
        System.out.println(new Date());
    }
}
