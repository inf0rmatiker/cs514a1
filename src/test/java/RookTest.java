import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class RookTest {

    private static ChessBoard testChessBoard;

    @BeforeAll
    static void initializeTests() {
        testChessBoard = new ChessBoard();
    }

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

    @Test(expected = IllegalPositionException.class)
    public void testSetPositionNotOnBoard() throws IllegalPositionException {
        new Rook(testChessBoard, ChessPiece.Color.WHITE).setPosition("c9");
    }

    @Test(expected = IllegalPositionException.class)
    public void testSetPositionEmptyString() throws IllegalPositionException {
        new Rook(testChessBoard, ChessPiece.Color.WHITE).setPosition("");
    }

    @Test(expected = IllegalPositionException.class)
    public void testSetPositionMalformed() throws IllegalPositionException {
        new Rook(testChessBoard, ChessPiece.Color.WHITE).setPosition("#!");
    }

    @Test
    public void testLegalMoves() {

    }
}
