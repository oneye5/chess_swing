package ChessEngine;

import ChessEngine.MoveSearchAlgorithms.*;
import ChessGame.ChessBoard;

/**
 * ChessEngine
 * Acts as an intermediate class for easily calling .findBestMove() whilst hiding the complexity of the actual move search algorithm.
 * It also allows for changing the search algorithm during runtime.
 *
 * @author Owan Lazic
 */
public class ChessEngine
{
    public static Integer depth = 4;
    private MoveSearchAlgorithm searchAlgorithm = new BfsPruningMinimax(()->depth); //new StandaloneMinimax(()->depth); // the selected move search algorithm

    public void setSearchAlgorithm(MoveSearchAlgorithm searchAlgorithm) {this.searchAlgorithm = searchAlgorithm;}
    public byte[] findBestMove(ChessBoard board)
    {
       long startTime = System.currentTimeMillis();

        if(board.getAllMoves().size() == 0)
            return null;

        var move =  searchAlgorithm.findBestMove(board);

        // record and report the elapsed time to execute the move search algorithm
        long finishTime = System.currentTimeMillis();
        long elapsedTime = finishTime - startTime;
        System.out.println("elapsed move search time : " + elapsedTime);

        return move;
    }
}
