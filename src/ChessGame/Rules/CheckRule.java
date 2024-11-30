package ChessGame.Rules;

import ChessGame.ChessBoard;
import ChessGame.ChessPiece;


public class CheckRule implements MoveRule {
    @Override
    public Boolean isValidMove(ChessBoard board, ChessPiece piece, int toX, int toY)
    {
        /*
        // Create a new board with the proposed move
        ChessBoard newBoard = board.newBoardWithMove(piece.x(), piece.y(), toX, toY);

        // Check if the move puts or leaves the moving player in check
        return !newBoard.isInCheck(piece.isWhitePiece());
        */
        //board.newBoardWithMove(piece.x(),piece.y(),toX,toY);

         return true;
    }
}