import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class RookTest{
    @Test
    public void basicMoveTests(){
        ChessBoard board = new ChessBoard();
        Rook r = new Rook (board, ChessPiece.Color.BLACK);
        try {
            // test vertical
            String startPos = "h8";
            String vertical1 = "h6";
            String vertical2 = "h1";
            String vertical3 = "h4";
            // Perform moves
            r.setPosition(startPos);
            r.setPosition(vertical1);
            r.setPosition(vertical2);
            r.setPosition(vertical3);

            // test Horizontal
            String horizontal1 = "e4";
            String horizontal2 = "a4";
            String horizontal3 = "g4";
            // Perform Moves
            r.setPosition(horizontal1);
            r.setPosition(horizontal2);
            r.setPosition(horizontal3);
        } catch (IllegalPositionException ignored) {
            fail();
        }
    }

    @Test
    public void moveCaptureTests(){
        ChessBoard board = new ChessBoard();
        board.initialize();
        String rookStartingPosition = "a8";
        String stupidPawnInTheWay = "a7";
        String positionToCapture1 = "a2";
        String positionToCapture2 = "a1";
        String positionToCapture3 = "b1";
        ChessPiece rookAsPiece = null;
        try {
            rookAsPiece = board.getPiece(rookStartingPosition);
            board.removePiece(stupidPawnInTheWay);
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }
        assert rookAsPiece != null;
        try {
            rookAsPiece.setPosition(positionToCapture1);
            rookAsPiece.setPosition(positionToCapture2);
            rookAsPiece.setPosition(positionToCapture3);
        } catch (IllegalPositionException ignored) {
            fail();
        }
    }

    @Test
    public void badMoveTest() {
        ChessBoard board = new ChessBoard();
        board.initialize();
        String rookStartingPosition = "a8";
        String stupidPawnInTheWay = "a7";
        String diagonalFail = "b7";
        ChessPiece rookAsPiece = null;
        try {
            rookAsPiece = board.getPiece(rookStartingPosition);
            board.removePiece(stupidPawnInTheWay);
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }
        assert rookAsPiece != null;
        try {
            rookAsPiece.setPosition(diagonalFail);
            rookAsPiece.setPosition(stupidPawnInTheWay);
            try {
                board.removePiece(stupidPawnInTheWay);
                rookAsPiece.setPosition(stupidPawnInTheWay);
            } catch (IllegalPositionException e){
                e.printStackTrace();
            }
            // Should fail for different reason now
            rookAsPiece.setPosition(diagonalFail);
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void legalMovesTest(){
        ChessBoard board = new ChessBoard();
        board.initialize();
        String rookStartingPosition = "a8";
        String stupidPawnInTheWay = "a7";
        try {
            ChessPiece rookAsPiece = board.getPiece(rookStartingPosition);
            assertEquals(0, rookAsPiece.legalMoves().size());
            board.removePiece(stupidPawnInTheWay);
            assertEquals(6,rookAsPiece.legalMoves().size());
            assertTrue(rookAsPiece.legalMoves().contains("a2"));
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }
    }
}
