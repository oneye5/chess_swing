package ChessGame.Rules;

import ChessGame.ChessBoard;
import ChessGame.ChessPiece;

public interface MoveRule
{
    /**
     *
     * @param board Object containing current and previous board states.
     * @param piece The desired piece on the board to test a move.
     * @param toX   Desired X position on the board to test if the desired piece can move to it.
     * @param toY   Desired X position on the board to test if the desired piece can move to it.
     * @return returns if it is a valid move or not.
     */
    Boolean isValidMove(ChessBoard board, ChessPiece piece, int toX, int toY);
}
