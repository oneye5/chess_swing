package ChessGame.Rules;
import ChessGame.ChessBoard;
import ChessGame.ChessPiece;

/**
 * FirstMoveRule,
 * This is a composite rule, it takes another rule as an argument and if the piece has not already moved,
 * it will evaluate and return the result of the composite rule.
 *
 * @see MoveRule
 * @author Owan Lazic
 */

public class FirstMoveRule implements MoveRule
{
    MoveRule rule;
    public FirstMoveRule(MoveRule rule)
    {
        this.rule = rule;
    }
    @Override
    public Boolean isValidMove(ChessBoard board, ChessPiece piece, int toX, int toY)
    {
        return !piece.hasMoved() && rule.isValidMove(board, piece, toX, toY);
    }
}
