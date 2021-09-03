import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

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
    public void testLegalMovesNoOtherPieces() {
        String[] positions = {"a8", "h8", "a1", "h1", "d5"};
        ArrayList<ArrayList<String>> expecteds = new ArrayList<>();
        expecteds.add(new ArrayList<>(Arrays.asList("a1", "a2", "a3", "a4", "a5", "a6", "a7", "b8", "c8", "d8", "e8", "f8", "g8", "h8")));
        expecteds.add(new ArrayList<>(Arrays.asList("h1", "h2", "h3", "h4", "h5", "h6", "h7", "a8", "b8", "c8", "d8", "e8", "f8", "g8")));
        expecteds.add(new ArrayList<>(Arrays.asList("a2", "a3", "a4", "a5", "a6", "a7", "a8", "b1", "c1", "d1", "e1", "f1", "g1", "h1")));
        expecteds.add(new ArrayList<>(Arrays.asList("h2", "h3", "h4", "h5", "h6", "h7", "h8", "a1", "b1", "c1", "d1", "e1", "f1", "g1")));
        expecteds.add(new ArrayList<>(Arrays.asList("a5", "b5", "c5", "e5", "f5", "g5", "h5", "d8", "d7", "d6", "d4", "d3", "d2", "d1")));

        for (int i = 0; i < positions.length; i++) {
            String position = positions[i];
            ChessBoard chessBoard = new ChessBoard();
            Rook rook = new Rook(chessBoard, ChessPiece.Color.WHITE); // color doesn't matter here
            assertTrue(chessBoard.placePiece(rook, position));
            ArrayList<String> expectedPositions = expecteds.get(i);
            ArrayList<String> actual = rook.legalMoves();
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
        Rook rook = new Rook(chessBoard, ChessPiece.Color.WHITE); // color doesn't matter here
        assertTrue(chessBoard.placePiece(rook, position));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.WHITE), "c6"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.WHITE), "d6"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.WHITE), "e6"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.WHITE), "c5"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.WHITE), "e5"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.WHITE), "c4"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.WHITE), "d4"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.WHITE), "e4"));
        assertTrue(rook.legalMoves().isEmpty());
    }

    @Test
    public void testLegalMovesCapturePieces() {
        String position = "d5";

        ChessBoard chessBoard = new ChessBoard();
        Rook rook = new Rook(chessBoard, ChessPiece.Color.WHITE); // color doesn't matter here
        assertTrue(chessBoard.placePiece(rook, position));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.BLACK), "c6"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.BLACK), "d6"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.BLACK), "e6"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.BLACK), "c5"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.BLACK), "e5"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.BLACK), "c4"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.BLACK), "d4"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.BLACK), "e4"));

        ArrayList<String> expecteds = new ArrayList<>(Arrays.asList("c5", "d6", "e5", "d4"));
        ArrayList<String> actuals = rook.legalMoves();
        for (String expected: expecteds) {
            assertTrue(actuals.contains(expected));
        }
    }
}
