package ChessGame;

import java.util.ArrayList;
import java.util.List;

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
}
