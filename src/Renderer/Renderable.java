package Renderer;

/**
 * Renderable,
 * Used to describe an Image for use by the Renderer.
 *
 * @see Renderer
 * @author Owan Lazic
 */

public abstract class Renderable implements Comparable<Renderable>
{
    public abstract float renderableGetWidth();

    public abstract float renderableGetHeight();

    public abstract float renderableGetX();

    public abstract float renderableGetY();

    public abstract float renderableGetZ();

    public abstract RenderableImage renderableGetImage();

    // For use in collections with z sorting
    @Override
    public int compareTo(Renderable other) { return Float.compare(this.renderableGetZ(), other.renderableGetZ()); }

    protected Renderable() {Renderer.INSTANCE.addRenderable(this);}

}
