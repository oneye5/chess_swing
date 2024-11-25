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
    KNIGHT(
            new AndRule(
                    new MoveKnightRule(),
                    new CannotLandOnFriendlyRule()
            )
    ),
    BISHOP(
            new AndRule(
                    new MoveDiagonalRule(8),
                    new CannotLandOnFriendlyRule(),
                    new LineOfSightRule()
            )
    ),
    ROOK(
            new AndRule(
            new CannotLandOnFriendlyRule(),
            new LineOfSightRule(),
            new OrRule(
                    new MoveHorizontalRule(8),
                    new MoveVerticalRule(8)
            ))),
    QUEEN(
            new AndRule(
            new CannotLandOnFriendlyRule(),
            new LineOfSightRule(),
            new OrRule(
                    new MoveHorizontalRule(8),
                    new MoveVerticalRule(8),
                    new MoveDiagonalRule(8)
            )
    )),
    KING(
            new AndRule(
                    new CannotLandOnFriendlyRule(),
                    new OrRule(
                            new MoveHorizontalRule(1),
                            new MoveVerticalRule(1),
                            new MoveDiagonalRule(1)
                    )
            ));

    private final MoveRule rootRule;
    public MoveRule getMoveRule() {return rootRule;}

    PieceType(MoveRule rootRule)
    {
        this.rootRule = rootRule;
    }
}
