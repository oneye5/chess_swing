package ChessEngine;

import ChessEngine.MoveSearchAlgorithms.AStarMoveSearch;
import ChessEngine.MoveSearchAlgorithms.BfsBruteForce;
import ChessEngine.MoveSearchAlgorithms.MoveSearchAlgorithm;
import ChessGame.ChessBoard;

public class ChessEngine
{
    private MoveSearchAlgorithm searchAlgorithm = new BfsBruteForce(4);  //new AStarMoveSearch(10,2496*10);
    public void setSearchAlgorithm(MoveSearchAlgorithm searchAlgorithm) {this.searchAlgorithm = searchAlgorithm;}
    public Integer[] findBestMove(ChessBoard board) { return searchAlgorithm.findBestMove(board); }
}
