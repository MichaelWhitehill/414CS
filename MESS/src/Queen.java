import java.util.ArrayList;

public class Queen extends ChessPiece{

    public Queen(ChessBoard board, Color color) {
        super(board, color);
        whiteCharacter = "\u2655";
        blackCharacter = "\u265B";
    }

    @Override
    public ArrayList<String> legalMoves() {
        return null;
    }

    public boolean equals(Object other){
        if (!(other instanceof Queen))
            return false;

        Queen otherPiece = (Queen) other;
        if (otherPiece.row == row && otherPiece.column == column && otherPiece.color == color)
            return true;
        else
            return false;
    }
}
