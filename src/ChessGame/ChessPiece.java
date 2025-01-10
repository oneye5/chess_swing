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
public record ChessPiece(PieceType PieceType, int x, int y, boolean isWhitePiece, boolean hasMoved)
{
    /**
     * @param board
     * @return a list of arrays containing the x and y position on the board for the piece to move
     */
    public List<Integer[]> getPossibleMoves(ChessBoard board)
    {
        return MoveFinder.get(this, board);
    }
}
class MoveFinder
{
    static { precomputeMoves(); }
    private static Map<PieceType,List<Integer[]>> precomputedMoves;


    public static List<Integer[]> get(ChessPiece p, ChessBoard board)
    {
        List<Integer[]> possibleMoves = new ArrayList<>();

        if (precomputedMoves.containsKey(p.PieceType()))
        {
            possibleMoves = precomputedMoves.get(p.PieceType());
            possibleMoves = possibleMoves.parallelStream() // make relative to piece position and filter out, out of bounds positions
                    .map(m->new Integer[]{m[0]+p.x(),m[1]+p.y()})
                    .filter(m->m[0] >= 0 && m[1] >= 0)
                    .filter(m->m[0] < 8 && m[1] < 8)
                    .toList();
        }
        else
        {
            for (int x = 0; x < 8; x++)
                for (int y = 0; y < 8; y++)
                    possibleMoves.add(new Integer[]{x, y});
        }

        return possibleMoves.parallelStream()
                    .filter(move->p.PieceType().getMoveRule().isValidMove(board,p,move[0],move[1]))
                    .toList();
    }

    private static void precomputeMoves()
    {
        precomputedMoves = new HashMap<>();

        precomputedMoves.put(
                PieceType.PAWN,
                List.of(
                        new Integer[]{0,1},
                        new Integer[]{0,-1},
                        new Integer[]{1,1},
                        new Integer[]{-1,1},
                        new Integer[]{0,2},
                        new Integer[]{0,-2}
                )
        );

        precomputedMoves.put
                (
                        PieceType.KING,
                        List.of(
                                new Integer[]{0,1},
                                new Integer[]{0,-1},
                                new Integer[]{1,0},
                                new Integer[]{-1,0},
                                new Integer[]{1,1},
                                new Integer[]{1,-1},
                                new Integer[]{-1,1},
                                new Integer[]{-1,-1},
                                new Integer[]{3,0},
                                new Integer[]{-2,0}
                        )
                );

        precomputedMoves.put
                (
                        PieceType.BISHOP,
                        List.of(
                                new Integer[]{1,1},
                                new Integer[]{1,-1},
                                new Integer[]{-1,1},
                                new Integer[]{-1,-1},
                                new Integer[]{2,2},
                                new Integer[]{2,-2},
                                new Integer[]{-2,2},
                                new Integer[]{-2,-2},
                                new Integer[]{3,3},
                                new Integer[]{3,-3},
                                new Integer[]{-3,3},
                                new Integer[]{-3,-3},
                                new Integer[]{4,4},
                                new Integer[]{4,-4},
                                new Integer[]{-4,4},
                                new Integer[]{-4,-4},
                                new Integer[]{5,5},
                                new Integer[]{5,-5},
                                new Integer[]{-5,5},
                                new Integer[]{-5,-5},
                                new Integer[]{6,6},
                                new Integer[]{6,-6},
                                new Integer[]{-6,6},
                                new Integer[]{-6,-6},
                                new Integer[]{7,7},
                                new Integer[]{7,-7},
                                new Integer[]{-7,7},
                                new Integer[]{-7,-7},
                                new Integer[]{8,8},
                                new Integer[]{8,-8},
                                new Integer[]{-8,8},
                                new Integer[]{-8,-8}
                        )
                );

        precomputedMoves.put(
                PieceType.KNIGHT,
                List.of(
                        new Integer[]{1,2},
                        new Integer[]{-1,2},

                        new Integer[]{2,1},
                        new Integer[]{2,-1},

                        new Integer[]{1,-2},
                        new Integer[]{-1,-2},

                        new Integer[]{-2,1},
                        new Integer[]{-2,-1}

                )
        );

        precomputedMoves.put(
                PieceType.ROOK,
                List.of(
                        new Integer[]{1,0},
                        new Integer[]{0,1},
                        new Integer[]{-1,0},
                        new Integer[]{0,-1},
                        new Integer[]{2,0},
                        new Integer[]{0,2},
                        new Integer[]{-2,0},
                        new Integer[]{0,-2},
                        new Integer[]{3,0},
                        new Integer[]{0,3},
                        new Integer[]{-3,0},
                        new Integer[]{0,-3},
                        new Integer[]{4,0},
                        new Integer[]{0,4},
                        new Integer[]{-4,0},
                        new Integer[]{0,-4},
                        new Integer[]{5,0},
                        new Integer[]{0,5},
                        new Integer[]{-5,0},
                        new Integer[]{0,-5},
                        new Integer[]{6,0},
                        new Integer[]{0,6},
                        new Integer[]{-6,0},
                        new Integer[]{0,-6},
                        new Integer[]{7,0},
                        new Integer[]{0,7},
                        new Integer[]{-7,0},
                        new Integer[]{0,-7},
                        new Integer[]{8,0},
                        new Integer[]{0,8},
                        new Integer[]{-8,0},
                        new Integer[]{0,-8}
                )
        );
    }
}