package ChessGame;

import ChessGame.Rules.*;

/**
 * PieceType
 * This class defines all the different pieces, and uses the MoveRule composite to define how the piece should act
 * @author Owan Lazic
 */

public enum PieceType
{
    PAWN(1.0f,
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
    KNIGHT(3.0f,
            new AndRule(
                    new MoveKnightRule(),
                    new CannotLandOnFriendlyRule(),
                    new CheckRule()
            )
    ),
    BISHOP(3.25f,
            new AndRule(

                    new MoveDiagonalRule(8),
                    new CannotLandOnFriendlyRule(),
                    new LineOfSightRule(),
                    new CheckRule()
            )
    ),
    ROOK(5.0f,
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
    QUEEN(9.0f,
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
    KING(9.0f,
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

    PieceType(float pieceValue,MoveRule rootRule)
    {
        this.rootRule = rootRule;
        this.pieceValue = pieceValue;
    }

    private final MoveRule rootRule;
    private float pieceValue;

    public float getPieceValue() {return pieceValue;}
    public void setPieceValue(float PieceValue) {this.pieceValue = PieceValue;}

    public MoveRule getMoveRule() {return rootRule;}
}
