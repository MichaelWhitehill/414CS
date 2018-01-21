
public class ChessPiece {
    public enum Color{WHITE, BLACK};

    protected ChessBoard board;
    protected int row;
    protected int column;
    protected Color color;

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
     * This constructor should only be used for testing
     * @param board
     * @param row
     * @param column
     * @param color
     */
    public ChessPiece(ChessBoard board, int row, int column, Color color) {
        this.board = board;
        this.row = row;
        this.column = column;
        this.color = color;
    }
}
