package tictactoe.player;

import tictactoe.Symbol;
import tictactoe.player.gameplan.Block;
import tictactoe.player.gameplan.GamePlan;
import tictactoe.player.gameplan.TakeCentreMove;
import tictactoe.player.gameplan.TakeVacantCell;
import tictactoe.player.gameplan.cornermoves.TakeEmptyCorner;
import tictactoe.player.gameplan.cornermoves.TakeOppositeCornerToOpponent;
import tictactoe.player.gameplan.cornermoves.TopLeftCorner;
import tictactoe.player.gameplan.forking.ForkFormationFromBottomRowWhenCentreIsVacant;
import tictactoe.player.gameplan.forking.ForkFormationFromTopRowWhenCentreIsVacant;
import tictactoe.player.gameplan.forking.ForkFormationInDiagonalsWhenCentreIsVacant;
import tictactoe.player.gameplan.forking.ForkFormationInVerticalRowsWhenCentreIsVacant;
import tictactoe.player.gameplan.forking.ForkFormationWhenCentreCellIsOccupied;
import tictactoe.player.gameplan.winningmoves.TakeWinningMove;
import tictactoe.prompt.Prompt;

public class AutomatedForkingPlayer extends AutomatedPlayer implements Player {

    public AutomatedForkingPlayer(Symbol symbol, Prompt prompt) {
        super(symbol, prompt);
    }

    protected GamePlan[] orderedGamePlan() {
        return new GamePlan[]{
                new TakeWinningMove(),
                new Block(new TakeWinningMove()),

                new TopLeftCorner(),

                new ForkFormationWhenCentreCellIsOccupied(),
                new ForkFormationFromTopRowWhenCentreIsVacant(),
                new ForkFormationInVerticalRowsWhenCentreIsVacant(),
                new ForkFormationFromBottomRowWhenCentreIsVacant(),
                new ForkFormationInDiagonalsWhenCentreIsVacant(),

                new TakeCentreMove(),
                new TakeOppositeCornerToOpponent(),
                new TakeEmptyCorner(),
                new TakeVacantCell()
        };
    }
}
