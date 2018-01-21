// import IllegalPositionException;

public class ChessBoard{
  public static final int BOARD_SIZE = 8;
  private ChessPiece[][] board;


  /**
  * No-argument constructor.
  * Initializes a the ChssPiece array (board) to the BOARD_SIZE variable
  **/
  public ChessBoard(){
    board = new ChessPiece[BOARD_SIZE][BOARD_SIZE];
  }

  /**
  * Sets pieces on the chess board to default chess starting position
  **/
  public void initialize(){
    return;
  }

  /**
  * Gets the chess piece at the position specified
  * String position: expected to be in the for of letterNumber ie: "a1" "b2" "e5"
  **/
  public ChessPiece getPiece(String position) throws IllegalPositionException{
    return null;
  }

  /**
  * Attempts to place a piece at given position. Returns fals if unsucssful.
  * ChessPiece piece: Chess piece to be placed
  * String position: Position piece should be placed at . Expected to be in the for of letterNumber ie: "a1" "b2" "e5"
  * Returns boolean: true for successfull placement. False for unsucssful
  **/
  public boolean placePiece(ChessPiece piece, String position){
    return false;
  }

  /**
  * Prints formatted chess board in an all fancy font. ♙
  **/
  public String toString(){
    return "Unimplimented chessBoard.toString";
  }
}
