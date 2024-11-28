package Controller;

import ChessGame.*;
import Renderer.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameController implements MouseListener
{
    private ChessGame game;
    private float squareSize = 40.0f;  // Match the square size from VisualChessBoard

    public GameController()
    {
        game = new ChessGame();
        updateVisuals();

        // Add the mouse listener to the renderer's main window
        // Note: You'll need to modify MainWindow to expose a way to add a mouse listener
        Renderer.INSTANCE.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        // Convert screen coordinates to tile coordinates
        int tileX = (int)(e.getX() / squareSize);
        int tileY = 7 - (int)(e.getY() / squareSize);  // Invert Y to match chess board coordinates

        // Ensure the click is within the chess board
        if (tileX >= 0 && tileX < 8 && tileY >= 0 && tileY < 8)
            actionTile(tileX, tileY);
    }

    // Implement other MouseListener methods as no-ops
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

    public void tick()
    {
        Renderer.INSTANCE.repaint();
    }

    public void actionTile(Integer x, Integer y)
    {
       game.moveSelectedPiece(x, y);
       game.selectPiece(x, y);

       updateVisuals();
    }
}