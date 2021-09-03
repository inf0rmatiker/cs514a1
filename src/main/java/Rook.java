import java.lang.reflect.Array;
import java.util.ArrayList;

public class Rook extends ChessPiece {

    public Rook(ChessBoard board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return (this.color == Color.WHITE) ? "\u2656" : "\u265C";
    }

    @Override
    public ArrayList<String> legalMoves() {
        ArrayList<String> legalMoves = new ArrayList<>();

        try {
            // span up until you hit a piece
            for (int i = this.row-1; i >= 0; i--) {
                String position = indicesToPosition(i, this.column);
                ChessPiece piece = board.getPiece(position);
                if (piece == null) {
                    legalMoves.add(position);
                } else {
                    if (piece.color != this.color) {
                        legalMoves.add(position);
                    }
                    break; // hit a piece, can't go beyond it
                }
            }

            // span down until you hit a piece
            for (int i = this.row+1; i <= 7; i++) {
                String position = indicesToPosition(i, this.column);
                ChessPiece piece = board.getPiece(position);
                if (piece == null) {
                    legalMoves.add(position);
                } else {
                    if (piece.color != this.color) {
                        legalMoves.add(position);
                    }
                    break; // hit a piece, can't go beyond it
                }
            }

            // span right until you hit a piece
            for (int j = this.column+1; j <= 7; j++) {
                String position = indicesToPosition(this.row, j);
                ChessPiece piece = board.getPiece(position);
                if (piece == null) {
                    legalMoves.add(position);
                } else {
                    if (piece.color != this.color) {
                        legalMoves.add(position);
                    }
                    break; // hit a piece, can't go beyond it
                }
            }

            // span left until you hit a piece
            for (int j = this.column-1; j >= 0; j--) {
                String position = indicesToPosition(this.row, j);
                ChessPiece piece = board.getPiece(position);
                if (piece == null) {
                    legalMoves.add(position);
                } else {
                    if (piece.color != this.color) {
                        legalMoves.add(position);
                    }
                    break; // hit a piece, can't go beyond it
                }
            }
        } catch (IllegalPositionException e) {
            System.err.println("Caught IllegalPositionException!");
        }


        return legalMoves;
    }
}
