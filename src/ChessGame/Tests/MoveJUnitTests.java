package ChessGame.Tests;

import ChessGame.ChessBoard;
import ChessGame.ChessPiece;
import ChessGame.PieceType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ChessEngine.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 *  Tests written by Claude (AI)
 *  Tests have been double-checked by Intellij's AI as well as chatgpt, as well as a manual review
 */


class MethodTests
{

    @Test void search()
    {
        ChessPiece whiteKing = new ChessPiece(PieceType.KING, 0, 0, true, false);
        ChessPiece blackKing = new ChessPiece(PieceType.KING, 7, 7, false, false);
        ChessPiece blackPawn = new ChessPiece(PieceType.PAWN, 4, 4, true, false);
        ChessPiece whitePawn = new ChessPiece(PieceType.PAWN, 3, 3, false, false);
        ChessBoard board = ChessBoard.newBoardWithPieces(blackPawn, whitePawn, whiteKing, blackKing);
        new ChessEngine().findBestMove(board);
    }
    @Test
    void getAvailableMoves()
    {
        ChessPiece whiteKing = new ChessPiece(PieceType.KING, 0, 0, true, false);
        ChessPiece blackKing = new ChessPiece(PieceType.KING, 7, 7, false, false);
        ChessBoard board = ChessBoard.newBoardWithPieces(whiteKing, blackKing);

        List<Integer[]> moves = board.getAllMoves();
        List<Integer[]> expectedMoves = List.of(new Integer[]{0,0,1,0},new Integer[]{0,0,1,1},new Integer[]{0,0,0,1});
        for(var move : expectedMoves)
        {
            assert expectedMoves.contains(move);
        }
        assert moves.size() == expectedMoves.size();
    }
    @Test void treeNode()
    {
        ChessPiece whiteBishop = new ChessPiece(PieceType.BISHOP, 3, 3, true, false);
        ChessPiece whiteKing = new ChessPiece(PieceType.KING, 4, 0, true, false);
        ChessPiece blackKing = new ChessPiece(PieceType.KING, 4, 7, false, false);
        ChessBoard board = ChessBoard.newBoardWithPieces(whiteBishop, whiteKing, blackKing);

        var root = TreeNode.root(board);
        var children = root.getChildren();
        var child = children.get(0);
        assert child.getDepth() == 1;
        assert child.getChildren().get(0).getDepth() == 2;
    }


    @Test void getPrecedingMove()
    {
        ChessPiece bishop = new ChessPiece(PieceType.BISHOP, 3, 3, true, false);
        ChessPiece blockingPiece = new ChessPiece(PieceType.PAWN, 5, 5, true, false);
        ChessPiece whiteKing = new ChessPiece(PieceType.KING, 4, 0, true, false);
        ChessPiece blackKing = new ChessPiece(PieceType.KING, 4, 7, false, false);
        ChessBoard board = ChessBoard.newBoardWithPieces(bishop, blockingPiece, whiteKing, blackKing);

        var nb = board.newBoardWithMove(3,3,4,4);
        var move = nb.getPrecedingMove();

        assert move != null;
        assert move[0] == 3;
        assert move[1] == 3;
        assert move[2] == 4;
        assert move[3] == 4;
    }
}

class PawnTests {
    @Test
    void pawnFirstMoveTwoSquares() {
        ChessPiece pawn = new ChessPiece(PieceType.PAWN, 0, 1, true, false);
        ChessPiece whiteKing = new ChessPiece(PieceType.KING, 4, 0, true, false);
        ChessPiece blackKing = new ChessPiece(PieceType.KING, 4, 7, false, false);
        ChessBoard board = ChessBoard.newBoardWithPieces(pawn, whiteKing, blackKing);

        List<Integer[]> moves = pawn.getPossibleMoves(board);

        Assertions.assertTrue(DebugUtility.possibleMovesContainPosition(moves, 0, 2));
        Assertions.assertTrue(DebugUtility.possibleMovesContainPosition(moves, 0, 3));
    }

    @Test
    void pawnBlockedByPiece() {
        ChessPiece pawn = new ChessPiece(PieceType.PAWN, 0, 1, true, false);
        ChessPiece blockingPiece = new ChessPiece(PieceType.PAWN, 0, 2, false, false);
        ChessPiece whiteKing = new ChessPiece(PieceType.KING, 4, 0, true, false);
        ChessPiece blackKing = new ChessPiece(PieceType.KING, 4, 7, false, false);
        ChessBoard board = ChessBoard.newBoardWithPieces(pawn, blockingPiece, whiteKing, blackKing);

        List<Integer[]> moves = pawn.getPossibleMoves(board);

        Assertions.assertFalse(DebugUtility.possibleMovesContainPosition(moves, 0, 2));
        Assertions.assertFalse(DebugUtility.possibleMovesContainPosition(moves, 0, 3));
    }

    @Test
    void pawnDiagonalCapture() {
        ChessPiece pawn = new ChessPiece(PieceType.PAWN, 3, 3, true, false);
        ChessPiece enemyPiece1 = new ChessPiece(PieceType.PAWN, 2, 4, false, false);
        ChessPiece enemyPiece2 = new ChessPiece(PieceType.PAWN, 4, 4, false, false);
        ChessPiece whiteKing = new ChessPiece(PieceType.KING, 4, 0, true, false);
        ChessPiece blackKing = new ChessPiece(PieceType.KING, 4, 7, false, false);
        ChessBoard board = ChessBoard.newBoardWithPieces(pawn, enemyPiece1, enemyPiece2, whiteKing, blackKing);

        List<Integer[]> moves = pawn.getPossibleMoves(board);

        Assertions.assertTrue(DebugUtility.possibleMovesContainPosition(moves, 2, 4));
        Assertions.assertTrue(DebugUtility.possibleMovesContainPosition(moves, 4, 4));
    }

    @Test
    void pawnCannotMoveBackward() {
        ChessPiece pawn = new ChessPiece(PieceType.PAWN, 3, 3, true, true);
        ChessPiece whiteKing = new ChessPiece(PieceType.KING, 4, 0, true, false);
        ChessPiece blackKing = new ChessPiece(PieceType.KING, 4, 7, false, false);
        ChessBoard board = ChessBoard.newBoardWithPieces(pawn, whiteKing, blackKing);

        List<Integer[]> moves = pawn.getPossibleMoves(board);

        Assertions.assertFalse(DebugUtility.possibleMovesContainPosition(moves, 3, 2));
    }
}

class KnightTests {
    @Test
    void knightLShapedMovement() {
        ChessPiece knight = new ChessPiece(PieceType.KNIGHT, 3, 3, true, false);
        ChessPiece whiteKing = new ChessPiece(PieceType.KING, 4, 0, true, false);
        ChessPiece blackKing = new ChessPiece(PieceType.KING, 4, 7, false, false);
        ChessBoard board = ChessBoard.newBoardWithPieces(knight, whiteKing, blackKing);

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
        ChessPiece whiteKing = new ChessPiece(PieceType.KING, 4, 0, true, false);
        ChessPiece blackKing = new ChessPiece(PieceType.KING, 4, 7, false, false);
        ChessBoard board = ChessBoard.newBoardWithPieces(knight, blockingPiece, whiteKing, blackKing);

        List<Integer[]> moves = knight.getPossibleMoves(board);

        Assertions.assertTrue(DebugUtility.possibleMovesContainPosition(moves, 2, 5));
        Assertions.assertTrue(DebugUtility.possibleMovesContainPosition(moves, 4, 5));
    }
}

class BishopTests {
    @Test
    void bishopDiagonalMovement() {
        ChessPiece bishop = new ChessPiece(PieceType.BISHOP, 3, 3, true, false);
        ChessPiece whiteKing = new ChessPiece(PieceType.KING, 4, 0, true, false);
        ChessPiece blackKing = new ChessPiece(PieceType.KING, 4, 7, false, false);
        ChessBoard board = ChessBoard.newBoardWithPieces(bishop, whiteKing, blackKing);

        List<Integer[]> moves = bishop.getPossibleMoves(board);

        // Test diagonal moves in all directions
        for (int i = 0; i < 8; i++)
            if (i != 3)
                Assertions.assertTrue(DebugUtility.possibleMovesContainPosition(moves, i, i));


    }

    @Test
    void bishopBlockedByPiece() {
        ChessPiece bishop = new ChessPiece(PieceType.BISHOP, 3, 3, true, false);
        ChessPiece blockingPiece = new ChessPiece(PieceType.PAWN, 5, 5, true, false);
        ChessPiece whiteKing = new ChessPiece(PieceType.KING, 4, 0, true, false);
        ChessPiece blackKing = new ChessPiece(PieceType.KING, 4, 7, false, false);
        ChessBoard board = ChessBoard.newBoardWithPieces(bishop, blockingPiece, whiteKing, blackKing);

        List<Integer[]> moves = bishop.getPossibleMoves(board);

        Assertions.assertFalse(DebugUtility.possibleMovesContainPosition(moves, 5, 5));
        Assertions.assertFalse(DebugUtility.possibleMovesContainPosition(moves, 6, 6));
    }
}

class RookTests {
    @Test
    void rookStraightLineMovement()
    {
        ChessPiece rook = new ChessPiece(PieceType.ROOK, 3, 3, true, false);
        ChessPiece whiteKing = new ChessPiece(PieceType.KING, 4, 0, true, false);
        ChessPiece blackKing = new ChessPiece(PieceType.KING, 4, 7, false, false);
        ChessBoard board = ChessBoard.newBoardWithPieces(rook, whiteKing, blackKing);

        List<Integer[]> moves = rook.getPossibleMoves(board);

        // Test horizontal and vertical moves
        for (int i = 0; i < 8; i++)
        {
            if (i != 3)
            {
                Assertions.assertTrue(DebugUtility.possibleMovesContainPosition(moves, i, 3));
                Assertions.assertTrue(DebugUtility.possibleMovesContainPosition(moves, 3, i));
            }
        }
    }
}

