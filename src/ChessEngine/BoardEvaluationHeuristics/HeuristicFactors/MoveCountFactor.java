package ChessEngine.BoardEvaluationHeuristics.HeuristicFactors;
import ChessEngine.BoardEvaluationHeuristics.HeuristicFactor;
import ChessGame.ChessBoard;
import ChessGame.ChessPiece;

//this is very slow!
public class MoveCountFactor extends HeuristicFactor
{
    public MoveCountFactor(float weight){super(weight);}

    @Override
    public float evaluate(ChessBoard board)
    {
        var b = board.modifyTurn(true);
        float white = b.getAllMoves().size();

        b = board.modifyTurn(false);
        float black = b.getAllMoves().size();

        return white / (white + black);
    }
}
