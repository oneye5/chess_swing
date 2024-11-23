package ChessGame.Rules;

import ChessGame.ChessBoard;
import ChessGame.ChessPiece;

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
