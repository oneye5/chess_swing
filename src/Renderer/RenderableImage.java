package Renderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * RenderableImage,
 * used to explicitly define the assets available for use by 'Renderables'
 * and to provide a layer of abstraction for getting the 'Image' file itself, and to provide efficient caching of said files.
 *
 * @see Renderable
 * @author Owan Lazic
 */

public enum RenderableImage
{
    DARK_TILE    {public String getFileName(){return  "darkTile.png";}},
    LIGHT_TILE   {public String getFileName(){return  "lightTile.png";}},
    WHITE_PAWN   {public String getFileName(){return  "w_Pawn.png";}},
    BLACK_PAWN   {public String getFileName(){return  "b_Pawn.png";}},
    WHITE_BISHOP {public String getFileName(){return  "w_Bishop.png";}},
    BLACK_BISHOP {public String getFileName(){return  "b_Bishop.png";}},
    WHITE_ROOK   {public String getFileName(){return  "w_Rook.png";}},
    BLACK_ROOK   {public String getFileName(){return  "b_Rook.png";}},
    WHITE_KNIGHT {public String getFileName(){return  "w_Knight.png";}},
    BLACK_KNIGHT {public String getFileName(){return  "b_Knight.png";}},
    WHITE_QUEEN  {public String getFileName(){return  "w_Queen.png";}},
    BLACK_QUEEN  {public String getFileName(){return  "b_Queen.png";}},
    WHITE_KING   {public String getFileName(){return  "w_King.png";}},
    BLACK_KING   {public String getFileName(){return  "b_King.png";}},
    BOARD_MARKER {public String getFileName(){return  "marker.png";}},
    ENGINE_MARKER{public String getFileName(){return  "eMarker.png";}};

    HashMap<String,Image> imageCache = new HashMap<>();

    public abstract String getFileName();

    public String getFilePath()
    {
        var projectPath = Paths.get("").toAbsolutePath().normalize() + "/assets/";
        return projectPath + getFileName();
    }

    public Image getImage()
    {
        return imageCache.computeIfAbsent(getFilePath(),(path)->
        {
            try { return ImageIO.read(new File(getFilePath())); }
            catch(IOException e) { throw new RuntimeException(e); }
        });
    }
}

