package ChessEngine;

import ChessEngine.MoveSearchAlgorithms.AStarMoveSearch;
import ChessEngine.MoveSearchAlgorithms.BeamSearch;
import ChessEngine.MoveSearchAlgorithms.BfsBruteForce;
import ChessEngine.MoveSearchAlgorithms.MoveSearchAlgorithm;
import ChessGame.ChessBoard;

public class ChessEngine
{
    private MoveSearchAlgorithm searchAlgorithm = new BfsBruteForce(3);
         //new AStarMoveSearch(10,2496*10);
           //new BeamSearch(5,0.75f);
    public void setSearchAlgorithm(MoveSearchAlgorithm searchAlgorithm) {this.searchAlgorithm = searchAlgorithm;}
    public byte[] findBestMove(ChessBoard board) { return searchAlgorithm.findBestMove(board); }
}
