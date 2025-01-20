package ChessEngine.BoardEvaluationHeuristics.HeuristicFactors;

import ChessEngine.BoardEvaluationHeuristics.HeuristicFactor;
import ChessGame.ChessBoard;
import ChessGame.ChessPiece;
import ChessGame.PieceType;

public class PawnStructureFactor extends HeuristicFactor
{
    public PawnStructureFactor(float weight){super(weight);}

    @Override
    public float evaluate(ChessBoard board)
    {
       var whitePawns = board.getAllPieces().stream().filter(ChessPiece::isWhitePiece).filter(p->p.PieceType() == PieceType.PAWN).toList();
       var blackPawns = board.getAllPieces().stream().filter(p->!p.isWhitePiece()).filter(p->p.PieceType() == PieceType.PAWN).toList();
       float protectedWhite = 0, protectedBlack = 0;

       // check white for pawns defending pawns
       for (var p : whitePawns)
       {
           byte[] target = new byte[]{p.x(), p.y()};
           if (!(target[0] >= 0 && target[0] < 8 && target[1] >= 0 && target[1] < 8 )) // out of bounds
               continue;
           var left = target[0] - 1 >= 0 ? board.board()[target[0]-1][target[1]-1] : null;
           var right = target[0] + 1 < 8 ? board.board()[target[0]+1][target[1]-1] : null;

           var leftGood = left != null && left.isWhitePiece() && left.PieceType() == PieceType.PAWN;
           var rightGood = right != null && right.isWhitePiece() && right.PieceType() == PieceType.PAWN;

           if(leftGood)
               protectedWhite += 1.0f;
           if(rightGood)
               protectedWhite += 1.0f;
       }

       // check black for pawns defending pawns

        for (var p : blackPawns)
        {
            byte[] target = new byte[]{p.x(), p.y()};
            if (!(target[0] >= 0 && target[0] < 8 && target[1] >= 0 && target[1] < 8 )) // out of bounds
                continue;
            var left = target[0] - 1 >= 0 ? board.board()[target[0]-1][target[1]+1] : null;
            var right = target[0] + 1 < 8 ? board.board()[target[0]+1][target[1]+1] : null;

            var leftGood = left != null && !left.isWhitePiece() && left.PieceType() == PieceType.PAWN;
            var rightGood = right != null && !right.isWhitePiece() && right.PieceType() == PieceType.PAWN;

            if(leftGood)
                protectedBlack += 1.0f;
            if(rightGood)
                protectedBlack += 1.0f;
        }

        // calculate heuristic
        float whiteRatio, blackRatio;

        if(whitePawns.isEmpty())
            whiteRatio = 0.0f;
        else
            whiteRatio = protectedWhite / whitePawns.size();

        if (blackPawns.isEmpty())
            blackRatio = 0.0f;
        else
            blackRatio = protectedBlack / blackPawns.size();

        return (whiteRatio - blackRatio) / 2.0f + 0.5f;
    }
}
