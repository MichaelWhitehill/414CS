import java.util.ArrayList;

public class Pawn extends ChessPiece {

    public Pawn(ChessBoard board, Color color) {
        super(board, color);
    }

    public Pawn(ChessBoard board, int row, int column, Color color) {
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
        if (!(other instanceof Pawn))
            return false;

        Pawn otherPiece = (Pawn) other;
        if (otherPiece.row == row && otherPiece.column == column && otherPiece.color == color)
            return true;
        else
            return false;
    }
}
