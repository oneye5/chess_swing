package Controller;

import ChessGame.ChessBoard;
import ChessGame.PieceType;
import Renderer.Renderable;
import Renderer.RenderableImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * VisualChessPieces
 * Responsible for adding visual representations of every piece on the board
 *
 * @author Owan Lazic
 */

public class VisualChessPieces
{
    public VisualChessPieces(ChessBoard board)
    {
        Arrays.stream(board.board()).flatMap(Arrays::stream).filter(Objects::nonNull).forEach(piece->
        {
            new Renderable()
            {
                @Override public float renderableGetWidth() {return GameController.squareSize;}
                @Override public float renderableGetHeight() {return GameController.squareSize;}
                @Override public float renderableGetX() {return piece.x() * GameController.squareSize;}
                @Override public float renderableGetY() {return (7 - piece.y()) * GameController.squareSize;}
                @Override public float renderableGetZ() {return 0;}
                @Override public RenderableImage renderableGetImage()
                {
                    switch (piece.PieceType())
                    {
                        case PAWN:
                            if (piece.isWhitePiece())
                                return RenderableImage.WHITE_PAWN;
                            else
                                return RenderableImage.BLACK_PAWN;

                        case BISHOP:
                            if(piece.isWhitePiece())
                               return RenderableImage.WHITE_BISHOP;
                            else
                                return RenderableImage.BLACK_BISHOP;

                        case ROOK:
                            if(piece.isWhitePiece())
                                return RenderableImage.WHITE_ROOK;
                            else
                                return RenderableImage.BLACK_ROOK;

                        case KNIGHT:
                            if(piece.isWhitePiece())
                                return RenderableImage.WHITE_KNIGHT;
                            else
                                return RenderableImage.BLACK_KNIGHT;
                        case QUEEN:
                            if (piece.isWhitePiece())
                                return RenderableImage.WHITE_QUEEN;
                            else
                                return RenderableImage.BLACK_QUEEN;

                        case KING:
                            if(piece.isWhitePiece())
                                return RenderableImage.WHITE_KING;
                            else
                                return RenderableImage.BLACK_KING;

                        default:
                            throw new IllegalArgumentException("Unknown piece type: " + piece.PieceType());
                    }
                }
            };

        });
    }
}
