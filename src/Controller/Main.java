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
        var xxx = new Renderable()
        {

            @Override
            public float renderableGetWidth()
            {
                return 40;
            }

            @Override
            public float renderableGetHeight()
            {
                return 40;
            }

            @Override
            public float renderableGetX()
            {
                return 40;
            }

            @Override
            public float renderableGetY()
            {
                return 40;
            }

            @Override
            public float renderableGetZ()
            {
                return 0;
            }

            @Override
            public RenderableImage renderableGetImage()
            {
                return RenderableImage.pawn;
            }
        };
        while(true){}
    }
}