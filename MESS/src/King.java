import java.util.ArrayList;

public class King extends ChessPiece{

    public King(ChessBoard board, Color color) {
        super(board, color);
    }

    public King(ChessBoard board, int row, int column, Color color) {
        super(board, row, column, color);
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public ArrayList<String> legalMoves() {
        return null;
    }
}
