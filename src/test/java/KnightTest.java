import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KnightTest {

    private static ChessBoard testChessBoard;

    @Test
    public void testGetColorWhite() {
        Knight knight = new Knight(testChessBoard, ChessPiece.Color.WHITE);
        ChessPiece.Color expected = ChessPiece.Color.WHITE;
        ChessPiece.Color actual = knight.getColor();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetColorBlack() {
        Knight knight = new Knight(testChessBoard, ChessPiece.Color.BLACK);
        ChessPiece.Color expected = ChessPiece.Color.BLACK;
        ChessPiece.Color actual = knight.getColor();
        assertEquals(expected, actual);
    }

    @Test
    public void testToStringWhite() {
        Knight knight = new Knight(testChessBoard, ChessPiece.Color.WHITE);
        String expected = "\u2658";
        String actual = knight.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testToStringBlack() {
        Knight knight = new Knight(testChessBoard, ChessPiece.Color.BLACK);
        String expected = "\u265E";
        String actual = knight.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPositionDefaultOnBoard() {
        Knight knight = new Knight(testChessBoard, ChessPiece.Color.WHITE);
        knight.row = 7;
        knight.column = 2;
        String expected = "c1";
        String actual = knight.getPosition();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPositionNotOnBoard() {
        Knight knight = new Knight(testChessBoard, ChessPiece.Color.WHITE);
        String expected = "";
        String actual = knight.getPosition();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetPositionDefaultOnBoard() {
        Knight knight = new Knight(testChessBoard, ChessPiece.Color.WHITE);
        try {
            knight.setPosition("c1");
            int expectedRow = 7;
            int expectedCol = 2;
            int actualRow = knight.row;
            int actualCol = knight.column;
            assertEquals(expectedRow, actualRow);
            assertEquals(expectedCol, actualCol);
        } catch (IllegalPositionException e) {
            fail("Caught IllegalPositionException");
        }
    }

    @Test
    public void testSetPositionNotOnBoard() {
        assertThrows(IllegalPositionException.class, () -> new Knight(testChessBoard, ChessPiece.Color.WHITE).setPosition("c9"));
    }

    @Test
    public void testSetPositionEmptyString() {
        assertThrows(IllegalPositionException.class, () -> new Knight(testChessBoard, ChessPiece.Color.WHITE).setPosition(""));
    }

    @Test
    public void testSetPositionMalformed() {
        assertThrows(IllegalPositionException.class, () -> new Knight(testChessBoard, ChessPiece.Color.WHITE).setPosition("#!"));
    }

    @Test
    public void testLegalMoves() {
        Knight knight = new Knight(testChessBoard, ChessPiece.Color.BLACK);
        int expected = 0;
        int actual = knight.legalMoves().size();
        assertEquals(expected, actual);
    }
}
