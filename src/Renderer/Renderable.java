package Renderer;


public abstract class Renderable implements Comparable<Renderable>
{
    // width and height of the image to be drawn
    public abstract float renderableGetWidth();

    public abstract float renderableGetHeight();

    // x and y represent positions on screen, z represents what should be rendered on top
    public abstract float renderableGetX();

    public abstract float renderableGetY();

    public abstract float renderableGetZ();

    // returns the desired image to draw
    public abstract RenderableImage renderableGetImage();

    // for use in collections with z sorting
    @Override
    public int compareTo(Renderable other) { return Float.compare(this.renderableGetZ(), other.renderableGetZ()); }

    Renderable()
    {

    }

}
