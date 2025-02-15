package Controller;

import ChessEngine.ChessEngine;
import ChessGame.ChessBoard;
import ChessGame.ChessGame;
import Renderer.*;

import static java.lang.System.exit;

public class Main
{
    public static void main(String[] args)
    {
        var controller = new GameController();
        ChessEngine x = new ChessEngine();
        x.findBestMove(controller.game.getBoard());
        exit(1);
        //while(true){controller.tick();}
    }
}
