package ChessGame;

/**
* @param board 0,0 (a1) is bottom left, 7,7 (h8) is top right
*
*/
public record ChessBoard (ChessPiece[][] board,ChessBoard prevBoard)
{
    public static ChessBoard newBoardWithPieces()
    {
        ChessPiece[][] board = new ChessPiece[8][8];
        return new ChessBoard(board,null);
    }
    public ChessBoard newBoardWithMove(Integer pieceX, Integer pieceY, Integer desiredX, Integer desiredY)
    {
        var newBoard = board.clone();
        newBoard[pieceX][pieceY] = null;
        newBoard[desiredX][desiredY] = board[pieceX][pieceY];
        return new ChessBoard(newBoard,this);
    }

}
