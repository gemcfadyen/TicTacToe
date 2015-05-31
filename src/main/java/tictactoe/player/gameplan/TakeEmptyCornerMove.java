package tictactoe.player.gameplan;

import com.google.common.base.Predicate;
import tictactoe.Symbol;
import tictactoe.grid.Grid;

import java.util.Map;

public class TakeEmptyCornerMove extends CornerMove implements GamePlan {
    @Override
    public int execute(Grid grid, Symbol symbol) {
        Map<Integer, Integer> vacantCorners = applyToCorners(getVacantCorners(grid));
        return has(vacantCorners)
                ? first(vacantCorners)
                : NO_SUGGESTED_MOVE;
    }

    private Predicate<Map.Entry<Integer, Integer>> getVacantCorners(final Grid grid) {
        return corner -> {
            if (grid.isEmptyAt(corner.getKey())) {
                return true;
            }
            return false;
        };
    }
}

