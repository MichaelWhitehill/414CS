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
     * @throws IllegalPositionException
     */
    abstract public void setPosition(String position) throws IllegalPositionException;

    /**
     * Sets the piece position
     * @param row 0-7
     * @param column 0-7
     * @throws IllegalPositionException
     */
    public void setPosition(int row, int column) throws IllegalPositionException{

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

    protected static boolean isInbounds (int r, int c){
        return (r>=0 && r<8 && c>=0 && c<8);
    }
}
