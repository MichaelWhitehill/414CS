public class ChessBoard {
    public static final int BOARD_SIZE = 8;
    private ChessPiece[][] board;


    /**
     * No-argument constructor.
     * Initializes a the ChessPiece array (board) to the BOARD_SIZE variable
     */
    public ChessBoard() {
        board = new ChessPiece[BOARD_SIZE][BOARD_SIZE];
    }

    /**
     * Sets pieces on the chess board to default chess starting position
     */
    public void initialize() {
        return;
    }

    /**
     * Gets the chess piece at the position specified
     *
     * @param position: expected to be in the for of letterNumber ie: "a1" "b2" "e5"
     */
    public ChessPiece getPiece(String position) throws IllegalPositionException {
        return null;
    }

    /**
     * Attempts to place a piece at given position. Returns false if unsuccessful.
     *
     * @param piece:    Chess piece to be placed
     * @param position: Position piece should be placed at . Expected to be in the form of letterNumber ie: "a1" "b2" "e5"
     * @return true for successful placement. False for unsuccessful
     */
    public boolean placePiece(ChessPiece piece, String position) {
        return false;
    }

    /**
     * Removes the piece at a given position.
     *
     * @param position: Expected to be in the form of letterNumber ie: "a1" "b2" "e5"
     */
    public void removePiece(String position) {
        return;
    }

    /**
     * Moves piece from the starting position to the to position
     * @param fromPosition Position of piece
     * @param toPosition Position for piece to be moved to
     * @throws IllegalMoveException
     */
    public void move(String fromPosition, String toPosition) throws IllegalMoveException {

    }

    /**
     * @return formatted chess board in an all fancy font. ♙
     */
    public String toString() {
        return "Unimplimented chessBoard.toString";
    }


    /**
     * Converts a chess style coordinate ie: "a1" "b2" "e5" to array coordinate ie: [0,0] [1,1] [4,4]
     *
     * @param position expected in for "a3", "b5"
     * @return int[]: int[] containing coordinates in java style
     */
    protected static int[] toJavaCoordinate(String position) {
        return new int[2];
    }

    protected static String toChessCoordinate(int row, int colounm){
        return "";
    }


    public static void main(String[] args) {
        System.out.println("Hello, world");
    }

}
