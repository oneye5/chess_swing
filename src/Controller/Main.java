package Controller;

import ChessGame.ChessBoard;
import ChessGame.ChessGame;
import Renderer.*;

public class Main
{
    public static void main(String[] args)
    {
        var game = new ChessGame();
        game.selectPiece(1,1);
        game.moveSelectedPiece(1,2);

        var vb = new VisualChessBoard();
        var vp = new VisualChessPieces(game.getBoard());

        while(true){}
    }
}