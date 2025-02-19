package ChessEngine.MoveSearchAlgorithms;

import ChessEngine.TreeNode;
import ChessGame.ChessBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * BfsPruningMinimax
 * Minimax with a simple bfs pruning pre-pass, any move that has a delta heuristic greater than the maximum, gets pruned
 * this results in much faster execution but at the cost of move quality.
 *
 * @author Owan Lazic
 */

public class BfsPruningMinimax implements MoveSearchAlgorithm
{
    public static final float MAX_HEURISTIC_DELTA = 0.7f;

    private Supplier<Integer> depthSupplier; // supplier so we can easily update this during runtime behind the scenes
    public BfsPruningMinimax(Supplier<Integer> depth) {depthSupplier = depth;}
    @Override
    public byte[] findBestMove(ChessBoard currentBoard)
    {
        var depth = depthSupplier.get();
        System.out.println("Starting bfs brute force search");
        var rootNode =  TreeNode.root(currentBoard);

        List<TreeNode> currentBreadth = new ArrayList<>();
        List<TreeNode> nextBreadth = new ArrayList<TreeNode>();
        int currentDepth = 0;

        var root =  TreeNode.root(currentBoard);
        nextBreadth.add(root);
        while (!nextBreadth.isEmpty() && currentDepth < depth)
        {
            currentDepth++;
            currentBreadth = nextBreadth;
            nextBreadth = new ArrayList<>();

            System.out.println("searching breadth at depth: " + currentDepth);

            var allChildren = currentBreadth.stream() // parallel stream cuts execution time in half compared to simple for loop
                    .flatMap(x-> x.getChildren()
                            .stream()
                            .filter(y->
                            {

                                return Math.abs(x.getNodeHeuristic() - y.getNodeHeuristic()) < MAX_HEURISTIC_DELTA;
                            })) // filter out children exceeding max delta heuristic
                    .toList();

            nextBreadth.addAll(allChildren);
        }

        System.out.println("finnished tree discovery, starting sort for best move, selecting from " + nextBreadth.size() + " leaf nodes");

        return Minimax.parallelFindMove(root,depth,true).precedingMove;
    }
}
