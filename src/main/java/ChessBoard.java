import java.util.ArrayList;

public class ChessBoard {

    private ChessPiece[][] board;

    public ChessBoard() {
        this.board = new ChessPiece[8][8];
    }

    /*
        This method initializes the board to the standard chess opening state with indexing as shown in the figure.
        This method should use the constructors of the appropriate pieces, and call placePiece below to place the newly
        constructed pieces in the right position.
    */
    public void initialize() {
        this.placePiece(new Rook(this, ChessPiece.Color.BLACK), "a8");
        this.placePiece(new Knight(this, ChessPiece.Color.BLACK), "b8");
        this.placePiece(new Bishop(this, ChessPiece.Color.BLACK), "c8");
        this.placePiece(new Queen(this, ChessPiece.Color.BLACK), "d8");
        this.placePiece(new King(this, ChessPiece.Color.BLACK), "e8");
        this.placePiece(new Bishop(this, ChessPiece.Color.BLACK), "f8");
        this.placePiece(new Knight(this, ChessPiece.Color.BLACK), "g8");
        this.placePiece(new Rook(this, ChessPiece.Color.BLACK), "h8");
        for (char col = 'a'; col <= 'h'; col++) {
            String positionBlackPawn = String.format("%c%c", col, '7');
            this.placePiece(new Pawn(this, ChessPiece.Color.BLACK), positionBlackPawn);
            String positionWhitePawn = String.format("%c%c", col, '2');
            this.placePiece(new Pawn(this, ChessPiece.Color.WHITE), positionWhitePawn);
        }
        this.placePiece(new Rook(this, ChessPiece.Color.WHITE), "a1");
        this.placePiece(new Knight(this, ChessPiece.Color.WHITE), "b1");
        this.placePiece(new Bishop(this, ChessPiece.Color.WHITE), "c1");
        this.placePiece(new Queen(this, ChessPiece.Color.WHITE), "d1");
        this.placePiece(new King(this, ChessPiece.Color.WHITE), "e1");
        this.placePiece(new Bishop(this, ChessPiece.Color.WHITE), "f1");
        this.placePiece(new Knight(this, ChessPiece.Color.WHITE), "g1");
        this.placePiece(new Rook(this, ChessPiece.Color.WHITE), "h1");
    }

    /*
        This method returns the chess piece at a given position. The position is represented as a two-character string
        (e.g., e8) as described above. The first letter is in lowercase (a..h) and the second letter is a digit (1..8).
        If the position is illegal because the string contains illegal characters or represents a position outside the
        board, the exception is thrown.
     */
    public ChessPiece getPiece(String position) throws IllegalPositionException {
        if (isValidPosition(position)) {
            return this.board[getRow(position)][getCol(position)];
        }
        throw new IllegalPositionException();
    }

    /*
        This method tries to place the given piece at a given position, and returns true if successful, and false if
        there is already a piece of the same player in the given position or the position was illegal for any of the two
        reasons mentioned in the description of getPiece. If an opponent's piece exists, that piece is captured. If
        successful, this method should call an appropriate method in the ChessPiece class (i.e., setPosition) to set the
        piece's position.
     */
    public boolean placePiece(ChessPiece piece, String position) {
        try {
            ChessPiece existingPiece = getPiece(position); // also checks validity of position
            if (existingPiece != null && existingPiece.color == piece.color) {
                return false; // you can't replace one of your own pieces!
            }

            this.board[getRow(position)][getCol(position)] = piece;
            piece.setPosition(position);
        } catch (IllegalPositionException e) {
            return false; // position must have been illegal
        }
        return true; // OK
    }

    /*
        This method checks if moving the piece from the fromPosition to toPosition is a legal move. Legality is defined
        based on the rules described above in Section 2.1. If the move is legal, it executes the move, changing the
        value of the board as needed. Otherwise, the stated exception is thrown.
     */
    public void move(String fromPosition, String toPosition) throws IllegalMoveException {
        try {
            ChessPiece fromPiece = getPiece(fromPosition); // checks fromPosition
            if (fromPiece == null) {
                throw new IllegalMoveException();
            }

            ArrayList<String> legalMoves = fromPiece.legalMoves();
            if (legalMoves.contains(toPosition)) {
                if (!placePiece(fromPiece, toPosition)) {
                    throw new IllegalMoveException();
                }
            } else {
                throw new IllegalMoveException();
            }
        } catch (IllegalPositionException e) {
            throw new IllegalMoveException();
        }
    }

    /*
        You must include the following toString method to help debug your program. We assume that ChessPiece has an
        appropriately implemented toString method, whose implementation is described below.
     */
    public String toString() {
        String chess = "";
        String upperLeft = "\u250C";
        String upperRight = "\u2510";
        String horizontalLine = "\u2500";
        String horizontal3 = horizontalLine + "\u3000" + horizontalLine;
        String verticalLine = "\u2502";
        String upperT = "\u252C";
        String bottomLeft = "\u2514";
        String bottomRight = "\u2518";
        String bottomT = "\u2534";
        String plus = "\u253C";
        String leftT = "\u251C";
        String rightT = "\u2524";

        String topLine = upperLeft;
        for (int i = 0; i < 7; i++) {
            topLine += horizontal3 + upperT;
        }
        topLine += horizontal3 + upperRight;

        String bottomLine = bottomLeft;
        for (int i = 0; i < 7; i++) {
            bottomLine += horizontal3 + bottomT;
        }
        bottomLine += horizontal3 + bottomRight;
        chess += topLine + "\n";

        for (int row = 7; row >= 0; row--) {
            String midLine = "";
            for (int col = 0; col < 8; col++) {
                if (board[row][col] == null) {
                    midLine += verticalLine + " \u3000 ";
                }
                else {
                    midLine += verticalLine + " " + board[row][col] + " ";
                }
            }
            midLine += verticalLine;
            String midLine2 = leftT;
            for (int i = 0; i < 7; i++) {
                midLine2 += horizontal3 + plus;
            }
            midLine2 += horizontal3 + rightT;
            chess += midLine + "\n";
            if (row >= 1)
                chess += midLine2 + "\n";
        }

        chess += bottomLine;
        return chess;
    }

    // --------- Private Helper Methods ---------

    private int getRow(String position) {
        return '8' - position.charAt(1);
    }

    private int getCol(String position) {
        return position.charAt(0) - 'a';
    }

    /*
        Private method to check validity of chess board position.
     */
    private boolean isValidPosition(String position) {
        if (position.length() != 2) {
            return false;
        }

        char col = position.charAt(0);
        char row = position.charAt(1);
        return (col >= 'a' && col <= 'h' && row >= '1' && row <= '8');
    }

    private boolean isInvalidPosition(String position) {
        return !isValidPosition(position);
    }
}
