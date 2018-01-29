import java.util.ArrayList;

public class Bishop extends ChessPiece {

    public Bishop(ChessBoard board, ChessPiece.Color color) {
        super(board, color);
    }

    public Bishop(ChessBoard board, int row, int column, ChessPiece.Color color) {
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