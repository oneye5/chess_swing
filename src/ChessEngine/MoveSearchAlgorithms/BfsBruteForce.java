package ChessEngine.MoveSearchAlgorithms;

import ChessEngine.TreeNode;
import ChessGame.ChessBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class BfsBruteForce implements MoveSearchAlgorithm
{
    int depth;
    public BfsBruteForce(int depth) {this.depth = depth;}
    @Override
    public Integer[] findBestMove(ChessBoard currentBoard)
    {
        System.out.println("Starting bfs brute force search");

        List<TreeNode> currentBreadth = new ArrayList<>();
        List<TreeNode> nextBreadth = new ArrayList<TreeNode>();
        int currentDepth = 0;

        nextBreadth.add( TreeNode.root(currentBoard));
        while (!nextBreadth.isEmpty() && currentDepth < depth)
        {
            currentDepth++;
            currentBreadth = nextBreadth;
            nextBreadth = new ArrayList<>();

            System.out.println("searching breadth at depth: " + currentDepth);

            for (TreeNode node : currentBreadth)
                nextBreadth.addAll(node.getChildren());
        }

        System.out.println("finnished tree discovery, starting sort for best move, selecting from " + nextBreadth.size() + " leaf nodes");
        // now find most desirable leaf
        Function<TreeNode,Float> averagePathHeuristic = (n)-> {
            var path = n.getPathToRoot();
            return (float) ((path.stream().mapToDouble(x-> currentBoard.WhitesTurn() ? 1.0 - x.nodeHeuristic : x.nodeHeuristic).sum() / (double) path.size()));
        };
        var sorted = new ArrayList<TreeNode>(nextBreadth);
        sorted.sort((a,b)-> Float.compare(averagePathHeuristic.apply(a), averagePathHeuristic.apply(b)));
        System.out.println("move path to root");
        sorted.get(0).printBoardsToRoot();
       return findRootMove(sorted.get(0));
    }
}
