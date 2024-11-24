package ChessGame;

import ChessGame.Rules.*;

import java.util.Arrays;
import java.util.List;

public enum PieceType
{
    PAWN(
            new AndRule(
                    new MoveUpOnlyRule(),
                    new CannotLandOnFriendlyRule(),
                    new LineOfSightRule(),
                    new OrRule(
                            new AndRule(
                                    new MoveVerticalRule(1),
                                    new CannotLandOnEnemyRule()),
                            new PawnTakeRule(),
                            new FirstMoveRule(
                                    new AndRule(
                                            new MoveVerticalRule(2),
                                            new CannotLandOnEnemyRule()
                                    ))))),
    KNIGHT(null),
    BISHOP(null),
    ROOK(null),
    QUEEN(null),
    KING(null);

    private final MoveRule rootRule;
    public MoveRule getMoveRule() {return rootRule;}

    PieceType(MoveRule rootRule)
    {
        this.rootRule = rootRule;
    }
}
