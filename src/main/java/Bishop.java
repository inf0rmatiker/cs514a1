import java.util.ArrayList;

public class Bishop extends ChessPiece {

    public Bishop(ChessBoard board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return (this.color == Color.WHITE) ? "\u2657" : "\u265D";
    }

    @Override
    public ArrayList<String> legalMoves() {
        return null;
    }
}
