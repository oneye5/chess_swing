package ChessEngine.BoardEvaluationHeuristics;

import ChessEngine.BoardEvaluationHeuristics.HeuristicFactors.*;
import ChessGame.ChessBoard;

public enum BoardHeuristic
{   // new MoveCountFactor(0.4f)  this factor has been omitted due to its computational cost
    PERFORMANT(new MaterialFactor(1.0f), new CentrePositioning(0.2f)),
    DETAILED(new MaterialFactor(2.0f),new PawnStructureFactor(0.01f), new CentrePositioning(0.05f) , new MoveCountFactor(1.0f));
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
