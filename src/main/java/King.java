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
        try {
            for (int i = this.row - 1; i <= this.row + 1; i++) {
                for (int j = this.column - 1; j <= this.column + 1; j++) {
                    if (i != this.row || j != this.column) {
                        if (isOnBoard(i,j)) {
                            String position = indicesToPosition(i,j);
                            ChessPiece piece = board.getPiece(position);
                            if (piece == null || piece.color != this.color) {
                                legalMoves.add(indicesToPosition(i,j));
                            }
                        }
                    }
                }
            }
        } catch (IllegalPositionException e) {
            System.err.println("Caught IllegalPositionException!");
        }
        return legalMoves;
    }
}
