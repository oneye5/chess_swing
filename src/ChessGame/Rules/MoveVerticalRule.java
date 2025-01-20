package ChessGame.Rules;
import ChessGame.ChessBoard;
import ChessGame.ChessPiece;

/**
 * MoveVerticalRule,
 * permits a piece to move on the y-axis.
 *
 * @see MoveRule
 * @author Owan Lazic
 */

public class MoveVerticalRule implements MoveRule
{
    Integer maxNumberOfSquares;
    public MoveVerticalRule(Integer maxNumberOfSquares) {this.maxNumberOfSquares = maxNumberOfSquares;}
    @Override
    public Boolean isValidMove(ChessBoard board, ChessPiece piece, byte toX, byte toY)
    {
       return toX == piece.x() && Math.abs(toY - piece.y()) <= maxNumberOfSquares;
    }
}
