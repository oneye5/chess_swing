package Controller;

import ChessGame.*;
import Renderer.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * GameController
 * Acts as a controller and a bridge between the game itself, and its visuals.
 *
 * @author Owan Lazic
 */
public class GameController implements MouseListener
{
    private ChessGame game;
    private float squareSize = 40.0f;  // match the square size from VisualChessBoard

    public GameController()
    {
        game = new ChessGame();
        updateVisuals();
        Renderer.INSTANCE.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        // convert screen space coordinates to tile coordinates
        int tileX = (int)(e.getX() / squareSize);
        int tileY = 7 - (int)(e.getY() / squareSize);  // invert Y to match chess board coordinates

        if (tileX >= 0 && tileX < 8 && tileY >= 0 && tileY < 8)
            actionTile(tileX, tileY);
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}

    public void updateVisuals()
    {
        Renderer.INSTANCE.clearRenderables();
        new VisualChessBoard();
        new VisualChessPieces(game.getBoard());
        new VisualAvailableMoves(game);
    }

    public void tick() {Renderer.INSTANCE.repaint();}

    public void actionTile(Integer x, Integer y)
    {
       game.moveSelectedPiece(x, y);
       game.selectPiece(x, y);
       updateVisuals();
    }
}