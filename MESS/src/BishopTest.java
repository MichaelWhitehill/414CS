import org.junit.*;
import static org.junit.Assert.*;

public class BishopTest {
    @Test
    public void basicMoveTests(){
        // test moving on an empty board
        {
            String startPos = "c3";
            ChessBoard board = new ChessBoard();
            Bishop b = new Bishop(board, ChessPiece.Color.WHITE);
            board.placePiece(b, startPos);
            String testPos1 = "d4";
            String testPos2 = "h8";
            String testPos3= "b2";
            String testPos4 = "a3";
            try {
                System.out.println("====----Rook move test----====");
                b.setPosition(testPos1);
                System.out.println(board);
                System.out.println("Passed test pos 1");
                b.setPosition(testPos2);
                System.out.println(board);
                System.out.println("Passed test pos 2");
                b.setPosition(testPos3);
                System.out.println(board);
                System.out.println("Passed test pos 3");
                b.setPosition(testPos4);
                System.out.println("Passed test pos 4");
            } catch (IllegalPositionException ignored) {
                fail();
            }
        }
        // Zig zag test
        {
            String startPos="a4";
            ChessBoard board =  new ChessBoard();
            Bishop b = new Bishop(board, ChessPiece.Color.BLACK);
            board.placePiece(b, startPos);

            String testPos1="b5";
            String testPos2="a6";
            String testPos3="b7";
            String testPos4 = "c8";

            try {
                System.out.println("====----Rook zig-zag test---====");
                b.setPosition(testPos1);
                assertEquals(board.getPiece(startPos), null);
                assertEquals(board.getPiece(testPos1), b);
                System.out.println("Passed test pos 1");
                b.setPosition(testPos2);
                assertEquals(board.getPiece(testPos1), null);
                assertEquals(board.getPiece(testPos2), b);
                System.out.println("Passed test pos 2");
                b.setPosition(testPos3);
                assertEquals(board.getPiece(testPos2), null);
                assertEquals(board.getPiece(testPos3), b);
                System.out.println("Passed test pos 3");
                b.setPosition(testPos4);
                assertEquals(board.getPiece(testPos3), null);
                assertEquals(board.getPiece(testPos4), b);
                System.out.println("Passed test pos 4");
            } catch (IllegalPositionException ignored) {
                fail();
            }
        }
        // Adjacent moves test
        {
            String startPos = "e4";
            ChessBoard board = new ChessBoard();
            board.initialize();
            Bishop b = new Bishop(board, ChessPiece.Color.BLACK);
            board.placePiece(b, startPos);
            String adjacentPosition1 = "g6";
            String adjacentPosition2 = "d3";
            try {
                System.out.println("====----Rook adjacent move test----====");
                b.setPosition(adjacentPosition1);
                b.setPosition(adjacentPosition2);
            } catch (IllegalPositionException ignored) {
                fail();
            }
        }
    }

    @Test
    public void moveCaptureTests() {
        //Test back-to-back capture
        ChessBoard board = new ChessBoard();
        board.initialize();
        String stupidPawnInTheWay = "d2";
        String rookStartingPos = "c1";
        board.removePiece(stupidPawnInTheWay);
        try {
            ChessPiece bishopAsPiece = board.getPiece(rookStartingPos);
            String IntermediatesPos = "f4";
            String capturePos1 = "c7";
            String capturePos2 = "d8";
            bishopAsPiece.setPosition(IntermediatesPos);
            bishopAsPiece.setPosition(capturePos1);
            bishopAsPiece.setPosition(capturePos2);
            assertEquals(null, board.getPiece(capturePos1));
            assertEquals(bishopAsPiece, board.getPiece(capturePos2));
        } catch (IllegalPositionException ignored) {
            fail();
        }
    }

    @Test
    public void badMoveTest(){
        // test moving to self
        {
            ChessBoard board = new ChessBoard();
            Bishop b = new Bishop(board, ChessPiece.Color.BLACK);
            String startPos = "b3";
            board.placePiece(b, startPos);
            try {
                b.setPosition(startPos);
                fail();
            } catch (IllegalPositionException ignored) {
            }
        }

        // Move to same color
        {
            ChessBoard board = new ChessBoard();
            board.initialize();
            String bishopStartingPos = "c1";
            String stupidPawnInTheWay = "d2";
            ChessPiece bishopAsPiece = null;
            try {
                bishopAsPiece = board.getPiece(bishopStartingPos);
            } catch (IllegalPositionException e) {
                e.printStackTrace();
            }
            try{
                assert bishopAsPiece != null;
                bishopAsPiece.setPosition(stupidPawnInTheWay);
                fail();
            } catch (IllegalPositionException ignored){

            }
        }
        // Move vertically and horizontally
        {
            String startPos = "c3";
            ChessBoard board = new ChessBoard();
            Bishop b = new Bishop(board, ChessPiece.Color.WHITE);
            board.placePiece(b, startPos);
            String testPos1 = "b3";
            String testPos2 = "c4";
            try {
                b.setPosition(testPos1);
                fail();
            } catch (IllegalPositionException ignored) {

            }
            try {
                b.setPosition(testPos2);
                fail();
            } catch (IllegalPositionException ignored) {

            }
        }
    }

    @Test
    public void legalMovesTest(){
        ChessBoard board = new ChessBoard();
        board.initialize();
        String bishopStartingPos = "c1";
        String stupidPawnInTheWay = "d2";
        ChessPiece bishopAsPiece = null;
        try {
            bishopAsPiece = board.getPiece(bishopStartingPos);
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }
        assert bishopAsPiece != null;
        assertEquals(0, bishopAsPiece.legalMoves().size());
        board.removePiece(stupidPawnInTheWay);
        assertEquals(5, bishopAsPiece.legalMoves().size());
        String testPos = "e3";
        assertTrue(bishopAsPiece.legalMoves().contains(testPos));
    }
}
