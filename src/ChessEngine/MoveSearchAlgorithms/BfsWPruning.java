package ChessEngine.MoveSearchAlgorithms;

import ChessEngine.TreeNode;
import ChessGame.ChessBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;



public class BfsWPruning implements MoveSearchAlgorithm
{
    public static final float MAX_HEURISTIC_DELTA = 0.7f;

    private Supplier<Integer> depthSupplier; // supplier so we can easily update this during runtime behind the scenes
    public BfsWPruning(Supplier<Integer> depth) {depthSupplier = depth;}
    @Override
    public byte[] findBestMove(ChessBoard currentBoard)
    {
        var depth = depthSupplier.get();
        System.out.println("Starting bfs brute force search");
        var rootNode =  TreeNode.root(currentBoard);

        if(1==1)
            return Minimax.findMove(rootNode,depth).precedingMove;

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

            var allChildren = currentBreadth.stream() // parallel stream cutts execution time in half compared to simple for loop
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
