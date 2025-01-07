package ChessEngine.BoardEvaluationHeuristics.HeuristicFactors;
import ChessEngine.BoardEvaluationHeuristics.HeuristicFactor;
import ChessGame.ChessBoard;
import ChessGame.ChessPiece;

public class AttackingFactor extends HeuristicFactor
{
    public AttackingFactor(float weight){super(weight);}

    @Override
    public float evaluate(ChessBoard board)
    {return 0;}
}
