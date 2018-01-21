public class ChessBoard{
  public static final int BOARD_SIZE = 8;
  private ChessPiece[][] board;


  /**
  * No-argument constructor.
  * Initializes a the ChssPiece array (board) to the BOARD_SIZE variable
  */
  public ChessBoard(){
    board = new ChessPiece[BOARD_SIZE][BOARD_SIZE];
  }

  /**
  * Sets pieces on the chess board to default chess starting position
  */
  public void initialize(){
    return;
  }

  /**
  * Gets the chess piece at the position specified
  * @param position: expected to be in the for of letterNumber ie: "a1" "b2" "e5"
  */
  public ChessPiece getPiece(String position) throws IllegalPositionException{
    return null;
  }

  /**
  * Attempts to place a piece at given position. Returns fals if unsucssful.
  * @param piece: Chess piece to be placed
  * @param position: Position piece should be placed at . Expected to be in the for of letterNumber ie: "a1" "b2" "e5"
  * @return true for successfull placement. False for unsucssful
  */
  public boolean placePiece(ChessPiece piece, String position){
    return false;
  }

  /**
  * @return formatted chess board in an all fancy font. ♙
  */
  public String toString(){
    return "Unimplimented chessBoard.toString";
  }

  /**
  * Converts a chess style coordinate ie: "a1" "b2" "e5" to array coordinate ie: [0,0] [1,1] [4,4]
  * @param  position
  * @return  int[]: int[] containing coordinates in java style
  */
  public int[] toJavaCoordinate(String position){
    return new int[2];
  }
  public static void main(String[] args){
      System.out.println("Hello, world");
  }

}