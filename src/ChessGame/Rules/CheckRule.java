package ChessGame.Rules;
import ChessGame.ChessBoard;
import ChessGame.ChessPiece;


/**
 * CheckRule,
 * A complicated rule in theory, where a player may not put themselves in check, or if they are already in check
 * they may not make any move that does not put the king out of check.
 * To satisfy this rule we check if the king will be in check after the proposed move has occurred.
 *
 * @see MoveRule
 * @author Owan Lazic
 */

public class CheckRule implements MoveRule {
    @Override
    public Boolean isValidMove(ChessBoard board, ChessPiece piece, int toX, int toY)
    {
        // Create a new board with the proposed move
        ChessBoard newBoard = board.newBoardWithMove(piece.x(), piece.y(), toX, toY);

        // Check if the move puts or leaves the moving player in check
        return !newBoard.isInCheck(piece.isWhitePiece());
    }
}