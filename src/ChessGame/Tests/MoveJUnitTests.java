package ChessGame.Tests;

import ChessGame.ChessBoard;
import ChessGame.ChessPiece;
import ChessGame.PieceType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 *  Tests written by Claude (AI)
 *  Tests have been double-checked by Intellij's AI as well as chatgpt, as well as a manual review
 */

class PawnTests {
    @Test
    void pawnFirstMoveTwoSquares() {
        ChessPiece pawn = new ChessPiece(PieceType.PAWN, 0, 1, true, false);
        ChessBoard board = ChessBoard.newBoardWithPieces(pawn);

        List<Integer[]> moves = pawn.getPossibleMoves(board);

        Assertions.assertTrue(DebugUtility.possibleMovesContainPosition(moves, 0, 2));
        Assertions.assertTrue(DebugUtility.possibleMovesContainPosition(moves, 0, 3));
    }

    @Test
    void pawnBlockedByPiece() {
        ChessPiece pawn = new ChessPiece(PieceType.PAWN, 0, 1, true, false);
        ChessPiece blockingPiece = new ChessPiece(PieceType.PAWN, 0, 2, false, false);
        ChessBoard board = ChessBoard.newBoardWithPieces(pawn, blockingPiece);

        List<Integer[]> moves = pawn.getPossibleMoves(board);

        Assertions.assertFalse(DebugUtility.possibleMovesContainPosition(moves, 0, 2));
        Assertions.assertFalse(DebugUtility.possibleMovesContainPosition(moves, 0, 3));
    }

    @Test
    void pawnDiagonalCapture() {
        ChessPiece pawn = new ChessPiece(PieceType.PAWN, 3, 3, true, false);
        ChessPiece enemyPiece1 = new ChessPiece(PieceType.PAWN, 2, 4, false, false);
        ChessPiece enemyPiece2 = new ChessPiece(PieceType.PAWN, 4, 4, false, false);
        ChessBoard board = ChessBoard.newBoardWithPieces(pawn, enemyPiece1, enemyPiece2);

        List<Integer[]> moves = pawn.getPossibleMoves(board);

        Assertions.assertTrue(DebugUtility.possibleMovesContainPosition(moves, 2, 4));
        Assertions.assertTrue(DebugUtility.possibleMovesContainPosition(moves, 4, 4));
    }

    @Test
    void pawnCannotMoveBackward() {
        ChessPiece pawn = new ChessPiece(PieceType.PAWN, 3, 3, true, true);
        ChessBoard board = ChessBoard.newBoardWithPieces(pawn);

        List<Integer[]> moves = pawn.getPossibleMoves(board);

        Assertions.assertFalse(DebugUtility.possibleMovesContainPosition(moves, 3, 2));
    }
}

class KnightTests {
    @Test
    void knightLShapedMovement() {
        ChessPiece knight = new ChessPiece(PieceType.KNIGHT, 3, 3, true, false);
        ChessBoard board = ChessBoard.newBoardWithPieces(knight);

        List<Integer[]> moves = knight.getPossibleMoves(board);

        // All possible L-shaped moves
        Assertions.assertTrue(DebugUtility.possibleMovesContainPosition(moves, 1, 4));
        Assertions.assertTrue(DebugUtility.possibleMovesContainPosition(moves, 1, 2));
        Assertions.assertTrue(DebugUtility.possibleMovesContainPosition(moves, 5, 4));
        Assertions.assertTrue(DebugUtility.possibleMovesContainPosition(moves, 5, 2));
        Assertions.assertTrue(DebugUtility.possibleMovesContainPosition(moves, 2, 5));
        Assertions.assertTrue(DebugUtility.possibleMovesContainPosition(moves, 4, 5));
        Assertions.assertTrue(DebugUtility.possibleMovesContainPosition(moves, 2, 1));
        Assertions.assertTrue(DebugUtility.possibleMovesContainPosition(moves, 4, 1));
    }

    @Test
    void knightCanJumpOverPieces() {
        ChessPiece knight = new ChessPiece(PieceType.KNIGHT, 3, 3, true, false);
        ChessPiece blockingPiece = new ChessPiece(PieceType.PAWN, 3, 4, true, false);
        ChessBoard board = ChessBoard.newBoardWithPieces(knight, blockingPiece);

        List<Integer[]> moves = knight.getPossibleMoves(board);

        Assertions.assertTrue(DebugUtility.possibleMovesContainPosition(moves, 2, 5));
        Assertions.assertTrue(DebugUtility.possibleMovesContainPosition(moves, 4, 5));
    }
}

class BishopTests {
    @Test
    void bishopDiagonalMovement() {
        ChessPiece bishop = new ChessPiece(PieceType.BISHOP, 3, 3, true, false);
        ChessBoard board = ChessBoard.newBoardWithPieces(bishop);

        List<Integer[]> moves = bishop.getPossibleMoves(board);

        // Test diagonal moves in all directions
        for (int i = 0; i < 8; i++) {
            if (i != 3) {
                Assertions.assertTrue(DebugUtility.possibleMovesContainPosition(moves, i, i));
                Assertions.assertTrue(DebugUtility.possibleMovesContainPosition(moves, i, 6-i));
            }
        }
    }

    @Test
    void bishopBlockedByPiece() {
        ChessPiece bishop = new ChessPiece(PieceType.BISHOP, 3, 3, true, false);
        ChessPiece blockingPiece = new ChessPiece(PieceType.PAWN, 5, 5, true, false);
        ChessBoard board = ChessBoard.newBoardWithPieces(bishop, blockingPiece);

        List<Integer[]> moves = bishop.getPossibleMoves(board);

        Assertions.assertFalse(DebugUtility.possibleMovesContainPosition(moves, 5, 5));
        Assertions.assertFalse(DebugUtility.possibleMovesContainPosition(moves, 6, 6));
    }
}

class RookTests {
    @Test
    void rookStraightLineMovement() {
        ChessPiece rook = new ChessPiece(PieceType.ROOK, 3, 3, true, false);
        ChessBoard board = ChessBoard.newBoardWithPieces(rook);

        List<Integer[]> moves = rook.getPossibleMoves(board);

        // Test horizontal and vertical moves
        for (int i = 0; i < 8; i++) {
            if (i != 3) {
                Assertions.assertTrue(DebugUtility.possibleMovesContainPosition(moves, i, 3));
                Assertions.assertTrue(DebugUtility.possibleMovesContainPosition(moves, 3, i));
            }
        }
    }

    @Test
    void rookBlockedByPiece() {
        ChessPiece rook = new ChessPiece(PieceType.ROOK, 3, 3, true, false);
        ChessPiece blockingPiece = new ChessPiece(PieceType.PAWN, 3, 5, true, false);
        ChessBoard board = ChessBoard.newBoardWithPieces(rook, blockingPiece);

        List<Integer[]> moves = rook.getPossibleMoves(board);

        Assertions.assertFalse(DebugUtility.possibleMovesContainPosition(moves, 3, 5));
        Assertions.assertFalse(DebugUtility.possibleMovesContainPosition(moves, 3, 6));
        Assertions.assertFalse(DebugUtility.possibleMovesContainPosition(moves, 3, 7));
    }
}

class QueenTests {
    @Test
    void queenAllDirectionsMovement() {
        ChessPiece queen = new ChessPiece(PieceType.QUEEN, 3, 3, true, false);
        ChessBoard board = ChessBoard.newBoardWithPieces(queen);

        List<Integer[]> moves = queen.getPossibleMoves(board);

        // Test horizontal and vertical moves
        for (int i = 0; i < 8; i++) {
            if (i != 3) {
                Assertions.assertTrue(DebugUtility.possibleMovesContainPosition(moves, i, 3));
                Assertions.assertTrue(DebugUtility.possibleMovesContainPosition(moves, 3, i));
            }
        }

        // Test diagonal moves
        for (int i = 0; i < 8; i++) {
            if (i != 3) {
                Assertions.assertTrue(DebugUtility.possibleMovesContainPosition(moves, i, i));
                Assertions.assertTrue(DebugUtility.possibleMovesContainPosition(moves, i, 6-i));
            }
        }
    }

    @Test
    void queenBlockedByPiece() {
        ChessPiece queen = new ChessPiece(PieceType.QUEEN, 3, 3, true, false);
        ChessPiece blockingPiece = new ChessPiece(PieceType.PAWN, 3, 5, true, false);
        ChessBoard board = ChessBoard.newBoardWithPieces(queen, blockingPiece);

        List<Integer[]> moves = queen.getPossibleMoves(board);

        Assertions.assertFalse(DebugUtility.possibleMovesContainPosition(moves, 3, 5));
        Assertions.assertFalse(DebugUtility.possibleMovesContainPosition(moves, 3, 6));
        Assertions.assertFalse(DebugUtility.possibleMovesContainPosition(moves, 3, 7));
    }
}

class KingTests {
    @Test
    void kingOneSquareMovement() {
        ChessPiece king = new ChessPiece(PieceType.KING, 3, 3, true, false);
        ChessBoard board = ChessBoard.newBoardWithPieces(king);

        List<Integer[]> moves = king.getPossibleMoves(board);

        // Test all adjacent squares
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx != 0 || dy != 0) {
                    Assertions.assertTrue(DebugUtility.possibleMovesContainPosition(moves, 3 + dx, 3 + dy));
                }
            }
        }
    }

    @Test
    void kingCastling() {
        ChessPiece king = new ChessPiece(PieceType.KING, 4, 0, true, false);
        ChessPiece rook = new ChessPiece(PieceType.ROOK, 7, 0, true, false);
        ChessBoard board = ChessBoard.newBoardWithPieces(king, rook);

        List<Integer[]> moves = king.getPossibleMoves(board);

        // Test kingside castling
        Assertions.assertTrue(DebugUtility.possibleMovesContainPosition(moves, 6, 0));
    }

    @Test
    void kingCannotMoveIntoCheck() {
        ChessPiece king = new ChessPiece(PieceType.KING, 4, 0, true, false);
        ChessPiece enemyRook = new ChessPiece(PieceType.ROOK, 4, 7, false, false);
        ChessBoard board = ChessBoard.newBoardWithPieces(king, enemyRook);

        List<Integer[]> moves = king.getPossibleMoves(board);

        // King cannot move along the file controlled by the enemy rook
        Assertions.assertFalse(DebugUtility.possibleMovesContainPosition(moves, 4, 1));
    }
}