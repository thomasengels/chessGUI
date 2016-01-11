import game.AI.MiniMax;
import game.AI.Node;
import game.Game;
import game.pieces.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.List;

/**
 * Created by Glenn on 11-1-2016.
 */
public class Tests {
    private Game game;
    private MiniMax algorithm;

    @Before
    public void init() {
        game = new Game();
        algorithm = new MiniMax();
    }

    @Test
    public void testPawnCount() {
        List<Piece> pieces = game.getBoard().getPieces();
        int pawnCounter = 0;
        for(Piece p : pieces) {
            if(p instanceof Pawn) {
                pawnCounter++;
            }
        }
        assertEquals(16, pawnCounter);
    }

    @Test
    public void testRookCount() {
        List<Piece> pieces = game.getBoard().getPieces();
        int rookCounter = 0;
        for(Piece p : pieces) {
            if(p instanceof Rook) {
                rookCounter++;
            }
        }
        assertEquals(4, rookCounter);
    }

    @Test
    public void testKnightCount() {
        List<Piece> pieces = game.getBoard().getPieces();
        int counter = 0;
        for(Piece p : pieces) {
            if(p instanceof Knight) {
                counter++;
            }
        }
        assertEquals(4, counter);
    }
    @Test
    public void testBishopCount() {
        List<Piece> pieces = game.getBoard().getPieces();
        int counter = 0;
        for(Piece p : pieces) {
            if(p instanceof Bishop) {
                counter++;
            }
        }
        assertEquals(4, counter);
    }

    @Test
    public void testQueenCount() {
        List<Piece> pieces = game.getBoard().getPieces();
        int counter = 0;
        for(Piece p : pieces) {
            if(p instanceof Queen) {
                counter++;
            }
        }
        assertEquals(2, counter);
    }

    @Test
    public void testKingCount() {
        List<Piece> pieces = game.getBoard().getPieces();
        int counter = 0;
        for(Piece p : pieces) {
            if(p instanceof King) {
                counter++;
            }
        }
        assertEquals(2, counter);
    }

    @Test
    public void testPiecesPerPlayer() {
        List<Piece> pieces = game.getBoard().getPieces();
        int whiteCounter = 0;
        int blackCounter = 0;
        for(Piece p : pieces) {
            if(p.getColor().equals("White")) {
                whiteCounter++;
            } else {
                blackCounter++;
            }
        }
        assertEquals(16, whiteCounter);
        assertEquals(16, blackCounter);
    }

    @Test
    public void testAlogrithm() {
        int depth = 3;
        Node node = algorithm.calculate(new Node(game.getBoard(), 0), 3, true);
        assertEquals(depth, node.getMoves().size());
    }
}
