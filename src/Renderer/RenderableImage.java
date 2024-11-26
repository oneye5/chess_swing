package Renderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public enum RenderableImage
{
    darkTile    {public String getFileName(){return  "darkTile.png";}},
    lightTile   {public String getFileName(){return  "lightTile.png";}},
    pawn        {public String getFileName(){return  "chap.png";}};

    public abstract String getFileName();
    public String getFilePath()
    {
        var projectPath = Paths.get("").toAbsolutePath().normalize() + "/assets/";
        return projectPath + getFileName();
    }

    HashMap<String,Image> imageCache = new HashMap<>();
    public Image getImage()
    {
        return imageCache.computeIfAbsent(getFilePath(),(path)->
        {
            try { return ImageIO.read(new File(getFilePath())); }
            catch(IOException e) { throw new RuntimeException(e); }
        });
    }
}
