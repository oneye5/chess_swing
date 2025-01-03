package ChessGame;

import ChessGame.Rules.*;

/**
 * PieceType
 * This class defines all the different pieces, and uses the MoveRule composite to define how the piece should act
 * @author Owan Lazic
 */

public enum PieceType
{
    PAWN(
            new AndRule(
                    new MoveUpOnlyRule(),
                    new CannotLandOnFriendlyRule(),
                    new LineOfSightRule(),
                    new OrRule(
                            new EnPassantRule(),
                            new AndRule(
                                    new MoveVerticalRule(1),
                                    new CannotLandOnEnemyRule()),
                            new PawnTakeRule(),
                            new FirstMoveRule(
                                    new AndRule(
                                            new MoveVerticalRule(2),
                                            new CannotLandOnEnemyRule()
                                    ))),
                    new CheckRule()
            )
    ),
    KNIGHT(
            new AndRule(
                    new MoveKnightRule(),
                    new CannotLandOnFriendlyRule(),
                    new CheckRule()
            )
    ),
    BISHOP(
            new AndRule(

                    new MoveDiagonalRule(8),
                    new CannotLandOnFriendlyRule(),
                    new LineOfSightRule(),
                    new CheckRule()
            )
    ),
    ROOK(
            new AndRule(
                    new CannotLandOnFriendlyRule(),
                    new LineOfSightRule(),
                    new OrRule(
                        new MoveHorizontalRule(8),
                        new MoveVerticalRule(8)
                    ),
                    new CheckRule()
            )
    ),
    QUEEN(
            new AndRule(
                    new CannotLandOnFriendlyRule(),
                    new LineOfSightRule(),
                    new OrRule(
                            new MoveHorizontalRule(8),
                            new MoveVerticalRule(8),
                            new MoveDiagonalRule(8)
            ),
                    new CheckRule()
    )),
    KING(
            new AndRule(
                    new CannotLandOnFriendlyRule(),
                    new OrRule(
                            new MoveHorizontalRule(1),
                            new MoveVerticalRule(1),
                            new MoveDiagonalRule(1),
                            new CastlingRule()
                    ),
                    new LineOfSightRule(),
                    new CheckRule()
            ));

    private final MoveRule rootRule;
    public MoveRule getMoveRule() {return rootRule;}

    PieceType(MoveRule rootRule)
    {
        this.rootRule = rootRule;
    }
}
