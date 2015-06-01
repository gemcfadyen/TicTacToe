package tictactoe.player;

import tictactoe.Symbol;
import tictactoe.player.gameplan.Block;
import tictactoe.player.gameplan.GamePlan;
import tictactoe.player.gameplan.TakeCentreMove;
import tictactoe.player.gameplan.TakeVacantCellMove;
import tictactoe.player.gameplan.cornermoves.TakeEmptyCornerMove;
import tictactoe.player.gameplan.cornermoves.TakeOppositeCornerToOpponentMove;
import tictactoe.player.gameplan.cornermoves.TopLeftCornerMove;
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

                new TopLeftCornerMove(),

                new ForkFormationWhenCentreCellIsOccupied(),
                new ForkFormationFromTopRowWhenCentreIsVacant(),
                new ForkFormationInVerticalRowsWhenCentreIsVacant(),
                new ForkFormationFromBottomRowWhenCentreIsVacant(),
                new ForkFormationInDiagonalsWhenCentreIsVacant(),

                new TakeCentreMove(),
                new TakeOppositeCornerToOpponentMove(),
                new TakeEmptyCornerMove(),
                new TakeVacantCellMove()
        };
    }
}
