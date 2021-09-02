import java.util.ArrayList;

public abstract class ChessPiece {

    public enum Color {WHITE, BLACK}

    protected ChessBoard board; // the board it belongs to, default null
    protected int row; // the index of the horizontal rows 0..7
    protected int column; // the index of the vertical column 0..7
    protected Color color; // the color of the piece

    /*
        This constructor sets the board and color attributes.
     */
    public ChessPiece(ChessBoard board, Color color) {
        this.board = board;
        this.color = color;
        this.row = -1;
        this.column = -1;
    }

    /*
        This method returns the color of the piece. There is no need for a setColor method because a piece cannot change
        color.
     */
    public Color getColor() {
        return this.color;
    }

    /*
        This method returns the position of the piece in the format single letter (a..h) followed by a single digit
        (1..8).
     */
    public String getPosition() {
        try {
            return indicesToPosition(row, column);
        } catch (IllegalPositionException e) {
            System.err.printf("Unable to get position for row=%d, col=%d", row, column);
            return "";
        }
    }

    /*
        This method sets the position of the piece to the appropriate row and column based on the argument, which in the
        format single letter (a..h) followed by a single digit (1..8). If the position is illegal for any of the two
        reasons mentioned earlier, throw the stated exception.
     */
    public void setPosition(String position) throws IllegalPositionException {
        if (isValidPosition(position)) {
            this.row = getRow(position);
            this.column = getCol(position);
        } else {
            throw new IllegalPositionException();
        }
    }

    /*
        This method will be implemented in the concrete subclasses corresponding to each chess piece. This method
        returns a String composed of a single character that corresponds to which piece it is. In the unicode character
        encoding scheme there are characters that represent each chess piece. Use one of the following characters:

        character     piece
        ----------   -----------
          "\u2654"   white king
          "\u2655"   white queen
          "\u2656"   white rook
          "\u2657"   white bishop
          "\u2658"   white knight
          "\u2659"   white pawn
          "\u265A"   black king
          "\u265B"   black queen
          "\u265C"   black rook
          "\u265D"   black bishop
          "\u265E"   black knight
          "\u265F"   black pawn
     */
    abstract public String toString();

    // --------- Private Helper Methods ---------

    /*
        This method will be implemented in the concrete subclasses corresponding to each chess piece. This method
        returns all the legal moves that piece can make based on the rules described above in the assignment. Each
        string in the ArrayList should be the position of a possible destination for the piece (in the same format
        described above). If there are multiple legal moves, the order of moves in the ArrayList does not matter.
        If there are no legal moves, return an empty ArrayList, i.e., the size should be zero.
     */
    abstract public ArrayList<String> legalMoves();

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

    /*
        Private method to convert 2D-array indices to a chessboard position (i.e. "e6").
     */
    private String indicesToPosition(int row, int col) throws IllegalPositionException {
        if (row >= 0 && row <= 7 && col >= 0 && col <= 7) {
            return String.format("%c%c", 'a' + col, '8' - row);
        }
        throw new IllegalPositionException();
    }
}
