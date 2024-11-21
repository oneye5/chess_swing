package ChessGame;

import ChessGame.Rules.MoveRule;

import java.util.List;

public enum PieceType
{
    x;
    public abstract List<MoveRule> getMoveRules();
}
