package ChessEngine.MoveSearchAlgorithms;

import ChessEngine.TreeNode;
import com.sun.source.tree.Tree;

import java.util.HashMap;
import java.util.List;


public class Minimax
{
    public static float minimax(TreeNode node, int depth)
    {
        float value;
        if (depth == 0 || !node.hasEvaluatedChildren())
            return node.getNodeHeuristic();

        value = node.board.WhitesTurn() ? Float.NEGATIVE_INFINITY : Float.POSITIVE_INFINITY ;
        for(var n : node.getChildren())
            value = node.board.WhitesTurn() ? Math.max(value, minimax(n, depth - 1)) : Math.min(value, minimax(n, depth - 1));

        return value;
    }

    /**
     * @param root node, the base of the tree of all moves, the root is the current board state
     * @return best move as treeNode (depth of 1)
     */
    public static TreeNode findMove(TreeNode root, int depth)
    {
        List<TreeNode> moves = root.getChildren();
        TreeNode bestMove = null;
        float bestValue = root.board.WhitesTurn() ? Float.MIN_VALUE : Float.MAX_VALUE;

        for (TreeNode move : moves)
        {
            float value = minimax(move, depth-1);

            if (root.board.WhitesTurn() ? value > bestValue : value < bestValue)
            {
                bestValue = value;
                bestMove = move;
            }
        }

        return bestMove;
    }
}
