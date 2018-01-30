import java.util.ArrayList;

public class Knight extends  ChessPiece{

    public Knight(ChessBoard board, Color color) {
        super(board, color);
        whiteCharacter = "\u2658";
        blackCharacter = "\u265E";
    }

    @Override
    public ArrayList<String> legalMoves() {
        return new ArrayList<String>();
    }
}
