package ChessGame.Rules;
import ChessGame.ChessBoard;
import ChessGame.ChessPiece;

/**
 * MoveUpOnlyRule,
 * Used for pawns, as they may only move 'up the board'.
 * returns false if the proposed move happens 'down the board'.
 *
 * @see MoveRule
 * @author Owan Lazic
 */

public class MoveUpOnlyRule implements MoveRule
{
    @Override
    public Boolean isValidMove(ChessBoard board, ChessPiece piece, byte toX, byte toY) {return piece.isWhitePiece() ? toY > piece.y() : toY < piece.y();}
}
