import java.util.ArrayList;

public class Rook extends ChessPiece{


    public Rook(ChessBoard board, Color color) {
        super(board, color);
        whiteCharacter = "\u2656";
        blackCharacter = "\u265C";
    }


    @Override
    public ArrayList<String> legalMoves() {
        return null;
    }

    public boolean equals(Object other){
        if (!(other instanceof Rook))
            return false;

        Rook otherPiece = (Rook) other;
        if (otherPiece.row == row && otherPiece.column == column && otherPiece.color == color)
            return true;
        else
            return false;
    }
}
