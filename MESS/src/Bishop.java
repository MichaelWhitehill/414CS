import java.util.ArrayList;

public class Bishop extends ChessPiece {

    public Bishop(ChessBoard board, ChessPiece.Color color) {
        super(board, color);
        whiteCharacter = "\u2657";
        blackCharacter = "\u265D";
    }

    @Override
    public void setPosition(String position) throws IllegalPositionException {
        if (!(legalMoves().contains(position)))
            throw new IllegalPositionException();
        // save our state because the board has to null out out properties in remove
        ChessBoard board = this.board;

        // null out old destination
        board.removePiece(ChessBoard.toChessCoordinate(row,column));
        this.board = board;
        // remove piece where we're going
        board.removePiece(position);
        // make a new piece where we want
        board.placePiece(this, position);
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

    private boolean addMove(int r, int c, ArrayList<String> moves){
        try{
            if (board.getPiece(ChessBoard.toChessCoordinate(r,c)) == null){
                moves.add(ChessBoard.toChessCoordinate(r,c));
            } else if (board.getPiece(ChessBoard.toChessCoordinate(r,c)).color != this.color){
                moves.add(ChessBoard.toChessCoordinate(r,c));
                return true;
            } else if (board.getPiece(ChessBoard.toChessCoordinate(r,c)).color == this.color){
                return true;
            }
            else {
                System.err.println("logic error in bishop legal moves");
                return true;
            }
        } catch (IllegalPositionException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean equals(Object other){
        if (!(other instanceof Bishop))
            return false;

        Bishop otherPiece = (Bishop) other;
        if (otherPiece.row == row && otherPiece.column == column && otherPiece.color == color)
            return true;
        else
            return false;
    }
}