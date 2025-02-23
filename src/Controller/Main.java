package Controller;

import ChessEngine.ChessEngine;

public class Main
{
    public static void main(String[] args)
    {
        System.setProperty("sun.java2d.noddraw", "true");
        var controller = new GameController();
    }
}
