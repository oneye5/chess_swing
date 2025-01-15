package ChessEngine;

import ChessEngine.BoardEvaluationHeuristics.BoardHeuristic;
import ChessGame.ChessBoard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.Function;


public class AStarMoveSearch implements MoveSearchAlgorithm
{
    Integer maxDepth,maxCheckedNodes;
    public AStarMoveSearch(Integer maxDepth, Integer maxCheckedNodes) {this.maxDepth = maxDepth; this.maxCheckedNodes = maxCheckedNodes;}

    private PriorityQueue<TreeNode> queue;
    @Override
    public Integer[] findBestMove(ChessBoard currentBoard)
    {
        System.out.println("Starting a* search for best move");

        // all nodes at the depth limit will end up at the end of the queue
        Comparator<TreeNode> comparator = (o1, o2) -> o1.getDepth() >= maxDepth ? 1 : o1.compareTo(o2);
        queue = new PriorityQueue<TreeNode>(comparator);

        queue.add(TreeNode.root(currentBoard));
        for (int i = 0; i < maxCheckedNodes; i++)
        {
            var head = queue.poll();
            if (head.getDepth() > maxDepth) // no more nodes exist that have a depth less than the max depth
                break;

            if(i%600 == 0)
            {
                Float progress = (float)i/(float)maxCheckedNodes;
                progress *= 100.0f;
                progress += 0.0001f;
                var out = progress.toString().substring(0,4);
                System.out.println(out + " %");
            }

            queue.addAll(head.getChildren());
        }
        // find the most favorable leaf in the tree by sorting by the average



        System.out.println("Finished tree search, with " + queue.size() + " considered nodes\n selecting the best move");

        Function<TreeNode,Float> averagePathHeuristic = (n)-> {
            var path = n.getPathToRoot();
            return (float) ((path.stream().mapToDouble(x-> currentBoard.WhitesTurn() ? 1.0 - x.nodeHeuristic : x.nodeHeuristic).sum() / (double) path.size()));
        };
        var sorted = new ArrayList<TreeNode>(queue.stream().toList());
        sorted.sort((a,b)-> Float.compare(averagePathHeuristic.apply(a), averagePathHeuristic.apply(b)));



        var out = findRootMove(sorted.get(0));
        return out;
    }

    private Integer[] findRootMove(TreeNode node)
    {
        TreeNode current = node;
        while(current.parent.parent != null) {current = current.parent;}
        return current.precedingMove;
    }
}