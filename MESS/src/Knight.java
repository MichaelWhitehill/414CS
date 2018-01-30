import java.util.ArrayList;

public class Knight extends  ChessPiece{

    public Knight(ChessBoard board, Color color) {
        super(board, color);
        whiteCharacter = "\u2658";
        blackCharacter = "\u265E";
    }

    @Override
    public void setPosition(String position) throws IllegalPositionException {

    }

    @Override
    public ArrayList<String> legalMoves() {
        return null;
    }
}
