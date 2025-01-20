package ChessGame.Rules;

import ChessGame.ChessBoard;
import ChessGame.ChessPiece;
import ChessGame.PieceType;

public class CastlingRule implements MoveRule
{
    @Override
    public Boolean isValidMove(ChessBoard board, ChessPiece piece, byte toX, byte toY)
    {
        int deltaX = toX - piece.x();
        int deltaY = toY - piece.y();

        if(deltaY != 0) // does not move on the y-axis
            return false;

        if(piece.PieceType() != PieceType.KING) // castling must be initiated by moving the king
            return false;

        if(piece.hasMoved()) // king must not have moved
            return false;

        //TODO: check for line of sight

        if(deltaX == 2) //SHORT CASTLE
            return !(board.board()[7][toY] == null || board.board()[7][toY].hasMoved()); // ensure rook is in starting position and has not moved
        else if (deltaX == -2) //LONG CASTLE
            return !(board.board()[0][toY] ==  null || board.board()[0][toY].hasMoved());
        else
            return false;
    }
}
