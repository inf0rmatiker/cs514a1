import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class BishopTest {

    private static ChessBoard testChessBoard;

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

    @Test
    public void testSetPositionNotOnBoard() {
        assertThrows(IllegalPositionException.class, () -> new Bishop(testChessBoard, ChessPiece.Color.WHITE).setPosition("c9"));
    }

    @Test
    public void testSetPositionEmptyString() {
        assertThrows(IllegalPositionException.class, () -> new Bishop(testChessBoard, ChessPiece.Color.WHITE).setPosition(""));
    }

    @Test
    public void testSetPositionMalformed() throws IllegalPositionException {
        assertThrows(IllegalPositionException.class, () -> new Bishop(testChessBoard, ChessPiece.Color.WHITE).setPosition("#!"));
    }

    @Test
    public void testLegalMovesNoOtherPieces() {
        String[] positions = {"a8", "h8", "a1", "h1", "d5"};
        ArrayList<ArrayList<String>> expecteds = new ArrayList<>();
        expecteds.add(new ArrayList<>(Arrays.asList("b7", "c6", "d5", "e4", "f3", "g2", "h1")));
        expecteds.add(new ArrayList<>(Arrays.asList("g7", "f6", "e5", "d4", "c3", "b2", "a1")));
        expecteds.add(new ArrayList<>(Arrays.asList("h8", "g7", "f6", "e5", "d4", "c3", "b2")));
        expecteds.add(new ArrayList<>(Arrays.asList("a8", "b7", "c6", "d5", "e4", "f3", "g2")));
        expecteds.add(new ArrayList<>(Arrays.asList("a8", "b7", "c6", "e4", "f3", "g2", "h1", "g8", "f7", "e6", "c4", "b3", "a2")));

        for (int i = 0; i < positions.length; i++) {
            String position = positions[i];
            ChessBoard chessBoard = new ChessBoard();
            Bishop bishop = new Bishop(chessBoard, ChessPiece.Color.WHITE); // color doesn't matter here
            assertTrue(chessBoard.placePiece(bishop, position));
            ArrayList<String> expectedPositions = expecteds.get(i);
            ArrayList<String> actual = bishop.legalMoves();
            assertNotNull(actual);
            assertEquals(expectedPositions.size(), actual.size());
            for (String expected: expectedPositions) {
                assertTrue(actual.contains(expected));
            }
        }
    }

    @Test
    public void testLegalMovesBlockingPieces() {
        String position = "d5";

        ChessBoard chessBoard = new ChessBoard();
        Bishop bishop = new Bishop(chessBoard, ChessPiece.Color.WHITE); // color doesn't matter here
        assertTrue(chessBoard.placePiece(bishop, position));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.WHITE), "c6"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.WHITE), "d6"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.WHITE), "e6"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.WHITE), "c5"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.WHITE), "e5"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.WHITE), "c4"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.WHITE), "d4"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.WHITE), "e4"));
        assertTrue(bishop.legalMoves().isEmpty());
    }

    @Test
    public void testLegalMovesCapturePieces() {
        String position = "d5";

        ChessBoard chessBoard = new ChessBoard();
        Bishop bishop = new Bishop(chessBoard, ChessPiece.Color.WHITE); // color doesn't matter here
        assertTrue(chessBoard.placePiece(bishop, position));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.BLACK), "c6"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.BLACK), "d6"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.BLACK), "e6"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.BLACK), "c5"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.BLACK), "e5"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.BLACK), "c4"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.BLACK), "d4"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.BLACK), "e4"));

        ArrayList<String> expecteds = new ArrayList<>(Arrays.asList("c6", "e6", "c4", "e4"));
        ArrayList<String> actuals = bishop.legalMoves();
        for (String expected: expecteds) {
            assertTrue(actuals.contains(expected));
        }
    }
}
