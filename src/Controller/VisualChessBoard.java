package Controller;

import Renderer.Renderable;
import Renderer.RenderableImage;

import java.util.ArrayList;
import java.util.List;

public class VisualChessBoard
{
    private Float squareSize = 40.0f;
    private List<Renderable> squares = new ArrayList<>();
    public VisualChessBoard()
    {
        RenderableImage img = RenderableImage.lightTile;
        for(int x = 0; x < 8; x++)
        {
            img = img == RenderableImage.lightTile ? RenderableImage.darkTile : RenderableImage.lightTile;
            for (int y = 0; y < 8; y++)
            {
                int finalX = x * squareSize.intValue();
                int finalY = y * squareSize.intValue();
                RenderableImage finalImg = img;
                squares.add(
                        new Renderable()
                        {
                            @Override
                            public float renderableGetWidth()
                            {
                                return squareSize;
                            }

                            @Override
                            public float renderableGetHeight()
                            {
                                return squareSize;
                            }

                            @Override
                            public float renderableGetX()
                            {
                                return finalX;
                            }

                            @Override
                            public float renderableGetY()
                            {
                                return finalY;
                            }

                            @Override
                            public float renderableGetZ()
                            {
                                return -1;
                            }

                            @Override
                            public RenderableImage renderableGetImage()
                            {
                                return finalImg;
                            }
                        }
                );
                img = img == RenderableImage.lightTile ? RenderableImage.darkTile : RenderableImage.lightTile;
            }
        }
    }
    public void setSquareSize(Float size) {this.squareSize = size;}
}
