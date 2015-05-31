package tictactoe.player;

import tictactoe.Symbol;
import tictactoe.grid.Grid;
import tictactoe.player.gameplan.Block;
import tictactoe.player.gameplan.GamePlan;
import tictactoe.player.gameplan.TakeCentreMove;
import tictactoe.player.gameplan.TakeVacantCellMove;
import tictactoe.player.gameplan.cornermoves.TakeOppositeCornerMove;
import tictactoe.player.gameplan.cornermoves.TopLeftCornerMove;
import tictactoe.player.gameplan.forking.ForkFormationFromBottomRowWhenCentreIsVacant;
import tictactoe.player.gameplan.forking.ForkFormationFromTopRowWhenCentreIsVacant;
import tictactoe.player.gameplan.forking.ForkFormationInDiagonalsWhenCentreIsVacant;
import tictactoe.player.gameplan.forking.ForkFormationInVerticalRowsWhenCentreIsVacant;
import tictactoe.player.gameplan.forking.ForkFormationWhenCentreCellIsOccupied;
import tictactoe.player.gameplan.winningmoves.TakeWinningMove;
import tictactoe.prompt.Prompt;

public class AutomatedPlayer implements Player {
    private static final int NO_WINNING_MOVE = -1;

    private final Symbol symbol;
    private final GamePlan[] orderedGamePlan;
    private final Prompt prompt;

    public AutomatedPlayer(Symbol symbol, Prompt prompt) {
        this.symbol = symbol;
        this.prompt = prompt;
        this.orderedGamePlan = orderedGamePlan();
    }

    @Override
    public int nextMoveOn(Grid grid) {
        for (GamePlan gamePlan : orderedGamePlan) {
            int move = gamePlan.execute(grid, symbol);

            if (move != NO_WINNING_MOVE) {
                prompt.display(move);
                return move;
            }
        }

        return NO_WINNING_MOVE;
    }

    @Override
    public Symbol getSymbol() {
        return symbol;
    }

    private GamePlan[] orderedGamePlan() {
        return new GamePlan[]{
                new TakeWinningMove(),
                new Block(new TakeWinningMove()),

                new TopLeftCornerMove(),

                new ForkFormationWhenCentreCellIsOccupied(),
                new ForkFormationFromTopRowWhenCentreIsVacant(),
                new ForkFormationInVerticalRowsWhenCentreIsVacant(),
                new ForkFormationFromBottomRowWhenCentreIsVacant(),
                new ForkFormationInDiagonalsWhenCentreIsVacant(),

                new Block(new ForkFormationWhenCentreCellIsOccupied()),
                new Block(new ForkFormationFromTopRowWhenCentreIsVacant()),
                new Block(new ForkFormationInVerticalRowsWhenCentreIsVacant()),
                new Block(new ForkFormationFromBottomRowWhenCentreIsVacant()),
                new Block(new ForkFormationInDiagonalsWhenCentreIsVacant()),

                new TakeCentreMove(),
                new TakeOppositeCornerMove(),
                new TakeVacantCellMove()
        };
    }
}
