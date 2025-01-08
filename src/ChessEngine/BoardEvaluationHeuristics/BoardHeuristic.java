package ChessEngine.BoardEvaluationHeuristics;

import ChessEngine.BoardEvaluationHeuristics.HeuristicFactors.MaterialFactor;
import ChessEngine.BoardEvaluationHeuristics.HeuristicFactors.MoveCountFactor;
import ChessGame.ChessBoard;

public enum BoardHeuristic
{
    INSTANCE( new MoveCountFactor(0.01f), new MaterialFactor(1.0f));
    BoardHeuristic(HeuristicFactor... factors) {this.factors = factors;}
    final HeuristicFactor[] factors;
    public float getHeuristic(ChessBoard board)
    {
        float sumWeights = 0;
        float sumHeuristics = 0;
        for (HeuristicFactor factor : factors)
        {
            sumWeights += factor.weight;
            sumHeuristics += factor.evaluate(board) * factor.weight;
        }
        return sumHeuristics / sumWeights;
    }
}
