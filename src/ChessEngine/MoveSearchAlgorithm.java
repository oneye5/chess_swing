package ChessEngine;

import ChessGame.ChessBoard;

public interface MoveSearchAlgorithm
{
    public Integer[] findBestMove(ChessBoard currentBoard);
}
