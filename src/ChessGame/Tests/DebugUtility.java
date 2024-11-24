package ChessGame.Tests;

import java.util.List;

public class DebugUtility
{
    public static void printPositions(List<Integer[]> xy)
    {
        for (var move : xy)
        {
            System.out.print(move[0].toString() + ",");
            System.out.println(move[1].toString());
        }
    }
    public  static String toStringPositions(List<Integer[]> xy)
    {
        String out = "";
        for (var move : xy)
        {
            out += move[0].toString() + ",";
            out += move[1].toString() + "\n";
        }
        return out;
    }

    public static Boolean possibleMovesContainPosition(List<Integer[]> xy, int x, int y)
    {
        for (var move : xy)
            if(move[0] == x && move[1] == y)
                return true;

        return false;
    }
}
