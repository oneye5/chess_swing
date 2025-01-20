package ChessGame.Rules;
import ChessGame.ChessBoard;
import ChessGame.ChessPiece;

/**
 * LineOfSightRule,
 * All chess pieces except for the Knight respect 'line of sight'.
 * Such that a piece may not move through another to land on the opposite side of that piece.
 *
 * @see MoveRule
 * @author Owan Lazic
 */

public class LineOfSightRule implements MoveRule {
    @Override
    public Boolean isValidMove(ChessBoard board, ChessPiece piece, byte toX, byte toY) {
        int deltaX = toX - piece.x();
        int deltaY = toY - piece.y();

        // Skip if there's no movement
        if (deltaX == 0 && deltaY == 0)
            return false;

        // Calculate the direction of movement
        int xSign = Integer.compare(deltaX, 0);
        int ySign = Integer.compare(deltaY, 0);

        // Calculate the number of steps to check
        int steps = Math.max(Math.abs(deltaX), Math.abs(deltaY));

        // Check if movement is diagonal, horizontal, or vertical
        if (Math.abs(deltaX) != Math.abs(deltaY) && deltaX != 0 && deltaY != 0)
            return false;

        // Check each square along the path
        for (int i = 1; i < steps; i++)
        {
            int x = piece.x() + (xSign * i);
            int y = piece.y() + (ySign * i);

            if (board.board()[x][y] != null)
                return false;
        }

        return true;
    }
}