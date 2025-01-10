package ChessEngine.BoardEvaluationHeuristics.HeuristicFactors;
import ChessEngine.BoardEvaluationHeuristics.HeuristicFactor;
import ChessGame.ChessBoard;
import ChessGame.ChessPiece;

public class MaterialFactor extends HeuristicFactor
{
    public MaterialFactor(float weight){super(weight);}

    @Override
    public float evaluate(ChessBoard board)
    {
        var sumWhite = board.getAllPieces()
                .stream()
                .filter(ChessPiece::isWhitePiece)
                .mapToDouble(p-> (double) p.PieceType().getPieceValue())
                .sum();
        var sumBlack = board.getAllPieces()
                .stream()
                .filter(p->!p.isWhitePiece())
                .mapToDouble(p-> (double) p.PieceType().getPieceValue())
                .sum();

        //divide by 0 case
        if (sumWhite + sumBlack == 0)
            return 0.5f;

        float materialDifference = (float)(sumWhite-sumBlack);

        return 1.0f / (1.0f + (float)Math.exp(-materialDifference / 8.0f)); //sigmoid 0-1
        //return (float)(sumWhite - sumBlack); //raw
        //return (float) (sumWhite / (sumWhite + sumBlack)); //ratio 0-1
    }
}
