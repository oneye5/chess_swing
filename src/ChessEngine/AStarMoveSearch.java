package ChessEngine;

import ChessEngine.BoardEvaluationHeuristics.BoardHeuristic;
import ChessGame.ChessBoard;

import java.util.ArrayList;
import java.util.List;

public class AStarMoveSearch implements MoveSearchAlgorithm
{
    Integer maxDepth;
    public AStarMoveSearch(Integer maxDepth) {this.maxDepth = maxDepth;}



    @Override
    public Integer[] findBestMove(ChessBoard currentBoard)
    {
        return new Integer[0];
    }
}