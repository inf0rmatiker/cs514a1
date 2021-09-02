import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class KingTest {

    private static ChessBoard testChessBoard;

    static void initializeTests() {
        testChessBoard = new ChessBoard();
    }

    @Test
    public void testGetColorWhite() {
        King king = new King(testChessBoard, ChessPiece.Color.WHITE);
        ChessPiece.Color expected = ChessPiece.Color.WHITE;
        ChessPiece.Color actual = king.getColor();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetColorBlack() {
        King king = new King(testChessBoard, ChessPiece.Color.BLACK);
        ChessPiece.Color expected = ChessPiece.Color.BLACK;
        ChessPiece.Color actual = king.getColor();
        assertEquals(expected, actual);
    }

    @Test
    public void testToStringWhite() {
        King king = new King(testChessBoard, ChessPiece.Color.WHITE);
        String expected = "\u2654";
        String actual = king.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testToStringBlack() {
        King king = new King(testChessBoard, ChessPiece.Color.BLACK);
        String expected = "\u265A";
        String actual = king.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPositionDefaultOnBoard() {
        King king = new King(testChessBoard, ChessPiece.Color.WHITE);
        king.row = 7;
        king.column = 2;
        String expected = "c1";
        String actual = king.getPosition();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPositionNotOnBoard() {
        King king = new King(testChessBoard, ChessPiece.Color.WHITE);
        String expected = "";
        String actual = king.getPosition();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetPositionDefaultOnBoard() {
        King king = new King(testChessBoard, ChessPiece.Color.WHITE);
        try {
            king.setPosition("c1");
            int expectedRow = 7;
            int expectedCol = 2;
            int actualRow = king.row;
            int actualCol = king.column;
            assertEquals(expectedRow, actualRow);
            assertEquals(expectedCol, actualCol);
        } catch (IllegalPositionException e) {
            fail("Caught IllegalPositionException");
        }
    }

    @Test
    public void testSetPositionNotOnBoard() {
        assertThrows(IllegalPositionException.class, () -> new King(testChessBoard, ChessPiece.Color.WHITE).setPosition("c9"));
    }

    @Test
    public void testSetPositionEmptyString() {
        assertThrows(IllegalPositionException.class, () -> new King(testChessBoard, ChessPiece.Color.WHITE).setPosition(""));
    }

    @Test
    public void testSetPositionMalformed() {
        assertThrows(IllegalPositionException.class, () -> new King(testChessBoard, ChessPiece.Color.WHITE).setPosition("#!"));
    }

    @Test
    public void testLegalMovesNoOtherPiecesCenterBoard() {
        initializeTests();
        King king = new King(testChessBoard, ChessPiece.Color.BLACK);
        if (testChessBoard.placePiece(king, "d4")) {
            ArrayList<String> legalMoves = king.legalMoves();
            assertNotNull(legalMoves);


        } else {
            fail("Unable to place King at d4");
        }
    }
}
