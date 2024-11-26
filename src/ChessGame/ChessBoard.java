package ChessGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
* @param board 0,0 (a1) is bottom left, 7,7 (h8) is top right
*
*/
public record ChessBoard (ChessPiece[][] board,ChessBoard prevBoard, Boolean WhitesTurn)
{
    public static ChessBoard newBoardWithPieces()
    {
        ChessPiece[][] board = new ChessPiece[8][8];
        return new ChessBoard(board,null,true);
    }
    public static ChessBoard newBoardWithPieces(ChessPiece... pieces)
    {
        ChessPiece[][] board = new ChessPiece[8][8];
        Arrays.stream(pieces).forEachOrdered(piece -> board[piece.x()][piece.y()] = piece);
        return new ChessBoard(board,null, true);
    }
    public static ChessBoard newGame()
    {
        return newBoardWithPieces(newPiecesWithDefaultPosition());
    }
    public static ChessPiece[] newPiecesWithDefaultPosition() {
        List<ChessPiece> pieces = new ArrayList<ChessPiece>();

        // Set up pawns (ranks 2 and 7)
        for (int x = 0; x < 8; x++) {  // Fixed: should be < 8, not < 7
            pieces.add(new ChessPiece(PieceType.PAWN, x, 1, true, false));   // White pawns
            pieces.add(new ChessPiece(PieceType.PAWN, x, 6, false, false));  // Black pawns
        }

        // White pieces (rank 1)
        pieces.add(new ChessPiece(PieceType.ROOK,   0, 0, true, false));
        pieces.add(new ChessPiece(PieceType.KNIGHT, 1, 0, true, false));
        pieces.add(new ChessPiece(PieceType.BISHOP, 2, 0, true, false));
        pieces.add(new ChessPiece(PieceType.QUEEN,  3, 0, true, false));
        pieces.add(new ChessPiece(PieceType.KING,   4, 0, true, false));
        pieces.add(new ChessPiece(PieceType.BISHOP, 5, 0, true, false));
        pieces.add(new ChessPiece(PieceType.KNIGHT, 6, 0, true, false));
        pieces.add(new ChessPiece(PieceType.ROOK,   7, 0, true, false));

        // Black pieces (rank 8 - appears as y=7 in zero-based coordinates)
        pieces.add(new ChessPiece(PieceType.ROOK,   0, 7, false, false));
        pieces.add(new ChessPiece(PieceType.KNIGHT, 1, 7, false, false));
        pieces.add(new ChessPiece(PieceType.BISHOP, 2, 7, false, false));
        pieces.add(new ChessPiece(PieceType.QUEEN,  3, 7, false, false));
        pieces.add(new ChessPiece(PieceType.KING,   4, 7, false, false));
        pieces.add(new ChessPiece(PieceType.BISHOP, 5, 7, false, false));
        pieces.add(new ChessPiece(PieceType.KNIGHT, 6, 7, false, false));
        pieces.add(new ChessPiece(PieceType.ROOK,   7, 7, false, false));

        return pieces.toArray(new ChessPiece[0]);
    }
    public ChessBoard newBoardWithMove(Integer pieceX, Integer pieceY, Integer desiredX, Integer desiredY)
    {
        var newBoard = board.clone();
        var piece = board[pieceX][pieceY];
        newBoard[pieceX][pieceY] = null;
        newBoard[desiredX][desiredY] = new ChessPiece(piece.PieceType(), desiredX, desiredY, piece.isWhitePiece(), true);
        Boolean whitesTurn = !newBoard[desiredX][desiredY].isWhitePiece();
        return new ChessBoard(newBoard,this,whitesTurn);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for(int x = 7; x >= 0; x--)
        {
            sb.append("\n");
            for (int y = 0; y < 8; y++)
                if (board[y][x] != null)
                    sb.append(board[y][x].PieceType().toString().charAt(0));
                else
                    sb.append("_");
        }
        return sb.toString();
    }
}

