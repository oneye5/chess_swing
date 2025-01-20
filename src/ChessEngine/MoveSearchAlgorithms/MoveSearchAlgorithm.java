package ChessEngine.MoveSearchAlgorithms;

import ChessEngine.TreeNode;
import ChessGame.ChessBoard;

public interface MoveSearchAlgorithm
{
    public byte[] findBestMove(ChessBoard currentBoard);

    // useful utility methods for the search algorithms

    default byte[] findRootMove(TreeNode node)
    {
        TreeNode current = node;
        while(current.parent.parent != null) {current = current.parent;}
        return current.precedingMove;
    }
    
}
