import java.util.ArrayList;

public class Bishop extends ChessPiece {

    public Bishop(ChessBoard board, ChessPiece.Color color) {
        super(board, color);
        whiteCharacter = "\u2657";
        blackCharacter = "\u265D";
    }


    @Override
    public ArrayList<String> legalMoves() {
        ArrayList<String> moves = new ArrayList<>();
        int r = row + 1;
        int c = column + 1;

        // build down/right
        while (isInbounds(r, c)) {
            if (addMove(r, c, moves))
                break;
            r++;
            c++;
        }

        // build down/left
        r = row + 1;
        c = column - 1;
        while (isInbounds(r, c)) {
            if (addMove(r, c, moves))
                break;
            r++;
            c--;
        }

        // build up left
        r = row - 1;
        c = column -1;
        while (isInbounds(r, c)){
            if (addMove(r, c, moves))
                break;
            r--;
            c--;
        }

        // build up right
        r = row - 1;
        c = column + 1;
        while (isInbounds(r, c)) {
            if (addMove(r, c, moves))
                break;
            r--;
            c++;
        }
        return moves;
    }

    public boolean equals(Object other){
        if (!(other instanceof Bishop))
            return false;

        Bishop otherPiece = (Bishop) other;
        return otherPiece.row == row && otherPiece.column == column && otherPiece.color == color;
    }
}