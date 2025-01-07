package ChessEngine.BoardEvaluationHeuristics.HeuristicFactors;
import ChessEngine.BoardEvaluationHeuristics.HeuristicFactor;
import ChessGame.ChessBoard;

public class MaterialFactor extends HeuristicFactor
{
    public MaterialFactor(float weight){super(weight);}

    @Override
    public float evaluate(ChessBoard board)
    {
        return 0;
    }
}
