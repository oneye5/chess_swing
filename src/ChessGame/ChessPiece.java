package ChessGame;

import java.util.ArrayList;
import java.util.List;

/**
 * ChessPiece
 * This class represents every piece, however behaviour is different based on what pieceType is assigned
 *
 * @param PieceType what kind of piece is this object
 * @param x position on the board
 * @param y position on the board
 * @param isWhitePiece does this piece belong to white
 * @param hasMoved has this piece been moved from its original position
 * @author Owan Lazic
 */
public record ChessPiece(PieceType PieceType, int x, int y, boolean isWhitePiece, boolean hasMoved)
{
    public List<Integer[]> getPossibleMoves(ChessBoard board)
    {
        List<Integer[]> out = new ArrayList<>();
        for (int x = 0; x < 8; x++)
            for (int y = 0; y < 8; y++)
                if(PieceType().getMoveRule().isValidMove(board, this, x, y))
                    out.add(new Integer[]{x, y});

        return out;
    }
    public ChessPiece deepClone()
    {
        return new ChessPiece(PieceType, x, y, isWhitePiece, hasMoved);
    }
}
