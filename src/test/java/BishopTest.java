import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class BishopTest {

    private static ChessBoard testChessBoard;

    @BeforeAll
    static void initializeTests() {
        testChessBoard = new ChessBoard();
    }

    @Test
    public void testGetColorWhite() {
        Bishop bishop = new Bishop(testChessBoard, ChessPiece.Color.WHITE);
        ChessPiece.Color expected = ChessPiece.Color.WHITE;
        ChessPiece.Color actual = bishop.getColor();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetColorBlack() {
        Bishop bishop = new Bishop(testChessBoard, ChessPiece.Color.BLACK);
        ChessPiece.Color expected = ChessPiece.Color.BLACK;
        ChessPiece.Color actual = bishop.getColor();
        assertEquals(expected, actual);
    }

    @Test
    public void testToStringWhite() {
        Bishop bishop = new Bishop(testChessBoard, ChessPiece.Color.WHITE);
        String expected = "\u2657";
        String actual = bishop.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testToStringBlack() {
        Bishop bishop = new Bishop(testChessBoard, ChessPiece.Color.BLACK);
        String expected = "\u265D";
        String actual = bishop.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPositionDefaultOnBoard() {
        Bishop bishop = new Bishop(testChessBoard, ChessPiece.Color.WHITE);
        bishop.row = 7;
        bishop.column = 2;
        String expected = "c1";
        String actual = bishop.getPosition();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPositionNotOnBoard() {
        Bishop bishop = new Bishop(testChessBoard, ChessPiece.Color.WHITE);
        String expected = "";
        String actual = bishop.getPosition();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetPositionDefaultOnBoard() {
        Bishop bishop = new Bishop(testChessBoard, ChessPiece.Color.WHITE);
        try {
            bishop.setPosition("c1");
            int expectedRow = 7;
            int expectedCol = 2;
            int actualRow = bishop.row;
            int actualCol = bishop.column;
            assertEquals(expectedRow, actualRow);
            assertEquals(expectedCol, actualCol);
        } catch (IllegalPositionException e) {
            fail("Caught IllegalPositionException");
        }
    }

    @Test(expected = IllegalPositionException.class)
    public void testSetPositionNotOnBoard() throws IllegalPositionException {
        new Bishop(testChessBoard, ChessPiece.Color.WHITE).setPosition("c9");
    }

    @Test(expected = IllegalPositionException.class)
    public void testSetPositionEmptyString() throws IllegalPositionException {
        new Bishop(testChessBoard, ChessPiece.Color.WHITE).setPosition("");
    }

    @Test(expected = IllegalPositionException.class)
    public void testSetPositionMalformed() throws IllegalPositionException {
        new Bishop(testChessBoard, ChessPiece.Color.WHITE).setPosition("#!");
    }

    @Test
    public void testLegalMoves() {

    }
}
