package ChessGame.Rules;
import ChessGame.ChessBoard;
import ChessGame.ChessPiece;

/**
 * MoveKnightRule
 * Special rule for only knights, that permits the 'L' movement of knights
 *
 * @see MoveRule
 * @author Owan Lazic
 */

public class MoveKnightRule implements MoveRule
{

    @Override
    public Boolean isValidMove(ChessBoard board, ChessPiece piece, byte toX, byte toY)
    {
        int fromX = piece.x();
        int fromY = piece.y();

        // Calculate the horizontal and vertical move distances
        int dx = Math.abs(fromX - toX);
        int dy = Math.abs(fromY - toY);

        // Check the L-shape move (two squares in one direction and one square in the other direction)
        return (dx == 2 && dy == 1) || (dx == 1 && dy == 2);
    }
}
