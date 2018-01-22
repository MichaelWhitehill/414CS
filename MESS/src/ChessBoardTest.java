import org.junit.*;

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
        Knight expectedBlackKnight = new Knight(dummyBoard,7, 7, ChessPiece.Color.BLACK);

        // Test Locations
        String whitePawn = "c2";
        String blackKnight = "h8";
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
            assertEquals(expectedBlackKnight, board.getPiece(blackKnight));
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
        // Tests that a pieces in soon-to-be occupied positons on board are changed after init is called
        {
            String occupiedLocation = "a1";
            ChessBoard board = new ChessBoard();
            King occupiedKing = new King(board, 3, 1, ChessPiece.Color.BLACK);
            board.placePiece(occupiedKing, occupiedLocation);
            board.initialize();
            try {
                assertNotEquals(occupiedKing, board.getPiece(occupiedLocation));
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

        // Make sure we don't crash when removing from outside the board
        {
            ChessBoard board = new ChessBoard();
            board.initialize();
            try{
                board.removePiece("f32");
            } catch (IndexOutOfBoundsException e){
                fail();
            }
        }
        
        // Make sure we're successful removing nothing
        
        {
            ChessBoard board = new ChessBoard();
            board.removePiece("e3");
        }
    }
    
    @Test
    public void toJavaCoordinateTest(){
        // brute force test
        for (int a = 0; a < 26; a++) {
            for (int i = 0; i < 10; i++) {
                int [] expected = {a,i};

                char capLetter = (char) (65 + a); // 65 is 'A'
                char lowLetter = (char) (97 + a); //97 is 'a'
                String capLoc = ""+capLetter+","+i;
                String lowLoc = ""+lowLetter+","+i;

                assertEquals(expected, ChessBoard.toJavaCoordinate(capLoc));
                assertEquals(expected, ChessBoard.toJavaCoordinate(lowLoc));
            }
        }

        // negative numbers
        try{
            ChessBoard.toJavaCoordinate("c,-3");
            fail();
        } catch (IndexOutOfBoundsException ignored){

        }
    }
}
