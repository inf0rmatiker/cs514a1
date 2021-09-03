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
        ArrayList<String> legalMoves = new ArrayList<>();
        int i,j;
        try {

            // span up-right
            for (i = this.row-1, j = this.column+1; i >= 0 && j <= 7; i--, j++) {
                String position = indicesToPosition(i, j);
                ChessPiece piece = board.getPiece(position);
                if (piece == null) {
                    legalMoves.add(position);
                } else {
                    if (piece.color != this.color) {
                        legalMoves.add(position);
                    }
                    break;
                }
            }

            // span up-left
            for (i = this.row-1, j = this.column-1; i >= 0 && j >= 0; i--, j--) {
                String position = indicesToPosition(i, j);
                ChessPiece piece = board.getPiece(position);
                if (piece == null) {
                    legalMoves.add(position);
                } else {
                    if (piece.color != this.color) {
                        legalMoves.add(position);
                    }
                    break;
                }
            }

            // span down-right
            for (i = this.row+1, j = this.column+1; i <= 7 && j <= 7; i++, j++) {
                String position = indicesToPosition(i, j);
                ChessPiece piece = board.getPiece(position);
                if (piece == null) {
                    legalMoves.add(position);
                } else {
                    if (piece.color != this.color) {
                        legalMoves.add(position);
                    }
                    break;
                }
            }

            // span down-left
            for (i = this.row+1, j = this.column-1; i <= 7 && j >= 0; i++, j--) {
                String position = indicesToPosition(i, j);
                ChessPiece piece = board.getPiece(position);
                if (piece == null) {
                    legalMoves.add(position);
                } else {
                    if (piece.color != this.color) {
                        legalMoves.add(position);
                    }
                    break;
                }
            }
        } catch (IllegalPositionException e) {
            System.err.println("Caught IllegalPositionException!");
        }

        return legalMoves;
    }
}
