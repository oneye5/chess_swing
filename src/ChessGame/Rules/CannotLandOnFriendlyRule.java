package ChessGame.Rules;

import ChessGame.ChessBoard;
import ChessGame.ChessPiece;

public class CannotLandOnFriendlyRule implements MoveRule
{
    @Override
    public Boolean isDisjunctiveRule()
    {
        return false; // conjunctive rule
    }

    @Override
    public Boolean isValidMove(ChessBoard board, ChessPiece piece, int toX, int toY)
    {
        return board.board()[toX][toY] == null;
    }
}
