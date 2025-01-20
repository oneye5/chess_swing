package ChessGame.Rules;
import ChessGame.ChessBoard;
import ChessGame.ChessPiece;

/**
 * CannotLandOnEnemyRule,
 * Used for pawns, since they are unable to land on 'bad' pieces with their normal vertical movement.
 *
 * @see MoveRule
 * @author Owan Lazic
 */


public class CannotLandOnEnemyRule implements MoveRule
{
    @Override
    public Boolean isValidMove(ChessBoard board, ChessPiece piece, byte toX, byte toY)
    {
        return board.board()[toX][toY] == null || board.board()[toX][toY].isWhitePiece() == piece.isWhitePiece();
    }
}
