import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ChessBoardTest {

    private static String[][] positions = new String[][] {
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

    @Test(expected=IllegalPositionException.class)
    public void testGetPieceIllegalChar() throws IllegalPositionException {
        ChessBoard cb = new ChessBoard();
        String position = "&9";
        cb.getPiece(position);
    }

    @Test(expected=IllegalPositionException.class)
    public void testGetPieceEmptyString() throws IllegalPositionException {
        ChessBoard cb = new ChessBoard();
        String position = "";
        cb.getPiece(position);
    }

    @Test(expected=IllegalPositionException.class)
    public void testGetPieceOutOfBounds() throws IllegalPositionException {
        ChessBoard cb = new ChessBoard();
        String position = "s9";
        cb.getPiece(position);
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
}
