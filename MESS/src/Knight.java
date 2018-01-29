import java.util.ArrayList;

public class Knight extends  ChessPiece{

    public Knight(ChessBoard board, Color color) {
        super(board, color);
    }

    public Knight(ChessBoard board, int row, int column, Color color) {
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
