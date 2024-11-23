package ChessGame.Rules;

import ChessGame.ChessBoard;
import ChessGame.ChessPiece;

import java.util.List;

public class OrRule implements MoveRule
{
    List<MoveRule> rules;
    @Override
    public Boolean isValidMove(ChessBoard board, ChessPiece piece, int toX, int toY)
    {
        return rules.stream().anyMatch(rule -> rule.isValidMove(board, piece, toX, toY));
    }

    public OrRule(MoveRule... rules)
    {
        this.rules = List.of(rules);
    }
}
