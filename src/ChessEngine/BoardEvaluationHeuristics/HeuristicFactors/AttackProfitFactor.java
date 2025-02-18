package ChessEngine.BoardEvaluationHeuristics.HeuristicFactors;
import ChessEngine.BoardEvaluationHeuristics.HeuristicFactor;
import ChessGame.ChessBoard;
import ChessGame.ChessPiece;
import ChessGame.PieceType;

/**
 * AttackProfitFactor
 * This is essentially incentivizing less valuable pieces to take more valuable pieces, known as
 * MVV-LVA (most valuable victim, least valuable attacker)
 *
 * @author Owan Lazic
 */

public class AttackProfitFactor extends HeuristicFactor
{
    public AttackProfitFactor(float weight){super(weight);}

    @Override
    public float evaluate(ChessBoard board)
    {
        Integer[] move = board.getPrecedingMove();
        if(move == null)
            return 0.5f;

        ChessPiece from = board.prevBoard().board()[move[0]][move[1]];
        ChessPiece to = board.prevBoard().board()[move[2]][move[3]];

        if(to == null) // no piece was taken
            return 0.5f;


        var valueDifference = to.PieceType().getPieceValue() - from.PieceType().getPieceValue();

        var out = to.isWhitePiece() ? -valueDifference : valueDifference; // make context dependant

        var maxDifference = PieceType.QUEEN.getPieceValue() - PieceType.PAWN.getPieceValue();
        out /= maxDifference; // normalize to a value between -1 and 1
        out = (out/2.0f) + 0.5f; // convert to a value between 0 and 1
        return out;
    }
}
