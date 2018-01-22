import java.util.ArrayList;

public class Rook extends ChessPiece{


    public Rook(ChessBoard board, Color color) {
        super(board, color);
    }

    public Rook(ChessBoard board, int row, int column, Color color) {
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
