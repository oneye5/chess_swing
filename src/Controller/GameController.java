package Controller;

import ChessEngine.BoardEvaluationHeuristics.BoardHeuristic;
import ChessEngine.ChessEngine;
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
    private Boolean hasReportedWin = false;
    public ChessGame game;
    public static float squareSize = 100.0f;

    public GameController()
    {
        game = new ChessGame();
        updateVisuals();
        Renderer.INSTANCE.addMouseListener(this);

        // assign behaviours to the UI
        UserInterface.INSTANCE.setOnUIChangedRunnable(this::tickUI);

        UserInterface.INSTANCE.setOnNewGamePressed(()->{
            game = new ChessGame();
            hasReportedWin = false;
            updateVisuals();
        });

        UserInterface.INSTANCE.setOnFindMovePressed(()->{
            updateVisuals();
            new VisualEngineEvaluation(game);
        });

        UserInterface.INSTANCE.setOnMakeMovePressed(()->{
            var move = new ChessEngine().findBestMove(game.getBoard());
            if(move == null)
                return;

            game.selectPiece(move[0],move[1]);
            game.moveSelectedPiece(move[2],move[3]);
            updateVisuals();
        });
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        // convert screen space coordinates to tile coordinates
        int tileX = (int)(e.getX() / squareSize);
        int tileY = 7 - (int)(e.getY() / squareSize);  // invert Y to match chess board coordinates

        if (tileX >= 0 && tileX < 8 && tileY >= 0 && tileY < 8)
            actionTile(tileX, tileY);
        else
            new VisualEngineEvaluation(game);
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

        if(!hasReportedWin && game.getBoard().getAllMoves().size() == 0)
            UserInterface.INSTANCE.onWin(!game.getBoard().WhitesTurn());
    }

    public void tick() {Renderer.INSTANCE.repaint();}

    public void actionTile(int x, int y)
    {
       game.moveSelectedPiece((byte)x, (byte)y);
       game.selectPiece((byte)x, (byte)y);
       updateVisuals();

        System.out.println( "current heuristic:" + BoardHeuristic.PERFORMANT.getHeuristic(game.getBoard()));
    }

    private void tickUI() {ChessEngine.depth = UserInterface.INSTANCE.getDepth();}
}