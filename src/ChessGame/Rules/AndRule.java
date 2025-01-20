package ChessGame.Rules;
import ChessGame.ChessBoard;
import ChessGame.ChessPiece;
import java.util.List;

/**
 * AndRule,
 * Contains any number of other 'MoveRule' objects, isValidMove returns true if all the MoveRule's return true.
 *
 * @see MoveRule
 * @author Owan Lazic
 */

public class AndRule implements MoveRule
{
    public List<MoveRule> rules;

    public AndRule(MoveRule... rules) {this.rules = List.of(rules);}

    @Override
    public Boolean isValidMove(ChessBoard board, ChessPiece piece, byte toX, byte toY)
    {
        for (MoveRule rule : rules)
            if (!rule.isValidMove(board, piece, toX, toY))
                return false;

        return true;
    }
}
