package ChessEngine.MoveSearchAlgorithms;

import ChessEngine.TreeNode;
import com.sun.source.tree.Tree;

import java.security.KeyPair;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


public class Minimax
{
    private static float minimax(TreeNode node, int depth, boolean prepassMode)
    {
        float value;

        if(depth == 0)
            return node.getNodeHeuristic();

        if(prepassMode && !node.hasEvaluatedChildren())
            return node.getNodeHeuristic();

        value = node.board.WhitesTurn() ? Float.NEGATIVE_INFINITY : Float.POSITIVE_INFINITY ;
        for(var n : node.getChildren())
            value = node.board.WhitesTurn() ? Math.max(value, minimax(n, depth - 1,prepassMode)) : Math.min(value, minimax(n, depth - 1,prepassMode));

        return value;
    }

    /**
     * @param root node, the base of the tree of all moves, the root is the current board state
     * @return best move as treeNode (depth of 1)
     */
    public static TreeNode findMove(TreeNode root, int depth, boolean prepassMode)
    {
        List<TreeNode> moves = root.getChildren();
        TreeNode bestMove = null;
        float bestValue = root.board.WhitesTurn() ? Float.MIN_VALUE : Float.MAX_VALUE;

        for (TreeNode move : moves)
        {
            float value = minimax(move, depth-1,prepassMode);

            if (root.board.WhitesTurn() ? value > bestValue : value < bestValue)
            {
                bestValue = value;
                bestMove = move;
            }
        }

        return bestMove;
    }

    record Pair<X,Y>(X x, Y y){};

    public static TreeNode findMove(TreeNode root, int depth) {return findMove(root, depth, false);}
    public static TreeNode parallelFindMove(TreeNode root, int depth, boolean prepassMode)
    {
        List<TreeNode> moves = root.getChildren();
        boolean isWhiteTurn = root.board.WhitesTurn();

        var moveValuePairs = moves.parallelStream()
                .map(move-> new Pair<TreeNode,Float>(move,minimax(move,depth,prepassMode))).toList();

        // find  the best move based on the value node pairs
        var best = moveValuePairs.get(0);
        for(var pair : moveValuePairs)
            if (pair.y() > best.y())
                best = pair;

        return best.x();
    }

    public static TreeNode parallelFindMove(TreeNode root, int depth) {return parallelFindMove(root, depth, false);}
}
