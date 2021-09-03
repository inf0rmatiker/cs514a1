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
        ArrayList<String> legalMoves = new ArrayList<>();

        try {
            if (this.color == Color.BLACK) {
                // If we are not at the end of the board
                if (this.row < 7) {

                    // Check position directly in front
                    String positionOneAhead = indicesToPosition(this.row+1, this.column);
                    if (this.board.getPiece(positionOneAhead) == null) {
                        legalMoves.add(positionOneAhead);

                        // If we are at starting position
                        if (this.row == 1) {
                            // Check position two spaces in front
                            String positionTwoAhead = indicesToPosition(this.row+2, this.column);
                            if (this.board.getPiece(positionTwoAhead) == null) {
                                legalMoves.add(positionTwoAhead);
                            }
                        }
                    }

                    // If we have space to move diagonally down-right
                    if (this.column < 7) {
                        // Check position diagonally down-right
                        String positionDownRight = indicesToPosition(this.row+1, this.column+1);
                        ChessPiece pieceDownRight = this.board.getPiece(positionDownRight);
                        if (pieceDownRight != null && pieceDownRight.color == Color.WHITE) {
                            legalMoves.add(positionDownRight);
                        }
                    }

                    // If we have space to move diagonally down-left
                    if (this.column > 0) {
                        // Check position diagonally down-left
                        String positionDownLeft = indicesToPosition(this.row+1, this.column-1);
                        ChessPiece pieceDownLeft = this.board.getPiece(positionDownLeft);
                        if (pieceDownLeft != null && pieceDownLeft.color == Color.WHITE) {
                            legalMoves.add(positionDownLeft);
                        }
                    }
                }
            } else { // Color = WHITE
                // If we are not at the end of the board
                if (this.row > 0) {

                    // Check position directly in front
                    String positionOneAhead = indicesToPosition(this.row-1, this.column);
                    if (this.board.getPiece(positionOneAhead) == null) {
                        legalMoves.add(positionOneAhead);

                        // If we are at starting position
                        if (this.row == 6) {
                            // Check position two spaces in front
                            String positionTwoAhead = indicesToPosition(this.row-2, this.column);
                            if (this.board.getPiece(positionTwoAhead) == null) {
                                legalMoves.add(positionTwoAhead);
                            }
                        }
                    }

                    // If we have space to move diagonally up-right
                    if (this.column < 7) {
                        // Check position diagonally up-right
                        String positionUpRight = indicesToPosition(this.row-1, this.column+1);
                        ChessPiece pieceUpRight = this.board.getPiece(positionUpRight);
                        if (pieceUpRight != null && pieceUpRight.color == Color.BLACK) {
                            legalMoves.add(positionUpRight);
                        }
                    }

                    // If we have space to move diagonally up-left
                    if (this.column > 0) {
                        // Check position diagonally down-left
                        String positionUpLeft = indicesToPosition(this.row-1, this.column-1);
                        ChessPiece pieceUpLeft = this.board.getPiece(positionUpLeft);
                        if (pieceUpLeft != null && pieceUpLeft.color == Color.BLACK) {
                            legalMoves.add(positionUpLeft);
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
