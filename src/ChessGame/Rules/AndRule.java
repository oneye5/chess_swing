package ChessGame.Rules;

import ChessGame.ChessBoard;
import ChessGame.ChessPiece;

import java.util.List;

public class AndRule implements MoveRule
{
    public List<MoveRule> rules;
    @Override
    public Boolean isValidMove(ChessBoard board, ChessPiece piece, int toX, int toY)
    {
        for (MoveRule rule : rules)
            if (!rule.isValidMove(board, piece, toX, toY))
                return false;

        return true;
    }

    public AndRule(MoveRule... rules)
    {
        this.rules = List.of(rules);
    }
}
