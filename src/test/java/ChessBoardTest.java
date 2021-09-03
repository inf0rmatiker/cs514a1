import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChessBoardTest {

    private static final String[][] positions = new String[][] {
            {"a8", "b8", "c8", "d8", "e8", "f8", "g8", "h8"},
            {"a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7"},
            {"a6", "b6", "c6", "d6", "e6", "f6", "g6", "h6"},
            {"a5", "b5", "c5", "d5", "e5", "f5", "g5", "h5"},
            {"a4", "b4", "c4", "d4", "e4", "f4", "g4", "h4"},
            {"a3", "b3", "c3", "d3", "e3", "f3", "g3", "h3"},
            {"a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2"},
            {"a1", "b1", "c1", "d1", "e1", "f1", "g1", "h1"}
    };

    @Test
    public void testPlacePieceInbounds() {
        ChessBoard cb = new ChessBoard();
        cb.placePiece(new Pawn(cb, ChessPiece.Color.WHITE), "a2");
        String expected = "\u2659";
        try {
            String actual = cb.getPiece("a2").toString();
            assertEquals(expected, actual);
        } catch (IllegalPositionException e) {
            fail("Caught IllegalPositionException");
        }
    }

    @Test
    public void testPlaceUppercaseChar() {
        ChessBoard cb = new ChessBoard();
        assertFalse(cb.placePiece(new Pawn(cb, ChessPiece.Color.WHITE), "A6"));
    }

    @Test
    public void testPlacePieceOutOfBounds() {
        ChessBoard cb = new ChessBoard();
        assertFalse(cb.placePiece(new Pawn(cb, ChessPiece.Color.WHITE), "s9"));
    }

    @Test
    public void testPlacePieceIllegalChar() {
        ChessBoard cb = new ChessBoard();
        assertFalse(cb.placePiece(new Pawn(cb, ChessPiece.Color.WHITE), "&9"));
    }

    @Test
    public void testPlacePieceEmptyString() {
        ChessBoard cb = new ChessBoard();
        assertFalse(cb.placePiece(new Pawn(cb, ChessPiece.Color.WHITE), ""));
    }

    @Test
    public void testGetPieceIllegalChar() {
        assertThrows(IllegalPositionException.class, () -> new ChessBoard().getPiece("&9"));
    }

    @Test
    public void testGetPieceUppercaseChar() {
        assertThrows(IllegalPositionException.class, () -> new ChessBoard().getPiece("A6"));
    }

    @Test
    public void testGetPieceEmptyString() {
        assertThrows(IllegalPositionException.class, () -> new ChessBoard().getPiece(""));
    }

    @Test
    public void testGetPieceOutOfBounds() {
        assertThrows(IllegalPositionException.class, () -> new ChessBoard().getPiece("s9"));
    }

    @Test
    public void testGetPieceBeforeInitInbounds() {
        ChessBoard cb = new ChessBoard();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String position = positions[i][j];
                try {
                    assertNull(cb.getPiece(position));
                } catch (IllegalPositionException e) {
                    fail("Caught IllegalPositionException");
                }
            }
        }
    }

    @Test
    public void testInitialize() {
        String[][] initialPieces = new String[][] {
                {"\u265C", "\u265E", "\u265D", "\u265B", "\u265A", "\u265D", "\u265E", "\u265C"},
                {"\u265F", "\u265F", "\u265F", "\u265F", "\u265F", "\u265F", "\u265F", "\u265F"},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {"\u2659", "\u2659", "\u2659", "\u2659", "\u2659", "\u2659", "\u2659", "\u2659"},
                {"\u2656", "\u2658", "\u2657", "\u2655", "\u2654", "\u2657", "\u2658", "\u2656"}
        };
        ChessBoard cb = new ChessBoard();
        cb.initialize();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String position = positions[i][j];
                String expected = initialPieces[i][j];
                try {
                    ChessPiece actual = cb.getPiece(position);
                    if (expected == null) {
                        assertNull(actual);
                    } else {
                        assertEquals(actual.toString(), expected);
                    }
                } catch (IllegalPositionException e) {
                    fail("Caught IllegalPositionException");
                }
            }
        }
    }

    @Test
    public void testMoveOutOfBounds() {
        ChessBoard cb = new ChessBoard();
        cb.initialize();
        assertThrows(IllegalMoveException.class, () -> cb.move("e8", "e9"));
    }

    @Test
    public void testMoveInvalidPosition() {
        ChessBoard cb = new ChessBoard();
        cb.initialize();
        assertThrows(IllegalMoveException.class, () -> cb.move("e8", ""));
    }

    @Test
    public void testMoveBlockingPiece() {
        ChessBoard cb = new ChessBoard();
        cb.initialize();
        assertThrows(IllegalMoveException.class, () -> cb.move("e8", "e7"));
    }

    @Test
    public void testMovePawnValidOneSpaceForward() {
        ChessBoard cb = new ChessBoard();
        cb.initialize();
        assertDoesNotThrow(() -> cb.move("e7", "e6"));
        try {
            assertNotNull(cb.getPiece("e6"));
        } catch (IllegalPositionException e) {
            fail("Caught IllegalPositionException!");
        }
    }

    @Test
    public void testMovePawnValidTwoSpacesForward() {
        ChessBoard cb = new ChessBoard();
        cb.initialize();
        assertDoesNotThrow(() -> cb.move("e7", "e5"));
        try {
            assertNotNull(cb.getPiece("e5"));
        } catch (IllegalPositionException e) {
            fail("Caught IllegalPositionException!");
        }
    }

    @Test
    public void testMovePawnInvalidThreeSpacesForward() {
        ChessBoard cb = new ChessBoard();
        cb.initialize();
        assertThrows(IllegalMoveException.class, () -> cb.move("e7", "e4"));
        try {
            assertNull(cb.getPiece("e4"));
        } catch (IllegalPositionException e) {
            fail("Caught IllegalPositionException!");
        }
    }
}
