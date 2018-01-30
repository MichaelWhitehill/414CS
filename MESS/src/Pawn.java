import java.rmi.activation.ActivationGroup_Stub;
import java.util.ArrayList;

public class Pawn extends ChessPiece {

    public Pawn(ChessBoard board, Color color) {
        super(board, color);
        whiteCharacter = "\u2659";
        blackCharacter = "\u265F";
    }

    @Override
    public ArrayList<String> legalMoves() {
        ArrayList<String> moves = new ArrayList<>();
        if (color == Color.BLACK) {
            addMove(row - 1, column, moves);
            if (row == 6)
                addMove(row - 2, column, moves);
        }
        if (color == Color.WHITE) {
            addMove(row + 1, column, moves);
            if (row == 1)
                addMove(row + 2, column, moves);
        }
        checkDiagonal(moves);
        return moves;
    }

    private void checkDiagonal(ArrayList<String> moves){
        try{
            if (color == Color.BLACK){
                // down left
                if(isInbounds(row-1, column - 1) && board.getPiece(ChessBoard.toChessCoordinate(row-1, column-1))!=null)
                    if (board.getPiece(ChessBoard.toChessCoordinate(row-1, column-1)).color != color)
                        moves.add(ChessBoard.toChessCoordinate(row-1, column-1));
                // down right
                if(isInbounds(row-1, column + 1) && board.getPiece(ChessBoard.toChessCoordinate(row-1, column+1))!=null)
                    if (board.getPiece(ChessBoard.toChessCoordinate(row-1, column+1)).color != color)
                        moves.add(ChessBoard.toChessCoordinate(row-1, column+1));
            } else {
                // up left
                if(isInbounds(row+1, column - 1) && board.getPiece(ChessBoard.toChessCoordinate(row+1, column-1))!=null)
                    if (board.getPiece(ChessBoard.toChessCoordinate(row+1, column-1)).color != color)
                        moves.add(ChessBoard.toChessCoordinate(row+1, column-1));
                // up right
                if(isInbounds(row+1, column + 1) && board.getPiece(ChessBoard.toChessCoordinate(row+1, column+1))!=null)
                    if (board.getPiece(ChessBoard.toChessCoordinate(row+1, column+1)).color != color)
                        moves.add(ChessBoard.toChessCoordinate(row+1, column+1));
            }
        } catch (IllegalPositionException e){
            e.printStackTrace();
        }
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
