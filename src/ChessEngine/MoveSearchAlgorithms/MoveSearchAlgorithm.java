package ChessEngine.MoveSearchAlgorithms;

import ChessEngine.TreeNode;
import ChessGame.ChessBoard;

public interface MoveSearchAlgorithm
{
    public Integer[] findBestMove(ChessBoard currentBoard);

    // useful utility methods for the search algorithms

    default Integer[] findRootMove(TreeNode node)
    {
        TreeNode current = node;
        while(current.parent.parent != null) {current = current.parent;}
        return current.precedingMove;
    }
    
}
