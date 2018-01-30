import java.util.ArrayList;

public class Rook extends ChessPiece{


    public Rook(ChessBoard board, Color color) {
        super(board, color);
        whiteCharacter = "\u2656";
        blackCharacter = "\u265C";
    }


    @Override
    public ArrayList<String> legalMoves() {
        ArrayList<String> moves = new ArrayList<>();
        int r = row + 1;
        int c = column;

        // build down
        while (isInbounds(r, c)) {
            if (addMove(r, c, moves))
                break;
            r++;
        }

        // build up
        r = row - 1;
        while (isInbounds(r, c)){
            if (addMove(r, c, moves))
                break;
            r--;
        }
        // build left
        r = row;
        c = column - 1;
        while (isInbounds(r, c)){
            if (addMove(r, c, moves))
                break;
            c--;
        }

        // build right
        c = column + 1;
        while (isInbounds(r, c)){
            if (addMove(r, c, moves))
                break;
            c++;
        }
        return moves;
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
