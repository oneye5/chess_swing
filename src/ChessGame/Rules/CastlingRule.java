package ChessGame.Rules;

import ChessGame.ChessBoard;
import ChessGame.ChessPiece;

public class CastlingRule implements MoveRule
{
    @Override
    public Boolean isValidMove(ChessBoard board, ChessPiece piece, int toX, int toY)
    {
        Integer deltaX = toX - piece.x();
        Integer deltaY = toY - piece.y();

        if(deltaY != 0)
            return false;

        if(deltaX == 2)
        {

        }
        else if ()
        {

        } || deltaX == -3;
    }
}
