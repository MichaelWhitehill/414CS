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

    public boolean equals(Object other){
        if (!(other instanceof Bishop))
            return false;

        Bishop otherPiece = (Bishop) other;
        if (otherPiece.row == row && otherPiece.column == column && otherPiece.color == color)
            return true;
        else
            return false;
    }
}