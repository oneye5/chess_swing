package ChessGame.Rules;

import ChessGame.ChessBoard;
import ChessGame.ChessPiece;

public class MoveVerticalRule implements MoveRule
{
    Integer maxNumberOfSquares; // -1 for infinite
    public MoveVerticalRule(Integer maxNumberOfSquares) {this.maxNumberOfSquares = maxNumberOfSquares;}
    @Override
    public Boolean isValidMove(ChessBoard board, ChessPiece piece, int toX, int toY)
    {
       return toX == piece.x() && Math.abs(toY - piece.y()) <= maxNumberOfSquares;
    }
}
