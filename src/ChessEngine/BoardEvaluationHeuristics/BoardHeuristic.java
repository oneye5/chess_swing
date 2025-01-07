package ChessEngine.BoardEvaluationHeuristics;

import ChessEngine.BoardEvaluationHeuristics.HeuristicFactors.MaterialFactor;

public enum BoardHeuristic
{
    INSTANCE(new MaterialFactor(1.0f));
    BoardHeuristic(HeuristicFactor... factors) {this.factors = factors;}
    final HeuristicFactor[] factors;
}
