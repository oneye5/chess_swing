package ChessEngine.MoveSearchAlgorithms;

import ChessEngine.TreeNode;
import ChessGame.ChessBoard;

import java.util.ArrayList;
import java.util.List;

public class BeamSearch implements MoveSearchAlgorithm
{
    final int depth;
    final float pruneSize;
    public BeamSearch(Integer depth, float prunedPortion) {this.depth = depth;this.pruneSize = prunedPortion;}

    public byte[] findBestMove(ChessBoard currentBoard)
    {
        var root = TreeNode.root(currentBoard);
        List<TreeNode> currentBreadth;
        List<TreeNode> nextBreadth = new ArrayList<>(root.getChildren());
        int currentDepth = 0;
        System.out.println("Starting beam search");

        while (currentDepth < depth)
        {
            currentDepth++;
            currentBreadth = new ArrayList<>(nextBreadth);
            nextBreadth = new ArrayList<>();

            int whitesTurn = currentBreadth.get(0).board.WhitesTurn() ? 1 : -1;

            System.out.println("Depth: " + currentDepth);


            currentBreadth.sort((a,b)->Float.compare(a.getNodeHeuristic(),b.getNodeHeuristic()) * whitesTurn);

            int beamWidth = Math.round((float)currentBreadth.size() * (1.0f - pruneSize));

            nextBreadth = currentBreadth.subList(0, currentBreadth.size() > beamWidth ? beamWidth - 1 : currentBreadth.size() - 1)
                    .stream()
                    .flatMap(node->node.getChildren().stream())
                    .toList();
        }


        return Minimax.findMove(root,depth).precedingMove;
    }
}
