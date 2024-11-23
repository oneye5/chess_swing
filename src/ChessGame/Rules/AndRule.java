package ChessGame.Rules;

import ChessGame.ChessBoard;
import ChessGame.ChessPiece;

import java.util.List;

public class AndRule implements MoveRule
{
    List<MoveRule> rules;
    @Override
    public Boolean isValidMove(ChessBoard board, ChessPiece piece, int toX, int toY)
    {
        return rules.stream().allMatch(rule -> rule.isValidMove(board, piece, toX, toY));
    }

    public AndRule(MoveRule... rules)
    {
        this.rules = List.of(rules);
    }
}
