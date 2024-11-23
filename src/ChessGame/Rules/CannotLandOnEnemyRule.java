package ChessGame.Rules;

import ChessGame.ChessBoard;
import ChessGame.ChessPiece;

public class CannotLandOnEnemyRule implements MoveRule
{
    @Override
    public Boolean isValidMove(ChessBoard board, ChessPiece piece, int toX, int toY)
    {
        return board.board()[toX][toY] == null || board.board()[toX][toY].isWhitePiece() == piece.isWhitePiece();
    }
}
