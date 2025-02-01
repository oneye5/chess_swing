package ChessGame;

import ChessGame.Rules.CheckRule;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ChessGame.Rules.*;
/**
* ChessBoard
* This class is responsible for storing the board state, it contains helpful utility methods to do with changing the current board state
* or get a pieces available moves.
*
* @param board 0,0 (a1) is bottom left, 7,7 (h8) is top right
* @param prevBoard contains the previous state the board was in, is null when no moves have been played
* @param WhitesTurn is true when it is whites turn, methods such as newBoardWithMove negate the current value
*
* @author Owan Lazic
*/
public record ChessBoard (ChessPiece[][] board,ChessBoard prevBoard, Boolean WhitesTurn)
{
    public ChessBoard modifyTurn(boolean white) {return new ChessBoard(deepCloneBoard(),this,white);}
    public List<byte[]> getAllMoves()
    {
        return getTurnAppropriatePieces()
                .parallelStream()
                .flatMap(p->
                        p.getPossibleMoves(this)
                                .stream()
                                .map(to->new byte[]{p.x(),p.y(),to[0],to[1]})
                )
                .toList(); // convert from {toX,toY} to {fromX,fromY,toX,toY};);
    }
    public List<ChessPiece> getTurnAppropriatePieces()
    {
        return getAllPieces().stream()
                .filter(p->p.isWhitePiece() == WhitesTurn)
                .collect(Collectors.toList());
    }
    public List<ChessPiece> getAllPieces()
    {
        return Arrays.stream(board())
                .flatMap(Arrays::stream)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
    public boolean isInCheck(Boolean isWhite)
    {
        // Find the king's position
        ChessPiece king = findKing(isWhite);

        // Check if any opponent pieces can attack the king's position
        return Arrays.stream(board())
                .flatMap(Arrays::stream)
                .filter(Objects::nonNull)
                .filter(piece -> piece.isWhitePiece() != isWhite)
                .anyMatch(piece -> canPieceAttackPosition(piece, king.x(), king.y()));
    }

    public boolean canPieceAttackPosition(ChessPiece piece, byte targetX, byte targetY)
    {
        // this method checks if a piece can move to the target position
        // without considering check rules
        MoveRule moveRuleWithoutCheck = piece.PieceType().getMoveRule();

        // remove the CheckRule from the move validation
        if (moveRuleWithoutCheck instanceof AndRule andRule)
        {
            List<MoveRule> rulesWithoutCheck = andRule.rules.stream()
                    .filter(rule -> !(rule instanceof CheckRule))
                    .toList();

            moveRuleWithoutCheck = new AndRule(rulesWithoutCheck.toArray(new MoveRule[0]));
        }

        return moveRuleWithoutCheck.isValidMove(this, piece, targetX, targetY);
    }

    public ChessPiece findKing(Boolean isWhite)
    {
        return Arrays.stream(board())
                .flatMap(Arrays::stream)
                .filter(Objects::nonNull)
                .filter(x -> x.PieceType() == PieceType.KING && x.isWhitePiece() == isWhite)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No king found for " + (isWhite ? "white" : "black")));
    }

    public static ChessBoard newBoardWithPieces(ChessPiece... pieces)
    {
        ChessPiece[][] board = new ChessPiece[8][8];
        Arrays.stream(pieces).forEachOrdered(piece -> board[piece.x()][piece.y()] = piece);
        return new ChessBoard(board,null, true);
    }
    public static ChessBoard newGame()
    {
        return newBoardWithPieces(newPiecesWithDefaultPosition());
    }
    public static ChessPiece[] newPiecesWithDefaultPosition() {
        List<ChessPiece> pieces = new ArrayList<ChessPiece>();

        // Set up pawns (ranks 2 and 7)
        for (byte x = 0; x < 8; x++) {  // Fixed: should be < 8, not < 7
            pieces.add(new ChessPiece(PieceType.PAWN, x, (byte)1, true, false));   // White pawns
            pieces.add(new ChessPiece(PieceType.PAWN, x, (byte)6, false, false));  // Black pawns
        }

        // White pieces (rank 1)
        pieces.add(new ChessPiece(PieceType.ROOK,   (byte)0, (byte)0, true, false));
        pieces.add(new ChessPiece(PieceType.KNIGHT, (byte)1, (byte)0, true, false));
        pieces.add(new ChessPiece(PieceType.BISHOP, (byte)2, (byte)0, true, false));
        pieces.add(new ChessPiece(PieceType.QUEEN,  (byte)3, (byte)0, true, false));
        pieces.add(new ChessPiece(PieceType.KING,   (byte)4, (byte)0, true, false));
        pieces.add(new ChessPiece(PieceType.BISHOP, (byte)5, (byte)0, true, false));
        pieces.add(new ChessPiece(PieceType.KNIGHT, (byte)6, (byte)0, true, false));
        pieces.add(new ChessPiece(PieceType.ROOK,   (byte)7, (byte)0, true, false));

        // Black pieces (rank 8 - appears as y=7 in zero-based coordinates)
        pieces.add(new ChessPiece(PieceType.ROOK,   (byte)0, (byte)7, false, false));
        pieces.add(new ChessPiece(PieceType.KNIGHT, (byte)1, (byte)7, false, false));
        pieces.add(new ChessPiece(PieceType.BISHOP, (byte)2, (byte)7, false, false));
        pieces.add(new ChessPiece(PieceType.QUEEN,  (byte)3, (byte)7, false, false));
        pieces.add(new ChessPiece(PieceType.KING,   (byte)4, (byte)7, false, false));
        pieces.add(new ChessPiece(PieceType.BISHOP, (byte)5, (byte)7, false, false));
        pieces.add(new ChessPiece(PieceType.KNIGHT, (byte)6, (byte)7, false, false));
        pieces.add(new ChessPiece(PieceType.ROOK,   (byte)7, (byte)7, false, false));

        return pieces.toArray(new ChessPiece[0]);
    }
    public ChessBoard newBoardWithMove(byte[] m) {return newBoardWithMove(m[0],m[1],m[2],m[3]);}
    public ChessBoard newBoardWithMove(byte pieceX, byte pieceY, byte desiredX, byte desiredY)
    {
        var newBoard = this.deepCloneBoard();
        var piece = board[pieceX][pieceY];
        if(piece == null)
            throw new Error("Piece is null 'newBoardWithMove' at x y " + pieceX + ", " + pieceY);

        newBoard[pieceX][pieceY] = null;
        newBoard[desiredX][desiredY] = new ChessPiece(piece.PieceType(), desiredX, desiredY, piece.isWhitePiece(), true);
        Boolean whitesTurn = !newBoard[desiredX][desiredY].isWhitePiece();
        var out = new ChessBoard(newBoard,this,whitesTurn);

        // Code for special checks ================================
        // this includes: en passant, castling and promoting pawns to Queens
        int deltaX = desiredX - pieceX;
        if(piece.PieceType() == PieceType.KING && Math.abs(deltaX) > 1) // if abs (deltaX > 1) for the king occurs then it must be castling
        {
            // need to move the appropriate rook
            byte rookPos;
            byte rookDesired;

            if(deltaX > 1) //short castle right
            {
                rookPos = 7;
                rookDesired = (byte) (desiredX - 1);
            }
            else // long castle left
            {
                rookPos = 0;
                rookDesired = (byte) (desiredX + 1);
            }

            var rook = out.board()[rookPos][desiredY];
            out.board()[rookPos][desiredY] = null;
            out.board()[rookDesired][desiredY] = new ChessPiece(rook.PieceType(), rookDesired, desiredY, piece.isWhitePiece(), true);
            out = new ChessBoard(out.deepCloneBoard(),this,whitesTurn);
        }

        // en passant
        if (piece.PieceType() == PieceType.PAWN && deltaX != 0 && board[desiredX][desiredY] == null)
        {
            if (whitesTurn)
                out.board()[desiredX][desiredY+1] = null;
            else
                out.board()[desiredX][desiredY-1] = null;
            out = new ChessBoard(out.deepCloneBoard(),this, whitesTurn);
        }

        // pawn promotion
        if(out.board()[desiredX][desiredY].PieceType() == PieceType.PAWN)
            if (desiredY == 0 || desiredY == 7)
                out.board()[desiredX][desiredY] = new ChessPiece(PieceType.QUEEN, desiredX, desiredY, out.board()[desiredX][desiredY].isWhitePiece(), true);

        return out;
    }

    //finds the {fromX, fromY, toX, toY} based on the difference between the current and previous board.
    public Integer[] getPrecedingMove()
    {
        if(prevBoard() == null)
        {
            System.out.println("ChessGame.Chessboard.prevBoard() is null, cannot find preceding move, returning null.");
            return null;
        }

        Integer[] move = IntStream.range(0, 8)
                .boxed()
                .flatMap(x -> IntStream.range(0, 8).mapToObj(y -> new int[]{x, y}))
                .collect(Collectors.teeing(
                        Collectors.filtering(
                                pos -> board()[pos[0]][pos[1]] != null &&
                                        !board()[pos[0]][pos[1]].equals(prevBoard().board()[pos[0]][pos[1]]),
                                Collectors.toList()
                        ),
                        Collectors.filtering(
                                pos -> board()[pos[0]][pos[1]] == null && prevBoard().board()[pos[0]][pos[1]] != null,
                                Collectors.toList()
                        ),
                        (toList, fromList) -> {
                            if (toList.size() != 1 || fromList.size() != 1)  // could not determine preceding move
                                return null;
                            int[] to = toList.get(0);
                            int[] from = fromList.get(0);
                            return new Integer[]{from[0], from[1], to[0], to[1]};
                        }
                ));

        return move;
    }

    public ChessPiece[][] deepCloneBoard()
    {
        var out = new ChessPiece[8][8];
        for (int i = 0; i < 8; i++)
            System.arraycopy(board[i], 0, out[i], 0, 8);
        return out;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for(int x = 7; x >= 0; x--)
        {
            sb.append("\n");
            for (int y = 0; y < 8; y++)
                if (board[y][x] != null)
                    sb.append(board[y][x].PieceType().toString().charAt(0));
                else
                    sb.append("_");
        }
        return sb.toString();
    }
}