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
    public byte[] findBestMove(ChessBoard currentBoard)
    {
        System.out.println("Starting bfs brute force search");

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

            for (TreeNode node : currentBreadth)
                nextBreadth.addAll(node.getChildren());
        }

        System.out.println("finnished tree discovery, starting sort for best move, selecting from " + nextBreadth.size() + " leaf nodes");

       return Minimax.findMove(root,this.depth).precedingMove;
    }
}
