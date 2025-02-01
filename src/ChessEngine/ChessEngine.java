package ChessEngine;

import ChessEngine.MoveSearchAlgorithms.AStarMoveSearch;
import ChessEngine.MoveSearchAlgorithms.BeamSearch;
import ChessEngine.MoveSearchAlgorithms.BfsBruteForce;
import ChessEngine.MoveSearchAlgorithms.MoveSearchAlgorithm;
import ChessGame.ChessBoard;

public class ChessEngine
{
    public static Integer depth = 3;
    private MoveSearchAlgorithm searchAlgorithm = new BfsBruteForce(()->depth);
         //new AStarMoveSearch(10,2496*10);
           //new BeamSearch(5,0.75f);
    public void setSearchAlgorithm(MoveSearchAlgorithm searchAlgorithm) {this.searchAlgorithm = searchAlgorithm;}
    public byte[] findBestMove(ChessBoard board)
    {
        if(board.getAllMoves().size() == 0)
            return null;

        return searchAlgorithm.findBestMove(board);
    }
}
