package tictactoe.player;

import tictactoe.Symbol;
import tictactoe.grid.Grid;
import tictactoe.player.gameplan.Block;
import tictactoe.player.gameplan.GamePlan;
import tictactoe.player.gameplan.TakeCenterMove;
import tictactoe.player.gameplan.TakeOppositeCornerMove;
import tictactoe.player.gameplan.TakeVacantCellMove;
import tictactoe.player.gameplan.TopLeftCornerMove;
import tictactoe.player.gameplan.forking.ForkFormationFromBottomRowWhenCenterIsVacant;
import tictactoe.player.gameplan.forking.ForkFormationFromTopRowWhenCenterIsVacant;
import tictactoe.player.gameplan.forking.ForkFormationInVerticalRowsWhenCenterIsVacant;
import tictactoe.player.gameplan.forking.ForkFormationWhenCenterCellIsOccupied;
import tictactoe.player.gameplan.winningmoves.TakeWinningMove;

/**
 * Created by Georgina on 25/05/2015.
 */
public class AutomatedPlayer implements Player {
    private static final int NO_WINNING_MOVE = -1;

    private final Symbol symbol;
    private final GamePlan[] orderedGamePlan;

    public AutomatedPlayer(Symbol symbol) {
        this.symbol = symbol;
        this.orderedGamePlan = orderedGamePlan();
    }

    @Override
    public int nextMoveOn(Grid grid) {
        for (GamePlan gamePlan : orderedGamePlan) {
            int move = gamePlan.execute(grid, symbol);
            if (move != NO_WINNING_MOVE) {
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

                new ForkFormationWhenCenterCellIsOccupied(),
                new ForkFormationFromTopRowWhenCenterIsVacant(),
                new ForkFormationInVerticalRowsWhenCenterIsVacant(),
                new ForkFormationFromBottomRowWhenCenterIsVacant(),

                new Block(new ForkFormationWhenCenterCellIsOccupied()),
                new Block(new ForkFormationFromTopRowWhenCenterIsVacant()),
                new Block(new ForkFormationInVerticalRowsWhenCenterIsVacant()),
                new Block(new ForkFormationFromBottomRowWhenCenterIsVacant()),

                new TakeCenterMove(),
                new TakeOppositeCornerMove(),
                new TakeVacantCellMove()
        };
    }
}
