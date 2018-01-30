import java.util.ArrayList;

public abstract class ChessPiece {
    public enum Color{WHITE, BLACK};

    protected ChessBoard board;
    protected int row;
    protected int column;
    protected Color color;
    protected String whiteCharacter = "unimplemented toString";
    protected String blackCharacter = "unimplemented toString";


    /**
     *
     * @param board
     * @param color
     */
    public ChessPiece(ChessBoard board, Color color){
        this.board = board;
        this.color=color;
    }

    /**
     * Gets the color of the piece
     * @return ChessPiece.color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Gets the piece position in String format ie "a5", "b3"
     * @return String of form character, number
     */
    public String getPosition(){
        return null;
    }

    /**
     * Sets the pieces position
     * @param position expected in chess format ie: "a5", "b6"
     * @throws IllegalPositionException Throws exception if new position is not legal
     */
    public void setPosition(String position) throws IllegalPositionException {
        if (!(legalMoves().contains(position)))
            throw new IllegalPositionException();
        // save our state because the board has to null out out properties in remove
        ChessBoard board = this.board;

        // null out old destination
        board.removePiece(ChessBoard.toChessCoordinate(row,column));
        this.board = board;
        // remove piece where we're going
        board.removePiece(position);
        // make a new piece where we want
        board.placePiece(this, position);
    }

    /**
     *
     * @return Fancy unicode to string
     */
    public String toString(){
        if (color == Color.BLACK)
            return blackCharacter;
        else
            return whiteCharacter;
    }


    /**
     * Gives back a list of all potential legal moves
     * @return List of all potential legal moves
     */
    abstract public ArrayList<String> legalMoves();

    static boolean isInbounds(int r, int c){
        return (r>=0 && r<8 && c>=0 && c<8);
    }

    boolean addMove(int r, int c, ArrayList<String> moves){
        try{
            if (board.getPiece(ChessBoard.toChessCoordinate(r,c)) == null){
                moves.add(ChessBoard.toChessCoordinate(r,c));
            } else if (board.getPiece(ChessBoard.toChessCoordinate(r,c)).color != this.color){
                moves.add(ChessBoard.toChessCoordinate(r,c));
                return true;
            } else if (board.getPiece(ChessBoard.toChessCoordinate(r,c)).color == this.color){
                return true;
            }
            else {
                System.err.println("logic error in bishop legal moves");
                return true;
            }
        } catch (IllegalPositionException e){
            e.printStackTrace();
        }
        return false;
    }
}
