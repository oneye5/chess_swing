package Controller;

import ChessGame.ChessBoard;
import Renderer.*;

public class Main
{
    public static void main(String[] args)
    {
        var board = ChessBoard.newGame();
        System.out.println(board.toString());
        var xx = new VisualChessBoard();
        var xxx = new VisualChessPieces(board);
        while(true){}
    }
}