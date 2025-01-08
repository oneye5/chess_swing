package ChessEngine;

import ChessEngine.BoardEvaluationHeuristics.BoardHeuristic;
import ChessGame.ChessBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class AStarMoveSearch implements MoveSearchAlgorithm
{
    {
        TreeNode.costToRootIncrementalCost = 0.1f;
    }
    Integer maxDepth;
    public AStarMoveSearch(Integer maxDepth) {this.maxDepth = maxDepth;}


    private PriorityQueue<TreeNode> queue;
    @Override
    public Integer[] findBestMove(ChessBoard currentBoard)
    {
        System.out.println("Starting search for best move");

        queue = new PriorityQueue<TreeNode>();
        queue.add(TreeNode.root(currentBoard));
        for (int i = 0; true; i++)
        {
            var head = queue.poll();
            if (head.getDepth() > maxDepth)
            {
                System.out.println("a* iterations = " + i);
                System.out.println("considered positions = " + queue.size());
                return findRootMove(head);
            }


            queue.remove(head);
            queue.addAll(head.getChildren());
        }
    }
    private Integer[] findRootMove(TreeNode node)
    {
        TreeNode current = node;
        while(current.parent.parent != null) {current = current.parent;}
        return current.precedingMove;
    }
}