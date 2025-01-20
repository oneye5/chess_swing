package Controller;

import ChessGame.ChessBoard;
import ChessGame.ChessGame;
import Renderer.*;

public class Main
{
    public static void main(String[] args)
    {
        var controller = new GameController();
        while(true){controller.tick();}
    }
}
