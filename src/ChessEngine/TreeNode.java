package ChessEngine;

import ChessEngine.BoardEvaluationHeuristics.BoardHeuristic;
import ChessGame.ChessBoard;

import java.util.ArrayList;
import java.util.List;

public class TreeNode implements Comparable<TreeNode>
{
    private Float nodeHeuristic = null;
    public TreeNode parent;
    public ChessBoard board;
    public byte[] precedingMove;
    private boolean hasEvaluatedChildren = false;

    public static TreeNode root(ChessBoard board) {return new TreeNode(null,board,null, BoardHeuristic.PERFORMANT.getHeuristic(board));}
    public float getContextDependantHeuristic(){return board.WhitesTurn() ? nodeHeuristic : 1.0f - nodeHeuristic;}
    private TreeNode(TreeNode parent, ChessBoard board, byte[] move, float nodeHeuristic)
    {
        this.parent = parent;
        this.board = board;
        this.precedingMove = move;
        this.nodeHeuristic = nodeHeuristic;
    }
    public TreeNode(TreeNode parent, byte[] move)
    {
        this.parent = parent;
        this.precedingMove = move;
        this.board = parent.board.newBoardWithMove(move);
    }
    public List<TreeNode> getChildren()
    {
        hasEvaluatedChildren = true;
        return board.getAllMoves().stream().map(x->new TreeNode(this,x)).toList();
    }

    // utility methods

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
    public List<TreeNode> getPathToRoot() {return getPathToRoot(new ArrayList<TreeNode>());} // public version of recursive method below
    private List<TreeNode> getPathToRoot(List<TreeNode> path)
    {
        if(parent == null)
            return path;
        path.add(this);
        return parent.getPathToRoot(path);
    }
    public void printBoardsToRoot()
    {
        var current = this;
        while(current.parent != null)
        {
            System.out.println(current.board);
            System.out.println(current.board.WhitesTurn()?"white to move":"black to move");
            current = current.parent;
        }
    }
    public float getNodeHeuristic()
    {
        if (nodeHeuristic == null)
            nodeHeuristic = BoardHeuristic.PERFORMANT.getHeuristic(board);
        return nodeHeuristic;
    }
    public boolean hasEvaluatedChildren() {return hasEvaluatedChildren;}
    public int compareTo(TreeNode o) {return Float.compare(getContextDependantHeuristic(), o.getContextDependantHeuristic());}
    public String toString() {return "node h:" + nodeHeuristic;}
}
