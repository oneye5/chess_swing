package Renderer;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Renderer
{
    // holds a z sorted queue of renderables
    PriorityQueue<Renderable> renderables = new PriorityQueue<Renderable>();

    public Renderer()
    {
        var x = new MainWindow(600,600, (a,b)->{});
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
