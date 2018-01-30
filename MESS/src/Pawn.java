import java.util.ArrayList;

public class Pawn extends ChessPiece {

    public Pawn(ChessBoard board, Color color) {
        super(board, color);
        whiteCharacter = "\u2659";
        blackCharacter = "\u265F";
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
