package ChessGame.Rules;
import ChessGame.ChessBoard;
import ChessGame.ChessPiece;

/**
 * MoveHorizontalRule,
 * Permits a piece to move on the X axis and only the X axis.
 *
 * @see MoveRule
 * @author Owan Lazic
 */

public class MoveHorizontalRule implements MoveRule
{
    private final Integer maxNumberOfSquares;
    public MoveHorizontalRule(Integer maxNumberOfSquares) {this.maxNumberOfSquares = maxNumberOfSquares;}
    @Override
    public Boolean isValidMove(ChessBoard board, ChessPiece piece, byte toX, byte toY)
    {
       return toY == piece.y() && Math.abs(toX - piece.x()) <= maxNumberOfSquares;
    }
}
