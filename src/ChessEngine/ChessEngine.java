package ChessEngine;

import ChessEngine.MoveSearchAlgorithms.*;
import ChessGame.ChessBoard;

import java.util.Timer;

public class ChessEngine
{
    public static Integer depth = 3;
    private MoveSearchAlgorithm searchAlgorithm = new BfsWPruning(()->depth);
         //new AStarMoveSearch(10,2496*10);
           //new BeamSearch(5,0.75f);
    public void setSearchAlgorithm(MoveSearchAlgorithm searchAlgorithm) {this.searchAlgorithm = searchAlgorithm;}
    public byte[] findBestMove(ChessBoard board)
    {
       long startTime = System.currentTimeMillis();

        if(board.getAllMoves().size() == 0)
            return null;

        var move =  searchAlgorithm.findBestMove(board);

        long finishTime = System.currentTimeMillis();
        long elapsedTime = finishTime - startTime;
        System.out.println("elapsed move search time : " + elapsedTime);
        return move;
    }
}
