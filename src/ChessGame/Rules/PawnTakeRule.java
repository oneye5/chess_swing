package ChessGame.Rules;
import ChessGame.ChessBoard;
import ChessGame.ChessPiece;

/**
 * PawnTakeRule,
 * permits a piece to take another directly diagonal to itself.
 *
 * @see MoveRule
 * @author Owan Lazic
 */

public class PawnTakeRule implements MoveRule
{
    @Override
    public Boolean isValidMove(ChessBoard board, ChessPiece piece, byte toX, byte toY)
    {
        return board.board()[toX][toY] != null
                && Math.abs(piece.x() - toX) == 1
                && Math.abs(piece.y() - toY) == 1
                && board.board()[toX][toY].isWhitePiece() != piece.isWhitePiece();
    }

}
