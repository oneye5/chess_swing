package ChessGame.Rules;

import ChessGame.ChessBoard;
import ChessGame.ChessPiece;

public class PawnTakeRule implements MoveRule
{
    @Override
    public Boolean isValidMove(ChessBoard board, ChessPiece piece, int toX, int toY)
    {
        return board.board()[toX][toY] != null
                && Math.abs(piece.x() - toX) == 1
                && Math.abs(piece.y() - toY) == 1
                && board.board()[toX][toY].isWhitePiece() != piece.isWhitePiece();
    }

}
