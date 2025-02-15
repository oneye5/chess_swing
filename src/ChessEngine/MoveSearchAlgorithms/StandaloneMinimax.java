package ChessEngine.MoveSearchAlgorithms;

import ChessEngine.TreeNode;
import ChessGame.ChessBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class StandaloneMinimax implements MoveSearchAlgorithm
{
    private final Supplier<Integer> depthSupplier; // supplier so we can easily update this during runtime behind the scenes
    public StandaloneMinimax(Supplier<Integer> depth) {depthSupplier = depth;}
    @Override
    public byte[] findBestMove(ChessBoard currentBoard)
    {
        System.out.println("Starting standalone minimax move search");
        int depth = depthSupplier.get();
        var root = TreeNode.root(currentBoard);

        return Minimax.parallelFindMove(root,depth,false).precedingMove;
    }
}
