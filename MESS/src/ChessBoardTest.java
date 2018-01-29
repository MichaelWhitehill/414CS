import org.junit.*;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ChessBoardTest {

    /**
     * Test the constructor and initialize of ChessBoard
     */
    @Test
    public void basicInitTest() {
        // Create dummy board and test chess pieces
        ChessBoard dummyBoard = new ChessBoard();
        Pawn expectedWhitePawn = new Pawn(dummyBoard,1, 2, ChessPiece.Color.WHITE);
        Rook expectedBlackRook = new Rook(dummyBoard,7, 7, ChessPiece.Color.BLACK);

        // Test Locations
        String whitePawn = "c2";
        String blackRook = "h8";
        String noPiece = "b6";
        String outOfBounds = "b9";

        //Create and initialize ChessBoard
        ChessBoard board = new ChessBoard();
        board.initialize();

        // Pawn case
        try {
            assertEquals(expectedWhitePawn, board.getPiece(whitePawn));
        } catch (IllegalPositionException e) {
            fail();
            e.printStackTrace();
        }

        // Corner of board knight case
        try {
            expectedBlackRook.equals(board.getPiece(blackRook));
            assertEquals(expectedBlackRook, board.getPiece(blackRook));
        } catch (IllegalPositionException e) {
            fail();
            e.printStackTrace();
        }

        // No piece
        try {
            assertEquals(null, board.getPiece(noPiece));
        } catch (IllegalPositionException e) {
            fail();
            e.printStackTrace();
        }

        // Failure case
        try {
            board.getPiece(outOfBounds);
            fail();
        } catch (IllegalPositionException e){
            System.out.println("Thew expected exception :)");
        }
    }

    /**
     * Tests strange behaviour of chessBoard
     */
    @Test
    public void initBehaviourTest(){
        // Testing that pieces remain on blank spaces of board after init is called
        {
            String carryOverPawnPosition = "b4";
            ChessBoard board = new ChessBoard();
            Pawn carryOverPawn = new Pawn(board, 3, 1, ChessPiece.Color.BLACK);
            assertTrue(board.placePiece(carryOverPawn, carryOverPawnPosition));
            board.initialize();
            try {
                assertEquals(carryOverPawn, board.getPiece(carryOverPawnPosition));
            } catch (IllegalPositionException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void placePieceTest(){

        // Basic case, place piece in empty space
        {
            // Create dummy board
            ChessBoard dummyBoard = new ChessBoard();
            String position = "c4";
            ChessBoard board = new ChessBoard();
            board.initialize();
            Bishop bishopToPlace = new Bishop(board, ChessPiece.Color.BLACK);
            assertTrue(board.placePiece(bishopToPlace, position));
            Bishop bishopToCompare = new Bishop(dummyBoard, 3, 2, ChessPiece.Color.BLACK);
            try {
                assertEquals(bishopToCompare, board.getPiece(position));
            } catch (IllegalPositionException e) {
                fail();
                e.printStackTrace();
            }
        }

        // Place piece out of bounds
        {
            String position = "i3";
            ChessBoard board = new ChessBoard();
            board.initialize();
            Rook testRook = new Rook(board, ChessPiece.Color.BLACK);
            assertFalse(board.placePiece(testRook, position));
        }

        // Place piece on top of another
        {
            String position = "f3";
            ChessBoard board = new ChessBoard();
            board.initialize();

            // rook will exist on the board first, then the pawn will be placed on top of it
            Rook rook = new Rook(board, 2, 5, ChessPiece.Color.WHITE);
            assertTrue(board.placePiece(rook, position));
            String pawnTempPosition = "e3";
            Pawn pawn = new Pawn(board, 2, 4, ChessPiece.Color.BLACK);
            assertTrue(board.placePiece(pawn, pawnTempPosition));
            assertFalse(board.placePiece(pawn, position));
        }
    }

    @Test
    public void removePieceTest(){
        // Attempt to place on top of where old one was
        {
            String position = "f3";
            ChessBoard board = new ChessBoard();
            board.initialize();

            // rook will exist on the board first, then the pawn will be placed on top of it
            Rook rook = new Rook(board, 2, 5, ChessPiece.Color.WHITE);
            assertTrue(board.placePiece(rook, position));
            String pawnTempPosition = "e3";
            Pawn pawn = new Pawn(board, 2, 4, ChessPiece.Color.BLACK);
            assertTrue(board.placePiece(pawn, pawnTempPosition));
            board.removePiece(position);
            assertTrue(board.placePiece(pawn, position));
        }
        // Make sure we're successful removing nothing
        
        {
            ChessBoard board = new ChessBoard();
            board.removePiece("e3");
        }
    }

    @Test
    public void testMovePiece(){
        /*
        Only tests moving with empty and out of bounds pieces
        Move logic is implemented in specific pieces and tested by their test classes
         */
        ChessBoard board = new ChessBoard();
        // empty start to empty to
        try {
            board.move("a1", "b3");
        } catch (IllegalMoveException ignored) {
            fail();
        }
        // out of bounds start to empty start
        try{
            board.move("e9", "a1");
            fail();
        } catch (IllegalMoveException ignored) {

        }
         // empty space to occupied space
        board.initialize();
        String emptySpace = "c4";
        String occupiedSpace = "e1";
        try {
            board.move(emptySpace, occupiedSpace);
            fail();
        } catch (IllegalMoveException ignored) {

        }
    }
    
    @Test
    public void toJavaCoordinateTest(){
        String input1 = "a1"; int[] expected1 = {0,0};
        String input2 = "b5"; int[] expected2 = {4,1};
        String input3 = "h8"; int[] expected3 = {7,7};
        String input4 = "e2"; int[] expected4 = {1,4};

        assertTrue(Arrays.equals(ChessBoard.toJavaCoordinate(input1), expected1));
        assertTrue(Arrays.equals(ChessBoard.toJavaCoordinate(input2), expected2));
        assertTrue(Arrays.equals(ChessBoard.toJavaCoordinate(input3), expected3));
        assertTrue(Arrays.equals(ChessBoard.toJavaCoordinate(input4), expected4));


        // negative numbers
        try{
            ChessBoard.toJavaCoordinate("c-3");
            fail();
        } catch (IndexOutOfBoundsException ignored){
        }
    }

    @Test
    public void toChessCoordinateTest(){
        String expected1 = "a1"; int r1 = 0; int c1 = 0;
        String expected2 = "a8"; int r2 = 7; int c2 = 0;
        String expected3 = "d4"; int r3 = 3; int c3 = 3;

        String fullTest1 = "h8";
        String fullTest2 = "f5";

        assertEquals(expected1, ChessBoard.toChessCoordinate(r1, c1));
        assertEquals(expected2, ChessBoard.toChessCoordinate(r2, c2));
        assertEquals(expected3, ChessBoard.toChessCoordinate(r3, c3));

        assertEquals(fullTest1, ChessBoard.toChessCoordinate(ChessBoard.toJavaCoordinate(fullTest1)[0],ChessBoard.toJavaCoordinate(fullTest1)[1]));
        assertEquals(fullTest2, ChessBoard.toChessCoordinate(ChessBoard.toJavaCoordinate(fullTest2)[0],ChessBoard.toJavaCoordinate(fullTest2)[1]));



    }
}
