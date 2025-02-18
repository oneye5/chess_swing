package ChessEngine.MoveSearchAlgorithms;

import ChessEngine.TreeNode;
import ChessGame.ChessBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;


/**
 * StandaloneMiniMax
 * Implements MoveSearchAlgorithm, provides a complete implementation of 'findBestMove' unlike the MiniMax class itself
 * The algorithm is a standalone algorithm, meaning that no other algorithms are used in the search for the best move, and that there are no pre-passes before searching the tree.
 *
 * @author Owan Lazic
 */

public class StandaloneMinimax implements MoveSearchAlgorithm
{
    private final Supplier<Integer> depthSupplier; // Instead of being a simple field a supplier is used for easier/cleaner updating of this value
    public StandaloneMinimax(Supplier<Integer> depth) {depthSupplier = depth;}
    @Override
    public byte[] findBestMove(ChessBoard currentBoard)
    {
        System.out.println("Starting standalone minimax move search");

        int depth = depthSupplier.get();
        var root = TreeNode.root(currentBoard);

        return Minimax.parallelFindMove(root,depth,false).precedingMove;
    }
}
