import java.util.ArrayList;

public class King extends ChessPiece {

    public King(ChessBoard board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return (this.color == Color.WHITE) ? "\u2654" : "\u265A";
    }

    @Override
    public ArrayList<String> legalMoves() {
        ArrayList<String> legalMoves = new ArrayList<>();
        return legalMoves;
    }
}
