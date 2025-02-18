package ChessEngine.BoardEvaluationHeuristics.HeuristicFactors;

import ChessEngine.BoardEvaluationHeuristics.HeuristicFactor;
import ChessGame.ChessBoard;
import ChessGame.ChessPiece;
import java.util.List;

/**
 * CentrePositioning
 * The more central pieces the higher the score
 *
 * @author Owan Lazic
 */

public class CentrePositioning extends HeuristicFactor
{
    public CentrePositioning(float weight) {super(weight);}

    @Override
    public float evaluate(ChessBoard board) {
        var whitePieces = board.getAllPieces().stream().filter(ChessPiece::isWhitePiece).toList();
        var blackPieces = board.getAllPieces().stream().filter(p -> !p.isWhitePiece()).toList();

        // Calculate average distance from center for each side
        float whiteAvgDist = calculateAverageDistance(whitePieces);
        float blackAvgDist = calculateAverageDistance(blackPieces);

        // Convert to a 0-1 scale where 1 means white is more central
        // and 0 means black is more central
        float maxPossibleDist = 7.0f; // Maximum possible distance (corners)
        float whiteCentrality = 1.0f - (whiteAvgDist / maxPossibleDist);
        float blackCentrality = 1.0f - (blackAvgDist / maxPossibleDist);

        // Return ratio between white and black centrality
        return (whiteCentrality) / (whiteCentrality + blackCentrality);
    }

    private float calculateAverageDistance(List<ChessPiece> pieces) {
        if (pieces.isEmpty()) return 0;

        float totalDist = 0;
        for (ChessPiece piece : pieces) {
            // Calculate Manhattan distance from center (3.5, 3.5)
            float xDist = Math.abs(3.5f - piece.x());
            float yDist = Math.abs(3.5f - piece.y());
            totalDist += xDist + yDist;
        }
        return totalDist / pieces.size();
    }
}