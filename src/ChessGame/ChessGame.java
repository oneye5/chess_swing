package ChessGame;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * ChessGame
 * This class acts as a layer of abstraction for modifying and reading board/game state.
 * It also provides functionality related to selecting a piece, moving the selected piece, and checking for win/lose conditions
 *
 * @author Owan Lazic
 */
public class ChessGame
{
    private ChessBoard board;
    private ChessPiece selectedPiece = null;
    private Consumer<Boolean> onWIn;

    public ChessGame() {board = ChessBoard.newGame();}

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

    public ChessPiece getSelectedPiece() {return selectedPiece;}

    public void checkForCheckmate() {
        boolean isWhiteTurn = board.WhitesTurn();
        boolean isInCheck = board.isInCheck(isWhiteTurn);

        if (!isInCheck) {
            return; // Not in check, so no need to check for checkmate.
        }

        boolean hasValidMoves = Arrays.stream(board.board())
                .flatMap(Arrays::stream)
                .filter(Objects::nonNull)
                .filter(piece -> piece.isWhitePiece() == isWhiteTurn)
                .anyMatch(piece -> !piece.getPossibleMoves(board).isEmpty());
        if(hasValidMoves)
            return;

        System.out.println("CHECKMATE!!!");
        if (onWIn != null)
            onWIn.accept(isWhiteTurn); // Notify listeners (e.g., UI) of the win state.
    }
}
