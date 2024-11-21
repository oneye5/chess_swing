package ChessGame.Rules;

import ChessGame.ChessBoard;
import ChessGame.ChessPiece;

public interface MoveRule
{
    /**
     * Determines if the rule is disjunctive or conjunctive.
     *
     * eg. valid moves are determined by doing the following
     * (disjunctiveRule1 || disjunctiveRule2 ...) && conjunctiveRule1 && conjunctiveRule2...
     * @return false indicating that the rule is conjunctive by default.
     */
    default Boolean isDisjunctiveRule(){return false;}

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
