import java.util.ArrayList;

public class King extends ChessPiece{

    public King(ChessBoard board, Color color) {
        super(board, color);
        whiteCharacter = "\u2654";
        blackCharacter = "\u265A";
    }

    @Override
    public void setPosition(String position) throws IllegalPositionException {

    }


    @Override
    public ArrayList<String> legalMoves() {
        return null;
    }

    public boolean equals(Object other){
        if (!(other instanceof King))
            return false;

        King otherPiece = (King) other;
        if (otherPiece.row == row && otherPiece.column == column && otherPiece.color == color)
            return true;
        else
            return false;
    }
}
