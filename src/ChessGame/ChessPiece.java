package ChessGame;

public abstract class ChessPiece
{
    public Integer getX()
    {
        return 0;
    }
    public Integer getY()
    {
        return 0;
    }
    public Boolean isWhitePiece()
    {
        return true;
    }
    public Boolean hasMoved()
    {
        return false;
    }
}
