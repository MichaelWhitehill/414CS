public class ChessBoard {
    private static final int BOARD_SIZE = 8;
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
        placeSet(ChessPiece.Color.WHITE);
        placeSet(ChessPiece.Color.BLACK);

    }



    /**
     * Gets the chess piece at the position specified
     *
     * @param position: expected to be in the for of letterNumber ie: "a1" "b2" "e5"
     */
    public ChessPiece getPiece(String position) throws IllegalPositionException {
        int[] positionAsArray = toJavaCoordinate(position);

        // check bounds
        if (checkBadCoordinates(positionAsArray))
            throw new IllegalPositionException();

        return this.board[positionAsArray[0]][positionAsArray[1]];
    }

    /**
     * Attempts to place a piece at given position. Returns false if unsuccessful.
     *
     * @param piece:    Chess piece to be placed
     * @param position: Position piece should be placed at . Expected to be in the form of letterNumber ie: "a1" "b2" "e5"
     * @return true for successful placement. False for unsuccessful
     */
    public boolean placePiece(ChessPiece piece, String position) {
        // convert position
        int[] positionAsArray = toJavaCoordinate(position);

        // check position
        if (checkBadCoordinates(positionAsArray))
            return false;
        if (this.board[positionAsArray[0]][positionAsArray[1]] != null){
            return false;
        }

        // place the piece
        else {
            this.board[positionAsArray[0]][positionAsArray[1]] = piece;
            if (piece != null){
                piece.row = positionAsArray[0];
                piece.column = positionAsArray[1];
            }
            return true;
        }
    }

    /**
     * Removes the piece at a given position.
     *
     * @param position: Expected to be in the form of letterNumber ie: "a1" "b2" "e5"
     */
    protected void removePiece(String position) {
        int[] positionAsArray = toJavaCoordinate(position);

        // check position
        if (checkBadCoordinates(positionAsArray))
            return;
        if (this.board[positionAsArray[0]][positionAsArray[1]] != null){
            try {
                ChessPiece piece = getPiece(position);
                piece.column = -1;
                piece.row = -1;
                piece.board = null;
                this.board[positionAsArray[0]][positionAsArray[1]] = null;
            } catch (IllegalPositionException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Moves piece from the starting position to the to position
     * @param fromPosition Position of piece
     * @param toPosition Position for piece to be moved to
     * @throws IllegalMoveException Throws exception if the move is illegal
     */
    public void move(String fromPosition, String toPosition) throws IllegalMoveException {
        int [] fromPositionAsArray = toJavaCoordinate(fromPosition);
        int[] toPositionAsArray = toJavaCoordinate(toPosition);

        if (checkBadCoordinates(fromPositionAsArray))
            throw new IllegalMoveException();

        if (checkBadCoordinates(toPositionAsArray))
            throw new IllegalMoveException();

        try {
            if (getPiece(fromPosition) == null && getPiece(toPosition) == null)
                return;
            if (getPiece(fromPosition) == null && getPiece(toPosition) != null)
                throw new IllegalMoveException();

            if (getPiece(fromPosition) != null){
                ChessPiece fromPiece = getPiece(toPosition);
                fromPiece.setPosition(toPosition);
            }
        } catch (IllegalPositionException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return formatted chess board in an all fancy font. ♙
    */
    public String toString(){
        StringBuilder s = new StringBuilder();
        for (int i = BOARD_SIZE-1; i>=0; i--){
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == null)
                    s.append(" ");
                else
                    s.append(board[i][j]);
            }
            s.append("\n");
        }
        return  s.toString();
    }


    /**
     * Converts a chess style coordinate ie: "a1" "b2" "e5" to array coordinate ie: [0,0] [1,1] [4,4]
     *
     * @param position expected in for "a3", "b5"
     * @return int[]: int[] containing coordinates in java style
     */
    protected static int[] toJavaCoordinate(String position) {
        if (position.length() > 2){
            throw new IndexOutOfBoundsException();
        }
        int [] ret = new int[2];

        char column = position.charAt(0);

        // ASCII math to turn the char into the column
        if (Character.isUpperCase(column)){
            ret[1] = column-65;
        } else {
            ret[1] = column-97;
        }

        ret[0] = Integer.parseInt(position.substring(1)) - 1;
        return ret;
    }

    protected static String toChessCoordinate(int row, int column){
        char c = 97; // lowercase 'a'
        c += column;
        return "" + c + Integer.toString(row + 1);
    }

    /**
     * Places the set of chess pieces in the starting arrangement.
     * @param color The color of the piece set
     */
    private void placeSet(ChessPiece.Color color){
        int row = 0;
        int pawnRow;

        // set columns
        if (color== ChessPiece.Color.WHITE)
            pawnRow = 1;
        else {
            row = 7;
            pawnRow = 6;
        }

        // place pieces
        placePiece(new Rook(this, color), toChessCoordinate(row, 0));
        placePiece(new Knight(this, color), toChessCoordinate(row,1));
        placePiece(new Bishop(this, color), toChessCoordinate(row,2));
        placePiece(new Queen(this, color), toChessCoordinate(row, 3));
        placePiece(new King(this, color), toChessCoordinate(row,4));

        placePiece(new Rook(this, color), toChessCoordinate(row,7));
        placePiece(new Knight(this, color), toChessCoordinate(row,6));
        placePiece(new Bishop(this, color), toChessCoordinate(row,5));

        // place pawns
        for (int i = 0; i < 8; i++) {
            placePiece(new Pawn(this, color), toChessCoordinate(pawnRow, i));
        }
    }

    private static boolean checkBadCoordinates(int[] coordinates){
        return coordinates[0] < 0 || coordinates[0] > BOARD_SIZE-1 || coordinates[1] < 0 || coordinates[1] > BOARD_SIZE-1;
    }


    public static void main(String[] args) {
        ChessBoard board =  new ChessBoard();
        board.initialize();
        System.out.println(board);
    }
}
