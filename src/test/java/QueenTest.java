import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class QueenTest {

    private static ChessBoard testChessBoard;

    @Test
    public void testGetColorWhite() {
        Queen queen = new Queen(testChessBoard, ChessPiece.Color.WHITE);
        ChessPiece.Color expected = ChessPiece.Color.WHITE;
        ChessPiece.Color actual = queen.getColor();
        assertEquals(actual, expected);
    }

    @Test
    public void testGetColorBlack() {
        Queen queen = new Queen(testChessBoard, ChessPiece.Color.BLACK);
        ChessPiece.Color expected = ChessPiece.Color.BLACK;
        ChessPiece.Color actual = queen.getColor();
        assertEquals(actual, expected);
    }

    @Test
    public void testToStringWhite() {
        Queen queen = new Queen(testChessBoard, ChessPiece.Color.WHITE);
        String expected = "\u2655";
        String actual = queen.toString();
        assertEquals(actual, expected);
    }

    @Test
    public void testToStringBlack() {
        Queen queen = new Queen(testChessBoard, ChessPiece.Color.BLACK);
        String expected = "\u265B";
        String actual = queen.toString();
        assertEquals(actual, expected);
    }

    @Test
    public void testGetPositionDefaultOnBoard() {
        Queen queen = new Queen(testChessBoard, ChessPiece.Color.WHITE);
        queen.row = 7;
        queen.column = 2;
        String expected = "c1";
        String actual = queen.getPosition();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPositionNotOnBoard() {
        Queen queen = new Queen(testChessBoard, ChessPiece.Color.WHITE);
        String expected = "";
        String actual = queen.getPosition();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetPositionDefaultOnBoard() {
        Queen queen = new Queen(testChessBoard, ChessPiece.Color.WHITE);
        try {
            queen.setPosition("c1");
            int expectedRow = 7;
            int expectedCol = 2;
            int actualRow = queen.row;
            int actualCol = queen.column;
            assertEquals(expectedRow, actualRow);
            assertEquals(expectedCol, actualCol);
        } catch (IllegalPositionException e) {
            fail("Caught IllegalPositionException");
        }
    }

    @Test
    public void testSetPositionNotOnBoard() {
        assertThrows(IllegalPositionException.class, () -> new Queen(testChessBoard, ChessPiece.Color.WHITE).setPosition("c9"));
    }

    @Test
    public void testSetPositionEmptyString() {
        assertThrows(IllegalPositionException.class, () -> new Queen(testChessBoard, ChessPiece.Color.WHITE).setPosition(""));
    }

    @Test
    public void testSetPositionMalformed() {
        assertThrows(IllegalPositionException.class, () -> new Queen(testChessBoard, ChessPiece.Color.WHITE).setPosition("#!"));
    }

    @Test
    public void testLegalMovesDefaultPosition() {
        ChessBoard chessBoard = new ChessBoard();
        Queen queen = new Queen(chessBoard, ChessPiece.Color.BLACK);
        assertTrue(chessBoard.placePiece(queen, "d8"));
        assertTrue(queen.legalMoves().isEmpty());

        chessBoard = new ChessBoard();
        queen = new Queen(chessBoard, ChessPiece.Color.WHITE);
        assertTrue(chessBoard.placePiece(queen, "d1"));
        assertTrue(queen.legalMoves().isEmpty());
    }
}
