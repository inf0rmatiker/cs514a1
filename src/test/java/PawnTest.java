import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class PawnTest {

    private static ChessBoard testChessBoard;

    @BeforeAll
    static void initializeTests() {
        testChessBoard = new ChessBoard();
    }

    @Test
    public void testGetColorWhite() {
        Pawn pawn = new Pawn(testChessBoard, ChessPiece.Color.WHITE);
        ChessPiece.Color expected = ChessPiece.Color.WHITE;
        ChessPiece.Color actual = pawn.getColor();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetColorBlack() {
        Pawn pawn = new Pawn(testChessBoard, ChessPiece.Color.BLACK);
        ChessPiece.Color expected = ChessPiece.Color.BLACK;
        ChessPiece.Color actual = pawn.getColor();
        assertEquals(expected, actual);
    }

    @Test
    public void testToStringWhite() {
        Pawn pawn = new Pawn(testChessBoard, ChessPiece.Color.WHITE);
        String expected = "\u2659";
        String actual = pawn.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testToStringBlack() {
        Pawn pawn = new Pawn(testChessBoard, ChessPiece.Color.BLACK);
        String expected = "\u265F";
        String actual = pawn.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPositionDefaultOnBoard() {
        Pawn pawn = new Pawn(testChessBoard, ChessPiece.Color.WHITE);
        pawn.row = 7;
        pawn.column = 2;
        String expected = "c1";
        String actual = pawn.getPosition();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPositionNotOnBoard() {
        Pawn pawn = new Pawn(testChessBoard, ChessPiece.Color.WHITE);
        String expected = "";
        String actual = pawn.getPosition();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetPositionDefaultOnBoard() {
        Pawn pawn = new Pawn(testChessBoard, ChessPiece.Color.WHITE);
        try {
            pawn.setPosition("c1");
            int expectedRow = 7;
            int expectedCol = 2;
            int actualRow = pawn.row;
            int actualCol = pawn.column;
            assertEquals(expectedRow, actualRow);
            assertEquals(expectedCol, actualCol);
        } catch (IllegalPositionException e) {
            fail("Caught IllegalPositionException");
        }
    }

    @Test(expected = IllegalPositionException.class)
    public void testSetPositionNotOnBoard() throws IllegalPositionException {
        new Pawn(testChessBoard, ChessPiece.Color.WHITE).setPosition("c9");
    }

    @Test(expected = IllegalPositionException.class)
    public void testSetPositionEmptyString() throws IllegalPositionException {
        new Pawn(testChessBoard, ChessPiece.Color.WHITE).setPosition("");
    }

    @Test(expected = IllegalPositionException.class)
    public void testSetPositionMalformed() throws IllegalPositionException {
        new Pawn(testChessBoard, ChessPiece.Color.WHITE).setPosition("#!");
    }

    @Test
    public void testLegalMoves() {

    }
}
