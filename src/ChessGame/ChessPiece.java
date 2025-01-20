package ChessGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ChessPiece
 * This class represents every piece, however behaviour is different based on what pieceType is assigned
 *
 * @param PieceType what kind of piece is this object
 * @param x position on the board
 * @param y position on the board
 * @param isWhitePiece does this piece belong to white
 * @param hasMoved has this piece been moved from its original position
 * @author Owan Lazic
 */
public record ChessPiece(PieceType PieceType, byte x, byte y, boolean isWhitePiece, boolean hasMoved)
{
    /**
     * @param board
     * @return a list of arrays containing the x and y position on the board for the piece to move
     */
    public List<byte[]> getPossibleMoves(ChessBoard board)
    {
        return MoveFinder.get(this, board);
    }
}
class MoveFinder
{
    static { precomputeMoves(); }
    private static Map<PieceType,List<byte[]>> precomputedMoves;


    public static List<byte[]> get(ChessPiece p, ChessBoard board)
    {
        List<byte[]> possibleMoves = new ArrayList<>();

        if (precomputedMoves.containsKey(p.PieceType()))
        {
            possibleMoves = precomputedMoves.get(p.PieceType());
            possibleMoves = possibleMoves.stream() // make relative to piece position and filter out, out of bounds positions
                    .map(m->new byte[]{(byte) (m[0]+p.x()), (byte) (m[1]+p.y())})
                    .filter(m->m[0] >= 0 && m[1] >= 0)
                    .filter(m->m[0] < 8 && m[1] < 8)
                    .toList();
        }
        else
        {
            for (byte x = 0; x < 8; x++)
                for (byte y = 0; y < 8; y++)
                    possibleMoves.add(new byte[]{x, y});
        }

        return possibleMoves.stream()
                    .filter(move->p.PieceType().getMoveRule().isValidMove(board,p,move[0],move[1]))
                    .toList();
    }

    private static void precomputeMoves()
    {
        precomputedMoves = new HashMap<>();

        precomputedMoves.put(
                PieceType.PAWN,
                List.of(
                        new byte[]{0,1},
                        new byte[]{0,-1},
                        new byte[]{1,1},
                        new byte[]{-1,1},
                        new byte[]{0,2},
                        new byte[]{0,-2}
                )
        );

        precomputedMoves.put
                (
                        PieceType.KING,
                        List.of(
                                new byte[]{0,1},
                                new byte[]{0,-1},
                                new byte[]{1,0},
                                new byte[]{-1,0},
                                new byte[]{1,1},
                                new byte[]{1,-1},
                                new byte[]{-1,1},
                                new byte[]{-1,-1},
                                new byte[]{3,0},
                                new byte[]{-2,0}
                        )
                );

        precomputedMoves.put
                (
                        PieceType.BISHOP,
                        List.of(
                                new byte[]{1,1},
                                new byte[]{1,-1},
                                new byte[]{-1,1},
                                new byte[]{-1,-1},
                                new byte[]{2,2},
                                new byte[]{2,-2},
                                new byte[]{-2,2},
                                new byte[]{-2,-2},
                                new byte[]{3,3},
                                new byte[]{3,-3},
                                new byte[]{-3,3},
                                new byte[]{-3,-3},
                                new byte[]{4,4},
                                new byte[]{4,-4},
                                new byte[]{-4,4},
                                new byte[]{-4,-4},
                                new byte[]{5,5},
                                new byte[]{5,-5},
                                new byte[]{-5,5},
                                new byte[]{-5,-5},
                                new byte[]{6,6},
                                new byte[]{6,-6},
                                new byte[]{-6,6},
                                new byte[]{-6,-6},
                                new byte[]{7,7},
                                new byte[]{7,-7},
                                new byte[]{-7,7},
                                new byte[]{-7,-7},
                                new byte[]{8,8},
                                new byte[]{8,-8},
                                new byte[]{-8,8},
                                new byte[]{-8,-8}
                        )
                );

        precomputedMoves.put(
                PieceType.KNIGHT,
                List.of(
                        new byte[]{1,2},
                        new byte[]{-1,2},

                        new byte[]{2,1},
                        new byte[]{2,-1},

                        new byte[]{1,-2},
                        new byte[]{-1,-2},

                        new byte[]{-2,1},
                        new byte[]{-2,-1}

                )
        );

        precomputedMoves.put(
                PieceType.ROOK,
                List.of(
                        new byte[]{1,0},
                        new byte[]{0,1},
                        new byte[]{-1,0},
                        new byte[]{0,-1},
                        new byte[]{2,0},
                        new byte[]{0,2},
                        new byte[]{-2,0},
                        new byte[]{0,-2},
                        new byte[]{3,0},
                        new byte[]{0,3},
                        new byte[]{-3,0},
                        new byte[]{0,-3},
                        new byte[]{4,0},
                        new byte[]{0,4},
                        new byte[]{-4,0},
                        new byte[]{0,-4},
                        new byte[]{5,0},
                        new byte[]{0,5},
                        new byte[]{-5,0},
                        new byte[]{0,-5},
                        new byte[]{6,0},
                        new byte[]{0,6},
                        new byte[]{-6,0},
                        new byte[]{0,-6},
                        new byte[]{7,0},
                        new byte[]{0,7},
                        new byte[]{-7,0},
                        new byte[]{0,-7},
                        new byte[]{8,0},
                        new byte[]{0,8},
                        new byte[]{-8,0},
                        new byte[]{0,-8}
                )
        );
    }
}