package ChessGame.Rules;

import ChessGame.ChessBoard;
import ChessGame.ChessPiece;
import ChessGame.PieceType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EnPassantRule implements MoveRule
{
    @Override
    public Boolean isValidMove(ChessBoard board, ChessPiece piece, int toX, int toY)
    {
        // ensure current board has previous moves
        if (board.prevBoard() == null)
            return false;

        // find pawns left and right of current
        Integer yDir = piece.isWhitePiece() ? 1 : -1;

        List<ChessPiece> candidates = new ArrayList<>();
        if(piece.x() != 7)
            candidates.add(board.board()[piece.x()+1][piece.y()]);

        if(piece.x() != 0)
            candidates.add(board.board()[piece.x()-1][piece.y()]);

        // make sure adjacent pieces are pawns and are non-null
        candidates = candidates.stream().filter(Objects::nonNull).filter(p -> p.PieceType() == PieceType.PAWN).toList();


        candidates = candidates.stream().filter(p -> {
            var x = p.x(); var y = p.y();
            var prev = board.prevBoard().board();

            return prev[x][y] == null // the piece has just moved
                    && prev[x][y + yDir] == null // the piece has not moved one square y
                    && prev[x][y + (2*yDir)].PieceType() == PieceType.PAWN; // the piece has moved two squares up the board
        }).toList();

        return candidates.stream().anyMatch(c-> c.x() == toX && c.y() + yDir == toY);
    }
}
