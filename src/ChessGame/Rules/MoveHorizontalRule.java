package ChessGame.Rules;

import ChessGame.ChessBoard;
import ChessGame.ChessPiece;

public class MoveHorizontalRule implements MoveRule
{
    Integer maxNumberOfSquares; // -1 for infinite
    public MoveHorizontalRule(Integer maxNumberOfSquares) {this.maxNumberOfSquares = maxNumberOfSquares;}
    @Override
    public Boolean isValidMove(ChessBoard board, ChessPiece piece, int toX, int toY)
    {
       return toY == piece.y() && Math.abs(toX - piece.x()) <= maxNumberOfSquares;
    }
}
