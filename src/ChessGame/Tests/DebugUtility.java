package ChessGame.Tests;

import java.util.List;

public class DebugUtility
{
    public static Boolean possibleMovesContainPosition(List<byte[]> xy, int x, int y)
    {
        for (var move : xy)
            if(move[0] == x && move[1] == y)
                return true;

        return false;
    }
}
