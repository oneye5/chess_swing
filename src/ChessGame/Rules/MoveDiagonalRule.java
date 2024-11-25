package ChessGame.Rules;

import ChessGame.ChessBoard;
import ChessGame.ChessPiece;

public class MoveDiagonalRule implements MoveRule
{
    private final Integer numSquares;
    public MoveDiagonalRule(Integer numSquares)
    {
        this.numSquares = numSquares;
    }
    @Override
    public Boolean isValidMove(ChessBoard board, ChessPiece piece, int toX, int toY)
    {
        return Math.abs(piece.x() - toX) == Math.abs(piece.y() - toY)
                && Math.abs(piece.x() - toX) <= numSquares;
    }
}
