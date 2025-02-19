package Renderer;

import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Renderer,
 * this class is a singleton that is responsible for the rendering and management of 'Renderables' as well as construction of the 'MainWindow'
 *
 * @see Renderable
 * @see MainWindow
 * @author Owan Lazic
 */

public enum Renderer
{
    INSTANCE;

    // Renderables sorted by z-position, smallest to biggest
    PriorityQueue<Renderable> renderables = new PriorityQueue<Renderable>();
    final MainWindow mainWindow;

    Renderer()
    {
        mainWindow = new MainWindow(1100,900, graphics ->
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

    public void addRenderable(Renderable... r) {renderables.addAll(Arrays.stream(r).toList());}

    public void removeRenderable(Renderable... r) {renderables.removeAll(Arrays.stream(r).toList());}

    public void clearRenderables() {renderables.clear();}

    public void addMouseListener(MouseListener listener) {mainWindow.getPanel().addMouseListener(listener);}

    public void repaint() {mainWindow.getPanel().repaint();}
}
