package ChessEngine;

import ChessEngine.BoardEvaluationHeuristics.BoardHeuristic;
import ChessGame.ChessBoard;

import java.util.ArrayList;
import java.util.List;

public class TreeNode implements Comparable<TreeNode>
{
    public float nodeHeuristic;
    public TreeNode parent;
    public ChessBoard board;
    public Integer[] precedingMove;

    public static TreeNode root(ChessBoard board) {return new TreeNode(null,board,null, BoardHeuristic.INSTANCE.getHeuristic(board));};
    public float getContextDependantHeuristic(){return board.WhitesTurn() ? nodeHeuristic : 1.0f - nodeHeuristic;}
    private TreeNode(TreeNode parent, ChessBoard board, Integer[] move, float nodeHeuristic)
    {
        this.parent = parent;
        this.board = board;
        this.precedingMove = move;
        this.nodeHeuristic = nodeHeuristic;
    }
    public TreeNode(TreeNode parent, Integer[] move)
    {
        this.parent = parent;
        this.precedingMove = move;
        this.board = parent.board.newBoardWithMove(move);
        this.nodeHeuristic = BoardHeuristic.INSTANCE.getHeuristic(board);
    }
    public List<TreeNode> getChildren()
    {
        var moves = board.getAllMoves();
        List<TreeNode> out = new ArrayList<>();

        for (var move : moves)
            out.add(new TreeNode(this ,move));

        return out;
    }

    /**
     * the heuristic value of each node is context dependant,
     * for example the board heuristic 0.6 would look like the following:
     * white to move (0.6)
     * black to move (0.4)
     * this needs to be done so that the move finding algorithm will select the most favorable move for both white and black
     * @return summed context dependant heuristic from this node to the root
     */
    public float contextDependantCostToRoot()
    {
        if(parent == null) // must be root
            return nodeHeuristic;
        return getContextDependantHeuristic() -0.5f + costToRootIncrementalCost + parent.contextDependantCostToRoot() ;
    }
    static float costToRootIncrementalCost = 0.0f;
    public int getDepth()
    {
        if (parent == null)
            return 0;
        return 1 + parent.getDepth();
    }
    public TreeNode getRoot()
    {
        if (parent == null)
            return this;
        return parent.getRoot();
    }

    @Override
    public int compareTo(TreeNode o)
    {
       return Float.compare(this.contextDependantCostToRoot(), o.contextDependantCostToRoot());
       // return Float.compare(nodeHeuristic, o.nodeHeuristic);
    }
    @Override
    public String toString()
    {
        return "node h:" + nodeHeuristic + " path h:" + contextDependantCostToRoot() ;
    }
}
