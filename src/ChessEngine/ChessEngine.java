package ChessEngine;

import ChessGame.ChessBoard;

public class ChessEngine
{
    private MoveSearchAlgorithm searchAlgorithm =  new BeamMoveSearch(6,32); // new AStarMoveSearch(4);
    public void setSearchAlgorithm(MoveSearchAlgorithm searchAlgorithm) {this.searchAlgorithm = searchAlgorithm;}
    public Integer[] findBestMove(ChessBoard board) { return searchAlgorithm.findBestMove(board); }
}
