package ChessGame;

import java.util.Arrays;
import java.util.Objects;

public class ChessGame
{
    private ChessBoard board;
    private ChessPiece selectedPiece = null;

    public ChessGame()
    {
        board = ChessBoard.newGame();
    }

    public ChessBoard getBoard() {return board;}

    public void selectPiece(int x, int y)
    {
        if(board.board()[x][y] == null)
        {
            selectedPiece = null;
            return;
        }

        if(selectedPiece != null && selectedPiece.getPossibleMoves(board).contains(new Integer[]{x, y}))
            return;


        if (board.board()[x][y].isWhitePiece() == board.WhitesTurn())
            selectedPiece = board.board()[x][y];
        else
            selectedPiece = null;
    }

    public void selectPiece(ChessPiece piece)
    {
        if (piece.isWhitePiece() == board.WhitesTurn())
            selectedPiece = piece;
    }

    public void moveSelectedPiece(int desiredX, int desiredY)
    {
        if (selectedPiece == null)
            return;
        checkForCheckmate();

        //var possibleMoves = selectedPiece.getPossibleMoves(board);
        if(!selectedPiece.PieceType().getMoveRule().isValidMove(board,selectedPiece,desiredX,desiredY)) //does not contain the desired move as a valid move
        {
            selectedPiece = null;
            return;
        }

        board = board.newBoardWithMove(selectedPiece.x(), selectedPiece.y(), desiredX, desiredY);
        selectedPiece = null;

        System.out.println("ChessGame.MoveSelectedPiece()\n"+ this.board.toString());
    }

    public ChessPiece getSelectedPiece()
    {
        return selectedPiece;
    }
    public void checkForCheckmate()
    {
        Boolean result = false;
        if(getBoard().isInCheck(true) && getBoard().WhitesTurn())
        {
            result = Arrays.stream(board.board())
                        .flatMap(Arrays::stream)
                        .filter(Objects::nonNull)
                        .filter(ChessPiece::isWhitePiece)
                        .filter(piece->piece.getPossibleMoves(getBoard()).size()!=0)
                        .toList().size() == 0;

        }
        else if (getBoard().isInCheck(false) && !getBoard().WhitesTurn())
        {
            result = Arrays.stream(board.board())
                    .flatMap(Arrays::stream)
                    .filter(Objects::nonNull)
                    .filter(x->!x.isWhitePiece())
                    .filter(piece->piece.getPossibleMoves(getBoard()).size()!=0)
                    .toList().size() == 0;
        }

        if(result)
            System.out.println("CHECKMATE !!!!");
    }
}
