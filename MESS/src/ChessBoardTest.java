import org.junit.*;
import static org.junit.Assert.*;

public class ChessBoardTest{

  /**
  * Test the constructor and initialize of ChessBoard
  **/
  @Test
  public void basicInitTest(){
    // Test Positions
    String whitePawn="a2"; String blackPawn="e7"; String blackKnight="h8"; String noPiece="b5";

    //Expected values
    String expectedWhitePawn="u2659"; String expectedBlackPawn="u265F"; String expectedBlackKnight="u265E";
    //Create and initialize ChessBoard
    ChessBoard board = new ChessBoard();
    board.initialize();
    try {
      assertEquals(expectedWhitePawn, board.getPiece(whitePawn));
    } catch (IllegalPositionException e) {
      e.printStackTrace();
    }
  }

  public static void main(String args[]) {
      org.junit.runner.JUnitCore.main("ChessBoardTest");
    }

}
