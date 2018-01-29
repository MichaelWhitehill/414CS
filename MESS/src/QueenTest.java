import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class QueenTest{
    @Test
    public void isEmpty(){
        ChessBoard board = new ChessBoard();
        Queen test = new Queen(board, ChessPiece.Color.WHITE);
        String testPos = "e6";
        board.placePiece(test, testPos);
        assertEquals(test.legalMoves().size(), 0);
    }
}
