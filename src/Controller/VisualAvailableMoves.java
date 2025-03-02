package Controller;

import ChessGame.*;
import Renderer.*;

/**
 * VisualAvailableMoves
 * Takes a chess game and uses it to add renderables showing the user where the selected piece may move.
 *
 * @author Owan Lazic
 */

public class VisualAvailableMoves
{
    VisualAvailableMoves(ChessGame g)
    {
        if(g.getSelectedPiece() == null)
            return;

        var moves = g.getSelectedPiece().getPossibleMoves(g.getBoard());

        for (var move : moves)
        {
            new Renderable()
            {
                @Override public float renderableGetWidth() {return GameController.squareSize;}
                @Override public float renderableGetHeight() {return GameController.squareSize;}
                @Override public float renderableGetX() {return move[0] * GameController.squareSize;}
                @Override public float renderableGetY() {return (7-move[1]) * GameController.squareSize;}
                @Override public float renderableGetZ() {return 2.0f;}
                @Override public RenderableImage renderableGetImage() {return RenderableImage.BOARD_MARKER;}
            };
        }
    }
}
