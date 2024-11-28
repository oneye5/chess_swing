package Renderer;

import Controller.Main;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.util.Arrays;
import java.util.PriorityQueue;

public enum Renderer
{
    INSTANCE;
    // holds a z sorted queue of renderables
    PriorityQueue<Renderable> renderables = new PriorityQueue<Renderable>();
    final MainWindow mainWindow;
    Renderer()
    {
        mainWindow = new MainWindow(600,600, (graphics)->
        {
            for(var r : renderables)
                graphics.drawImage(r.renderableGetImage().getImage(),
                        Math.round(r.renderableGetX()),
                        Math.round(r.renderableGetY()),
                        Math.round(r.renderableGetWidth()),
                        Math.round(r.renderableGetHeight()),
                        null);

        });
    }

    public void addRenderable(Renderable... r)
    {
        renderables.addAll(Arrays.stream(r).toList());
    }

    public void removeRenderable(Renderable... r)
    {
        renderables.removeAll(Arrays.stream(r).toList());
    }

    public void clearRenderables() {renderables.clear();}

    public void addMouseListener(MouseListener listener)
    {
        mainWindow.getPanel().addMouseListener(listener);
    }

    public void repaint()
    {
        mainWindow.getPanel().repaint();
    }

}
