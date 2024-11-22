package ChessGame.Rules;

import ChessGame.ChessBoard;
import ChessGame.ChessPiece;

public class MoveHorizontalRule implements MoveRule
{
    Integer maxNumberOfSquares; // -1 for infinite
    public MoveHorizontalRule(Integer maxNumberOfSquares) {this.maxNumberOfSquares = maxNumberOfSquares;}
    @Override
    public Boolean isDisjunctiveRule()
    {
        return true; //conjunctive rule
    }

    @Override
    public Boolean isValidMove(ChessBoard board, ChessPiece piece, int toX, int toY)
    {
       return toY == piece.getY() && Math.abs(toX - piece.getX()) <= maxNumberOfSquares;
    }
}
