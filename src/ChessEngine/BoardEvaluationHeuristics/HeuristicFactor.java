package ChessEngine.BoardEvaluationHeuristics;

import ChessGame.ChessBoard;

public abstract class HeuristicFactor
{
    public float weight;
    public HeuristicFactor(float weight) {this.weight = weight;}
    public abstract float evaluate(ChessBoard board);
}
