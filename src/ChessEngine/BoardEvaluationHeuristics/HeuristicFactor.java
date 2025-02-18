package ChessEngine.BoardEvaluationHeuristics;

import ChessGame.ChessBoard;
/**
 * HeuristicFactor
 * All heuristic factors implement this, used in BoardHeuristic
 *
 * @author Owan Lazic
 */

public abstract class HeuristicFactor
{
    public float weight;
    public HeuristicFactor(float weight) {this.weight = weight;}
    public abstract float evaluate(ChessBoard board);
}
