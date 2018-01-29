import org.junit.*;
import static org.junit.Assert.*;

public class KingTest{
    @Test
    public void basicMoveTests(){
        {
            String start = "b5";
            ChessBoard board = new ChessBoard();
            King king = new King(board, ChessPiece.Color.BLACK);
            board.placePiece(king, start);
            String position1 = "c5";
            String position2 = "c6";
            String position3 = "b5";
            try {
                king.setPosition(position1);
                king.setPosition(position2);
                king.setPosition(position3);
            } catch (IllegalPositionException ignored) {
                fail("King failed basic move test");
            }
        }
    }

    @Test
    public void moveCaptureTests(){
        {
            ChessBoard board = new ChessBoard();
            String kingStart = "d4";
            King king = new King(board, ChessPiece.Color.WHITE);
            String pieceToCaptureStart = "c4";
            Pawn pieceToCapture = new Pawn(board, ChessPiece.Color.BLACK);

            board.placePiece(king, kingStart);
            board.placePiece(pieceToCapture, pieceToCaptureStart);

            try {
                king.setPosition(pieceToCaptureStart);
            } catch (IllegalPositionException ignored) {
                fail();
            }
            try {
                assertEquals(king,board.getPiece(pieceToCaptureStart));
                assertEquals(null, board.getPiece(kingStart));
            } catch (IllegalPositionException e) {
                fail();
            }
        }
    }

    @Test
    public void badMoveTest(){
        ChessBoard board = new ChessBoard();
        String kingStart = "b4";
        King king = new King(board, ChessPiece.Color.WHITE);
        board.placePiece(king, kingStart);

        // try to move two spaces
        String upTwo = "b6";
        try {
            king.setPosition(upTwo);
            fail();
        } catch (IllegalPositionException ignored){}

        // try to move sideways two
        String overTwo = "d6";
        try {
            king.setPosition(overTwo);
            fail();
        } catch (IllegalPositionException ignored) {}

        board.initialize();
        String kingAtStart = "e8";
        ChessPiece kingAsPiece = null;
        try {
            kingAsPiece = board.getPiece(kingAtStart);
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }
        assert kingAsPiece!=null;

        String pawnInTheWay = "e7";

        try {
            kingAsPiece.setPosition(pawnInTheWay);
            fail();
        } catch (IllegalPositionException ignored) {}
    }

    @Test
    public void legalMovesTest(){
        ChessBoard board = new ChessBoard();
        String middleStart = "e5";
        King kingInMiddle = new King(board, ChessPiece.Color.BLACK);
        board.placePiece(kingInMiddle, middleStart);

        assertEquals(kingInMiddle.legalMoves().size(), 8);
        String expectedMove = "e6";
        assertTrue(kingInMiddle.legalMoves().contains(expectedMove));

        board.initialize();
        String kingAtStart = "e8";
        ChessPiece kingAsPiece = null;
        try {
            kingAsPiece = board.getPiece(kingAtStart);
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }
        assert kingAsPiece != null;

        assertEquals(kingAsPiece.legalMoves().size(), 0);
    }

}
