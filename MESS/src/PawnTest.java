import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class PawnTest{

    @Test
    public void basicMoveTests(){
        ChessBoard board = new ChessBoard();
        // Simply try moving in the middle of the board
        try {
            String p1Start = "g5";
            Pawn p1 = new Pawn(board, ChessPiece.Color.BLACK);
            board.placePiece(p1, p1Start);
            p1.setPosition("g4");
        } catch (IllegalPositionException ignored) {
            fail();
        }
        // WARNING: The pawn is still on the board and can interfere in further tests

        // Try moving as other color
        try {
            String p2Start = "f5";
            Pawn p2 = new Pawn(board, ChessPiece.Color.WHITE);
            board.placePiece(p2, p2Start);
            p2.setPosition("f6");
        } catch (IllegalPositionException ignored) {
            fail();
        }
        // WARNING: The pawn is still on the board and can interfere in further tests

        board.initialize();
        // Try moving two squares from start
        try {
            String pawnAtStart = "c2";
            String moveUp2 = "c4";
            ChessPiece pawnAsPiece = board.getPiece(pawnAtStart);
            pawnAsPiece.setPosition(moveUp2);

            String pawnAtStart2 = "a7";
            String moveDown2 = "a5";
            // We're done with the first pawn, so we'll re-use the variable
            pawnAsPiece = board.getPiece(pawnAtStart2);
            pawnAsPiece.setPosition(moveDown2);
        } catch (IllegalPositionException ignored) {
            fail();
        }

        // Try moving one square from start
        try {
            String pawnAtStart = "c2";
            String moveUp1 = "c3";
            ChessPiece pawnAsPiece = board.getPiece(pawnAtStart);
            pawnAsPiece.setPosition(moveUp1);

            String pawnAtStart2 = "a7";
            String moveDown1 = "a6";
            // We're done with the first pawn, so we'll re-use the variable
            pawnAsPiece = board.getPiece(pawnAtStart2);
            pawnAsPiece.setPosition(moveDown1);
        } catch (IllegalPositionException ignored) {
            fail();
        }

    }

    @Test
    public void moveCaptureTests(){
        // White capture black
        {
            // White pawn setup
            ChessBoard board = new ChessBoard();
            String whiteStart = "a2";
            Pawn white = new Pawn(board, ChessPiece.Color.WHITE);
            board.placePiece(white, whiteStart);

            // Black pawn setup
            String blackStart = "b3";
            Pawn black = new Pawn(board, ChessPiece.Color.BLACK);
            board.placePiece(black, blackStart);

            // Perform and check capture
            try {
                white.setPosition(blackStart);
                assertEquals(null, board.getPiece(whiteStart));
                assertEquals(white, board.getPiece(blackStart));
            } catch (IllegalPositionException ignored) {
                fail();
            }
        }

        // Black capture black
        {
            // White pawn setup
            ChessBoard board = new ChessBoard();
            String whiteStart = "d5";
            Pawn white = new Pawn(board, ChessPiece.Color.WHITE);
            board.placePiece(white, whiteStart);

            // Black pawn setup
            String blackStart = "c6";
            Pawn black = new Pawn(board, ChessPiece.Color.BLACK);
            board.placePiece(black, blackStart);

            // Perform and check capture
            try {
                black.setPosition(whiteStart);
                assertEquals(black, board.getPiece(whiteStart));
                assertEquals(null, board.getPiece(blackStart));
            } catch (IllegalPositionException ignored) {
                fail();
            }
        }
    }

    @Test
    public void badMoveTest(){
        ChessBoard board = new ChessBoard();
        // Try moving two from invalid location
        {
            String start1 = "g3";
            Pawn whitePawn = new Pawn(board, ChessPiece.Color.WHITE);
            board.placePiece(whitePawn, start1);
            String up2 = "g1";
            try {
                whitePawn.setPosition(up2);
                fail();
            } catch (IllegalPositionException ignored) {
            }
        }

        // Try moving backwards
        {
            String start = "d3";
            Pawn blackPawn = new Pawn(board, ChessPiece.Color.BLACK);
            board.placePiece(blackPawn, start);
            String backwards = "d2";
            try {
                blackPawn.setPosition(backwards);
                fail();
            } catch (IllegalPositionException ignored) {
            }
        }

        // Try capturing friend
        {
            String start = "d3";
            String friendStart = "e4";
            Pawn blackPawn = new Pawn(board, ChessPiece.Color.BLACK);
            Pawn friend = new Pawn(board, ChessPiece.Color.BLACK);
            board.placePiece(blackPawn, start);
            board.placePiece(friend, friendStart);

            try {
                blackPawn.setPosition(friendStart);
                fail();
            } catch (IllegalPositionException ignored) {

            }
        }
    }

    @Test
    public void legalMovesTest(){
        ChessBoard board = new ChessBoard();
        board.initialize();
        ChessPiece pawnAsPiece = null;
        try {
            pawnAsPiece = board.getPiece("c2");
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }
        assert  pawnAsPiece!=null;
        String testPawnStart = "b3";
        Pawn test = new Pawn(board, ChessPiece.Color.BLACK);
        board.placePiece(test, testPawnStart);

        assertEquals(3, pawnAsPiece.legalMoves().size());
    }
}
