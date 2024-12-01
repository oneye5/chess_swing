package ChessGame.Rules;
import ChessGame.ChessBoard;
import ChessGame.ChessPiece;

/**
 * MoveDiagonalRule,
 * Permits a piece to move diagonally.
 * Returns true if the proposed move is diagonal to the current piece position.
 *
 * @see MoveRule
 * @author Owan Lazic
 */

public class MoveDiagonalRule implements MoveRule
{
    private final Integer numSquares;
    public MoveDiagonalRule(Integer numSquares) {this.numSquares = numSquares;}
    @Override
    public Boolean isValidMove(ChessBoard board, ChessPiece piece, int toX, int toY)
    {
        return Math.abs(piece.x() - toX) == Math.abs(piece.y() - toY)
                && Math.abs(piece.x() - toX) <= numSquares;
    }
}
