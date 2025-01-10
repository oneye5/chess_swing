package ChessEngine;

import ChessEngine.BoardEvaluationHeuristics.BoardHeuristic;
import ChessGame.ChessBoard;

import java.util.*;

public class BeamMoveSearch implements MoveSearchAlgorithm
{
    private final Integer maxDepth;
    private final Integer beamWidth;

    public BeamMoveSearch(Integer maxDepth, Integer beamWidth)
    {
        this.maxDepth = maxDepth;
        this.beamWidth = beamWidth;
    }

    @Override
    public Integer[] findBestMove(ChessBoard currentBoard)
    {
        System.out.println("starting beam search for best move");

        List<TreeNode> currentBeam = new ArrayList<>();
        currentBeam.add(TreeNode.root(currentBoard));

        TreeNode bestLeafNode = null;
        float bestEvaluation = currentBoard.WhitesTurn() ? Float.NEGATIVE_INFINITY : Float.POSITIVE_INFINITY;
        int totalNodesExplored = 0;

        // iterate through depths
        for (int depth = 0; depth <= maxDepth; depth++) {
            List<TreeNode> nextBeam = new ArrayList<>();

            // generate all children of current beam nodes
            for (TreeNode node : currentBeam) {
                List<TreeNode> children = node.getChildren();
                nextBeam.addAll(children);
                totalNodesExplored += children.size();
            }

            // sort nodes based on context dependant heuristics
            nextBeam.sort((a,b)->Float.compare(a.getContextDependantHeuristic(), b.getContextDependantHeuristic()));

            // keep only the k-best nodes
            currentBeam = nextBeam.subList(0, Math.min(beamWidth, nextBeam.size()));

            // update best leaf node if we're at max depth
            if (depth == maxDepth) {
                for (TreeNode node : currentBeam) {
                    if (currentBoard.WhitesTurn()) {
                        if (node.nodeHeuristic > bestEvaluation) {
                            bestEvaluation = node.nodeHeuristic;
                            bestLeafNode = node;
                        }
                    } else {
                        if (node.nodeHeuristic < bestEvaluation) {
                            bestEvaluation = node.nodeHeuristic;
                            bestLeafNode = node;
                        }
                    }
                }
            }
        }

        System.out.println("Total nodes explored = " + totalNodesExplored);
        System.out.println("Final beam size = " + currentBeam.size());
        System.out.println("Best evaluation = " + bestEvaluation);

        return findRootMove(bestLeafNode);
    }

    private Integer[] findRootMove(TreeNode node) {
        TreeNode current = node;
        while (current.parent.parent != null) {
            current = current.parent;
        }
        return current.precedingMove;
    }
}