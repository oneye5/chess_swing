package ChessGame;

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
        var possibleMoves = selectedPiece.getPossibleMoves(board);
        if(!selectedPiece.PieceType().getMoveRule().isValidMove(board,selectedPiece,desiredX,desiredY)) //does not contain the desired move as a valid move
        {
            selectedPiece = null;
            return;
        }

        board = board.newBoardWithMove(selectedPiece.x(), selectedPiece.y(), desiredX, desiredY);
        selectedPiece = null;
    }

    public ChessPiece getSelectedPiece()
    {
        return selectedPiece;
    }
}
