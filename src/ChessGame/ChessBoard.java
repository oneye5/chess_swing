package ChessGame;

import java.util.Arrays;

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
    public static ChessBoard newBoardWithPieces(ChessPiece... pieces)
    {
        ChessPiece[][] board = new ChessPiece[8][8];
        Arrays.stream(pieces).forEachOrdered(piece -> board[piece.x()][piece.y()] = piece);
        return new ChessBoard(board,null);
    }
    public ChessBoard newBoardWithMove(Integer pieceX, Integer pieceY, Integer desiredX, Integer desiredY)
    {
        var newBoard = board.clone();
        newBoard[pieceX][pieceY] = null;
        newBoard[desiredX][desiredY] = board[pieceX][pieceY];
        return new ChessBoard(newBoard,this);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for(int x = 7; x >= 0; x--)
        {
            sb.append("\n");
            for (int y = 0; y < 8; y++)
                if (board[x][y] != null)
                    sb.append(board[x][y].PieceType().toString().charAt(0));
                else
                    sb.append("_");
        }
        return sb.toString();
    }
}
