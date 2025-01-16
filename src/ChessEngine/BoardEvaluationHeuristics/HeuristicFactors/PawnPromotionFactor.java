package ChessEngine.BoardEvaluationHeuristics.HeuristicFactors;
import ChessEngine.BoardEvaluationHeuristics.HeuristicFactor;
import ChessGame.ChessBoard;
import ChessGame.ChessPiece;
import ChessGame.PieceType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class PawnPromotionFactor extends HeuristicFactor
{

    public PawnPromotionFactor(float weight){super(weight);}

    @Override
    public float evaluate(ChessBoard board)
    {
        var whitePawns = board.getAllPieces().stream().filter(ChessPiece::isWhitePiece).filter(p->p.PieceType() == PieceType.PAWN).toList();
        var blackPawns = board.getAllPieces().stream().filter(p->!p.isWhitePiece()).filter(p->p.PieceType() == PieceType.PAWN).toList();

        // find forward most pawn for each colum, and check if it is an open file

        List<ChessPiece> openFileWhitePawns = new ArrayList<>();
        List<ChessPiece> openFileBlackPawns = new ArrayList<>();

        //check white
        for(int x = 0; x < 8; x++)
        {
            int finalX = x;
            var colum = new ArrayList<>(whitePawns.stream().filter(p -> p.x() == finalX).toList());
            if (colum.size() == 0)
                continue;
            colum.sort(Comparator.comparingInt(ChessPiece::y));
            var pawn = colum.get(colum.size()-1);

            // check for open file for the pawn
            boolean openFile = true;
            for(int y = pawn.y() + 1; y < 8; y++)
                if(board.board()[x][y] != null)
                {
                    openFile = false;
                    break;
                }

            if (!openFile)
                continue;

            openFileWhitePawns.add(pawn);
        }


        //check black
        for(int x = 0; x < 8; x++)
        {
            int finalX = x;
            List<ChessPiece> colum = new ArrayList<>(blackPawns.stream().filter(p -> p.x() == finalX).toList());
            if (colum.isEmpty())
                continue;
            colum.sort(Comparator.comparingInt(ChessPiece::y));

            var pawn = colum.get(colum.size()-1);

            // check for open file for the pawn
            boolean openFile = true;
            for(int y = pawn.y() - 1; y >= 0; y--)
                if(board.board()[x][y] != null)
                {
                    openFile = false;
                    break;
                }

            if (!openFile)
                continue;

            openFileBlackPawns.add(pawn);
        }

        float whiteScore = openFileWhitePawns.stream().mapToInt(ChessPiece::y).sum();
        float blackScore = openFileBlackPawns.stream().mapToInt(p->7-p.y()).sum();
        return (whiteScore + 1.0f) / (blackScore + 1.0f) / 6.5f - 0.5f;
    }
}
