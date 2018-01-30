import java.util.ArrayList;

public class King extends ChessPiece{

    public King(ChessBoard board, Color color) {
        super(board, color);
        whiteCharacter = "\u2654";
        blackCharacter = "\u265A";
    }


    @Override
    public ArrayList<String> legalMoves() {
        ArrayList<String> moves = new ArrayList<>();
        if (isInbounds(row+1, column+1))
            addMove(row+1, column+1, moves);

        if (isInbounds(row+1, column))
            addMove(row+1, column, moves);

        if (isInbounds(row+1, column-1))
            addMove(row+1, column-1, moves);

        if (isInbounds(row, column-1))
            addMove(row, column-1, moves);

        if (isInbounds(row-1, column-1))
            addMove(row-1, column-1, moves);

        if (isInbounds(row-1, column))
            addMove(row-1, column, moves);

        if (isInbounds(row-1, column+1))
            addMove(row-1, column+1, moves);

        if (isInbounds(row, column+1))
            addMove(row, column+1, moves);

        return moves;
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
