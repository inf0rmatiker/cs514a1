import java.util.ArrayList;

public class Pawn extends ChessPiece {

    public Pawn(ChessBoard board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return (this.color == Color.WHITE) ? "\u2659" : "\u265F";
    }

    @Override
    public ArrayList<String> legalMoves() {
        return null;
    }
}
