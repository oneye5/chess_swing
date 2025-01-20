package ChessGame.Rules;
import ChessGame.ChessBoard;
import ChessGame.ChessPiece;

/**
 * CannotLandOnFriendlyRule,
 * All chess pieces are unable to land on other pieces that are of the same color.
 *
 * @see MoveRule
 * @author Owan Lazic
 */

public class CannotLandOnFriendlyRule implements MoveRule
{
    @Override
    public Boolean isValidMove(ChessBoard board, ChessPiece piece, byte toX, byte toY)
    {
        return board.board()[toX][toY] == null || board.board()[toX][toY].isWhitePiece() != piece.isWhitePiece();
    }
}
