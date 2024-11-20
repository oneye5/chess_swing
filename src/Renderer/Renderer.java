package Renderer;

import java.awt.image.ImageObserver;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Renderer
{
    // holds a z sorted queue of renderables
    PriorityQueue<Renderable> renderables = new PriorityQueue<Renderable>();

    public Renderer()
    {
        var window = new MainWindow(600,600, (graphics)->
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
}
