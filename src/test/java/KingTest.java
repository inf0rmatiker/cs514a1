import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class KingTest {

    @Test
    public void testGetColorWhite() {
        King king = new King(new ChessBoard(), ChessPiece.Color.WHITE);
        ChessPiece.Color expected = ChessPiece.Color.WHITE;
        ChessPiece.Color actual = king.getColor();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetColorBlack() {
        King king = new King(new ChessBoard(), ChessPiece.Color.BLACK);
        ChessPiece.Color expected = ChessPiece.Color.BLACK;
        ChessPiece.Color actual = king.getColor();
        assertEquals(expected, actual);
    }

    @Test
    public void testToStringWhite() {
        King king = new King(new ChessBoard(), ChessPiece.Color.WHITE);
        String expected = "\u2654";
        String actual = king.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testToStringBlack() {
        King king = new King(new ChessBoard(), ChessPiece.Color.BLACK);
        String expected = "\u265A";
        String actual = king.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPositionDefaultOnBoard() {
        King king = new King(new ChessBoard(), ChessPiece.Color.WHITE);
        king.row = 7;
        king.column = 2;
        String expected = "c1";
        String actual = king.getPosition();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPositionNotOnBoard() {
        King king = new King(new ChessBoard(), ChessPiece.Color.WHITE);
        String expected = "";
        String actual = king.getPosition();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetPositionDefaultOnBoard() {
        King king = new King(new ChessBoard(), ChessPiece.Color.WHITE);
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
        assertThrows(IllegalPositionException.class, () -> new King(new ChessBoard(), ChessPiece.Color.WHITE).setPosition("c9"));
    }

    @Test
    public void testSetPositionEmptyString() {
        assertThrows(IllegalPositionException.class, () -> new King(new ChessBoard(), ChessPiece.Color.WHITE).setPosition(""));
    }

    @Test
    public void testSetPositionMalformed() {
        assertThrows(IllegalPositionException.class, () -> new King(new ChessBoard(), ChessPiece.Color.WHITE).setPosition("#!"));
    }

    @Test
    public void testLegalMovesNoOtherPieces() {
        String[] positions = {"e1", "e8", "a5", "h5", "a1"};
        ArrayList<ArrayList<String>> expecteds = new ArrayList<>();
        expecteds.add(new ArrayList<>(Arrays.asList("d1", "d2", "e2", "f2", "f1")));
        expecteds.add(new ArrayList<>(Arrays.asList("d8", "d7", "e7", "f7", "f8")));
        expecteds.add(new ArrayList<>(Arrays.asList("a6", "b6", "b5", "b4", "a4")));
        expecteds.add(new ArrayList<>(Arrays.asList("h6", "g6", "g5", "g4", "h4")));
        expecteds.add(new ArrayList<>(Arrays.asList("a2", "b2", "b1")));

        for (int i = 0; i < positions.length; i++) {
            String position = positions[i];
            ChessBoard chessBoard = new ChessBoard();
            King king = new King(chessBoard, ChessPiece.Color.WHITE); // color doesn't matter here
            assertTrue(chessBoard.placePiece(king, position));
            ArrayList<String> expectedPositions = expecteds.get(i);
            ArrayList<String> actual = king.legalMoves();
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
        King king = new King(chessBoard, ChessPiece.Color.WHITE); // color doesn't matter here
        assertTrue(chessBoard.placePiece(king, position));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.WHITE), "c6"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.WHITE), "d6"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.WHITE), "e6"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.WHITE), "c5"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.WHITE), "e5"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.WHITE), "c4"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.WHITE), "d4"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.WHITE), "e4"));
        assertTrue(king.legalMoves().isEmpty());
    }

    @Test
    public void testLegalMovesCapturePieces() {
        String position = "d5";

        ChessBoard chessBoard = new ChessBoard();
        King king = new King(chessBoard, ChessPiece.Color.WHITE); // color doesn't matter here
        assertTrue(chessBoard.placePiece(king, position));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.BLACK), "c6"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.BLACK), "d6"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.BLACK), "e6"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.BLACK), "c5"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.BLACK), "e5"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.BLACK), "c4"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.BLACK), "d4"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.BLACK), "e4"));

        ArrayList<String> expecteds = new ArrayList<>(Arrays.asList("c6","d6","e6","c5","e5","c4","d4","e4"));
        ArrayList<String> actuals = king.legalMoves();
        for (String expected: expecteds) {
            assertTrue(actuals.contains(expected));
        }
    }
}
