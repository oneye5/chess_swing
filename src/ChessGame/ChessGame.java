package ChessGame;

public class ChessGame
{
    ChessBoard board;
    private ChessPiece selectedPiece = null;
    private Boolean whitesTurn = true;
    public ChessGame()
    {
        board = ChessBoard.newGame();
    }

    public void selectPiece(int x, int y)
    {
        if(selectedPiece.getPossibleMoves(board).contains(new Integer[]{x, y}))
            return;

        selectedPiece = board.board()[x][y];
    }

    public void selectPiece(ChessPiece piece)
    {
        selectedPiece = piece;
    }

    public void moveSelectedPiece(int desiredX, int desiredY)
    {
        if(!selectedPiece.getPossibleMoves(board).contains(new Integer[]{desiredX, desiredY})) //does not contain the desired move as a valid move
        {
            selectedPiece = null;
            return;
        }

        board = board.newBoardWithMove(selectedPiece.x(), selectedPiece.y(), desiredX, desiredY);
        whitesTurn = !whitesTurn;
        selectedPiece = null;
    }


}
