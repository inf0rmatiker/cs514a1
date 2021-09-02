import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RookTest {

    private static ChessBoard testChessBoard;

    @Test
    public void testGetColorWhite() {
        Rook rook = new Rook(testChessBoard, ChessPiece.Color.WHITE);
        ChessPiece.Color expected = ChessPiece.Color.WHITE;
        ChessPiece.Color actual = rook.getColor();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetColorBlack() {
        Rook rook = new Rook(testChessBoard, ChessPiece.Color.BLACK);
        ChessPiece.Color expected = ChessPiece.Color.BLACK;
        ChessPiece.Color actual = rook.getColor();
        assertEquals(expected, actual);
    }

    @Test
    public void testToStringWhite() {
        Rook rook = new Rook(testChessBoard, ChessPiece.Color.WHITE);
        String expected = "\u2656";
        String actual = rook.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testToStringBlack() {
        Rook rook = new Rook(testChessBoard, ChessPiece.Color.BLACK);
        String expected = "\u265C";
        String actual = rook.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPositionDefaultOnBoard() {
        Rook rook = new Rook(testChessBoard, ChessPiece.Color.WHITE);
        rook.row = 7;
        rook.column = 2;
        String expected = "c1";
        String actual = rook.getPosition();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPositionNotOnBoard() {
        Rook rook = new Rook(testChessBoard, ChessPiece.Color.WHITE);
        String expected = "";
        String actual = rook.getPosition();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetPositionDefaultOnBoard() {
        Rook rook = new Rook(testChessBoard, ChessPiece.Color.WHITE);
        try {
            rook.setPosition("c1");
            int expectedRow = 7;
            int expectedCol = 2;
            int actualRow = rook.row;
            int actualCol = rook.column;
            assertEquals(expectedRow, actualRow);
            assertEquals(expectedCol, actualCol);
        } catch (IllegalPositionException e) {
            fail("Caught IllegalPositionException");
        }
    }

    @Test
    public void testSetPositionNotOnBoard() {
        assertThrows(IllegalPositionException.class, () -> new Rook(testChessBoard, ChessPiece.Color.WHITE).setPosition("c9"));
    }

    @Test
    public void testSetPositionEmptyString() {
        assertThrows(IllegalPositionException.class, () -> new Rook(testChessBoard, ChessPiece.Color.WHITE).setPosition(""));
    }

    @Test
    public void testSetPositionMalformed() {
        assertThrows(IllegalPositionException.class, () -> new Rook(testChessBoard, ChessPiece.Color.WHITE).setPosition("#!"));
    }

    @Test
    public void testLegalMoves() {

    }
}
