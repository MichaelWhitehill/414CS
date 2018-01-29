import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KnightTest {
    @Test
    public void isEmpty(){
        ChessBoard board = new ChessBoard();
        Knight test = new Knight(board, ChessPiece.Color.WHITE);
        String testPos = "e6";
        board.placePiece(test, testPos);
        assertEquals(test.legalMoves().size(), 0);
    }
}
