public class Pawn extends ChessPiece {

    public Pawn(ChessBoard board, Color color) {
        super(board, color);
    }

    public Pawn(ChessBoard board, int row, int column, Color color) {
        super(board, row, column, color);
    }
}
