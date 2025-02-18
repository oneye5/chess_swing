package ChessEngine.BoardEvaluationHeuristics.HeuristicFactors;
import ChessEngine.BoardEvaluationHeuristics.HeuristicFactor;
import ChessGame.ChessBoard;
import ChessGame.ChessPiece;
import java.util.Random;

/**
 * RandomFactor
 * Returns a random number between 0-1.
 *
 * @author Owan Lazic
 */

public class RandomFactor extends HeuristicFactor
{
    Random rand = new Random();
    public RandomFactor(float weight){super(weight);}

    @Override
    public float evaluate(ChessBoard board)
    {
       return rand.nextFloat();
    }
}
