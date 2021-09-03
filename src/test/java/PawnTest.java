import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PawnTest {

    @Test
    public void testGetColorWhite() {
        Pawn pawn = new Pawn(new ChessBoard(), ChessPiece.Color.WHITE);
        ChessPiece.Color expected = ChessPiece.Color.WHITE;
        ChessPiece.Color actual = pawn.getColor();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetColorBlack() {
        Pawn pawn = new Pawn(new ChessBoard(), ChessPiece.Color.BLACK);
        ChessPiece.Color expected = ChessPiece.Color.BLACK;
        ChessPiece.Color actual = pawn.getColor();
        assertEquals(expected, actual);
    }

    @Test
    public void testToStringWhite() {
        Pawn pawn = new Pawn(new ChessBoard(), ChessPiece.Color.WHITE);
        String expected = "\u2659";
        String actual = pawn.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testToStringBlack() {
        Pawn pawn = new Pawn(new ChessBoard(), ChessPiece.Color.BLACK);
        String expected = "\u265F";
        String actual = pawn.toString();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPositionDefaultOnBoard() {
        Pawn pawn = new Pawn(new ChessBoard(), ChessPiece.Color.WHITE);
        pawn.row = 7;
        pawn.column = 2;
        String expected = "c1";
        String actual = pawn.getPosition();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPositionNotOnBoard() {
        Pawn pawn = new Pawn(new ChessBoard(), ChessPiece.Color.WHITE);
        String expected = "";
        String actual = pawn.getPosition();
        assertEquals(expected, actual);
    }

    @Test
    public void testSetPositionDefaultOnBoard() {
        Pawn pawn = new Pawn(new ChessBoard(), ChessPiece.Color.WHITE);
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

    @Test
    public void testSetPositionNotOnBoard() {
        assertThrows(IllegalPositionException.class, () -> new Pawn(new ChessBoard(), ChessPiece.Color.WHITE).setPosition("c9"));
    }

    @Test
    public void testSetPositionEmptyString() {
        assertThrows(IllegalPositionException.class, () -> new Pawn(new ChessBoard(), ChessPiece.Color.WHITE).setPosition(""));
    }

    @Test
    public void testSetPositionMalformed() {
        assertThrows(IllegalPositionException.class, () -> new Pawn(new ChessBoard(), ChessPiece.Color.WHITE).setPosition("#!"));
    }

    @Test
    public void testLegalMovesCapturePieceLeft() {
        // Test white pawn
        ChessBoard chessBoard = new ChessBoard();
        Pawn pawn = new Pawn(chessBoard, ChessPiece.Color.WHITE);
        assertTrue(chessBoard.placePiece(pawn, "c5"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.BLACK), "b6"));
        ArrayList<String> actuals = pawn.legalMoves();
        assertNotNull(actuals);

        ArrayList<String> expecteds = new ArrayList<>(Arrays.asList("c6", "b6"));
        assertEquals(expecteds.size(), actuals.size());
        for (String expected: expecteds) {
            assertTrue(actuals.contains(expected));
        }

        // Test black pawn
        chessBoard = new ChessBoard();
        pawn = new Pawn(chessBoard, ChessPiece.Color.BLACK);
        assertTrue(chessBoard.placePiece(pawn, "c5"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.WHITE), "b4"));
        actuals = pawn.legalMoves();
        assertNotNull(actuals);

        expecteds = new ArrayList<>(Arrays.asList("c4", "b4"));
        assertEquals(expecteds.size(), actuals.size());
        for (String expected: expecteds) {
            assertTrue(actuals.contains(expected));
        }
    }

    @Test
    public void testLegalMovesCapturePieceRight() {
        // Test white pawn
        ChessBoard chessBoard = new ChessBoard();
        Pawn pawn = new Pawn(chessBoard, ChessPiece.Color.WHITE);
        assertTrue(chessBoard.placePiece(pawn, "c5"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.BLACK), "d6"));
        ArrayList<String> actuals = pawn.legalMoves();
        assertNotNull(actuals);

        ArrayList<String> expecteds = new ArrayList<>(Arrays.asList("c6", "d6"));
        assertEquals(expecteds.size(), actuals.size());
        for (String expected: expecteds) {
            assertTrue(actuals.contains(expected));
        }

        // Test black pawn
        chessBoard = new ChessBoard();
        pawn = new Pawn(chessBoard, ChessPiece.Color.BLACK);
        assertTrue(chessBoard.placePiece(pawn, "c5"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.WHITE), "d4"));
        actuals = pawn.legalMoves();
        assertNotNull(actuals);

        expecteds = new ArrayList<>(Arrays.asList("c4", "d4"));
        assertEquals(expecteds.size(), actuals.size());
        for (String expected: expecteds) {
            assertTrue(actuals.contains(expected));
        }
    }

    @Test
    public void testLegalMovesCaptureBothPiece() {
        // Test white pawn
        ChessBoard chessBoard = new ChessBoard();
        Pawn pawn = new Pawn(chessBoard, ChessPiece.Color.WHITE);
        assertTrue(chessBoard.placePiece(pawn, "c5"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.BLACK), "d6"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.BLACK), "b6"));
        ArrayList<String> actuals = pawn.legalMoves();
        assertNotNull(actuals);

        ArrayList<String> expecteds = new ArrayList<>(Arrays.asList("c6", "d6", "b6"));
        assertEquals(expecteds.size(), actuals.size());
        for (String expected: expecteds) {
            assertTrue(actuals.contains(expected));
        }

        // Test black pawn
        chessBoard = new ChessBoard();
        pawn = new Pawn(chessBoard, ChessPiece.Color.BLACK);
        assertTrue(chessBoard.placePiece(pawn, "c5"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.WHITE), "d4"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.WHITE), "b4"));
        actuals = pawn.legalMoves();
        assertNotNull(actuals);

        expecteds = new ArrayList<>(Arrays.asList("c4", "d4", "b4"));
        assertEquals(expecteds.size(), actuals.size());
        for (String expected: expecteds) {
            assertTrue(actuals.contains(expected));
        }
    }

    @Test
    public void testLegalMovesCaptureRightPieceCheckColor() {
        // Test white pawn
        ChessBoard chessBoard = new ChessBoard();
        Pawn pawn = new Pawn(chessBoard, ChessPiece.Color.WHITE);
        assertTrue(chessBoard.placePiece(pawn, "c5"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.BLACK), "d6"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.WHITE), "b6"));
        ArrayList<String> actuals = pawn.legalMoves();
        assertNotNull(actuals);

        ArrayList<String> expecteds = new ArrayList<>(Arrays.asList("c6", "d6"));
        assertEquals(expecteds.size(), actuals.size());
        for (String expected: expecteds) {
            assertTrue(actuals.contains(expected));
        }

        // Test black pawn
        chessBoard = new ChessBoard();
        pawn = new Pawn(chessBoard, ChessPiece.Color.BLACK);
        assertTrue(chessBoard.placePiece(pawn, "c5"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.WHITE), "d4"));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.BLACK), "b4"));
        actuals = pawn.legalMoves();
        assertNotNull(actuals);

        expecteds = new ArrayList<>(Arrays.asList("c4", "d4"));
        assertEquals(expecteds.size(), actuals.size());
        for (String expected: expecteds) {
            assertTrue(actuals.contains(expected));
        }
    }

    @Test
    public void testLegalMovesDefaultPositions() {
        // Test white pawn
        ChessBoard chessBoard = new ChessBoard();
        Pawn pawn = new Pawn(chessBoard, ChessPiece.Color.WHITE);
        assertTrue(chessBoard.placePiece(pawn, "c2"));
        ArrayList<String> actuals = pawn.legalMoves();
        assertNotNull(actuals);

        ArrayList<String> expecteds = new ArrayList<>(Arrays.asList("c3", "c4"));
        assertEquals(expecteds.size(), actuals.size());
        for (String expected: expecteds) {
            assertTrue(actuals.contains(expected));
        }

        chessBoard = new ChessBoard();
        pawn = new Pawn(chessBoard, ChessPiece.Color.BLACK);
        assertTrue(chessBoard.placePiece(pawn, "c7"));
        actuals = pawn.legalMoves();
        assertNotNull(actuals);
        expecteds = new ArrayList<>(Arrays.asList("c6", "c5"));
        assertEquals(expecteds.size(), actuals.size());
        for (String expected: expecteds) {
            assertTrue(actuals.contains(expected));
        }
    }

    @Test
    public void testCorners() {
        String[] cornerPositions = new String[]{"a8", "a1", "h8", "h1"};
        List<ArrayList<String>> expectedsBlack = new ArrayList<>();
        expectedsBlack.add(new ArrayList<>(List.of("a7")));
        expectedsBlack.add(new ArrayList<>());
        expectedsBlack.add(new ArrayList<>(List.of("h7")));
        expectedsBlack.add(new ArrayList<>());

        List<ArrayList<String>> expectedsWhite = new ArrayList<>();
        expectedsWhite.add(new ArrayList<>());
        expectedsWhite.add(new ArrayList<>(List.of("a2")));
        expectedsWhite.add(new ArrayList<>());
        expectedsWhite.add(new ArrayList<>(List.of("h2")));

        // Test white pawns
        for (int i = 0; i < expectedsWhite.size(); i++) {
            String position = cornerPositions[i];
            ArrayList<String> expecteds = expectedsWhite.get(i);

            ChessBoard chessBoard = new ChessBoard();
            Pawn pawn = new Pawn(chessBoard, ChessPiece.Color.WHITE);
            assertTrue(chessBoard.placePiece(pawn, position));

            ArrayList<String> actuals = pawn.legalMoves();
            assertNotNull(actuals);
            assertEquals(expecteds.size(), actuals.size());
            for (String expected: expecteds) {
                assertTrue(actuals.contains(expected));
            }
        }

        // Test black pawns
        for (int i = 0; i < expectedsBlack.size(); i++) {
            String position = cornerPositions[i];
            ArrayList<String> expecteds = expectedsBlack.get(i);

            ChessBoard chessBoard = new ChessBoard();
            Pawn pawn = new Pawn(chessBoard, ChessPiece.Color.BLACK);
            assertTrue(chessBoard.placePiece(pawn, position));

            ArrayList<String> actuals = pawn.legalMoves();
            assertNotNull(actuals);
            assertEquals(expecteds.size(), actuals.size());
            for (String expected: expecteds) {
                assertTrue(actuals.contains(expected));
            }
        }
    }

    @Test
    public void testStartingBlockingPieceOneOut() {
        String startingPlaceBlack = "c7";
        String startingPlaceWhite = "c2";

        // Test white pawn
        ChessBoard chessBoard = new ChessBoard();
        Pawn pawn = new Pawn(chessBoard, ChessPiece.Color.WHITE);
        assertTrue(chessBoard.placePiece(pawn, startingPlaceWhite));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.WHITE), "c3"));
        assertTrue(pawn.legalMoves().isEmpty());

        // Test black pawn
        chessBoard = new ChessBoard();
        pawn = new Pawn(chessBoard, ChessPiece.Color.BLACK);
        assertTrue(chessBoard.placePiece(pawn, startingPlaceBlack));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.BLACK), "c6"));
        assertTrue(pawn.legalMoves().isEmpty());
    }

    @Test
    public void testStartingBlockingPieceTwoOut() {
        String startingPlaceBlack = "c7";
        String startingPlaceWhite = "c2";

        // Test white pawn
        ChessBoard chessBoard = new ChessBoard();
        Pawn pawn = new Pawn(chessBoard, ChessPiece.Color.WHITE);
        assertTrue(chessBoard.placePiece(pawn, startingPlaceWhite));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.WHITE), "c4"));
        ArrayList<String> expecteds = new ArrayList<>(List.of("c3"));
        ArrayList<String> actuals = pawn.legalMoves();
        assertEquals(expecteds.size(), actuals.size());
        for (String expected: expecteds) {
            assertTrue(actuals.contains(expected));
        }

        // Test black pawn
        chessBoard = new ChessBoard();
        pawn = new Pawn(chessBoard, ChessPiece.Color.BLACK);
        assertTrue(chessBoard.placePiece(pawn, startingPlaceBlack));
        assertTrue(chessBoard.placePiece(new Pawn(chessBoard, ChessPiece.Color.BLACK), "c5"));
        expecteds = new ArrayList<>(List.of("c6"));
        actuals = pawn.legalMoves();
        assertEquals(expecteds.size(), actuals.size());
        for (String expected: expecteds) {
            assertTrue(actuals.contains(expected));
        }
    }
}
