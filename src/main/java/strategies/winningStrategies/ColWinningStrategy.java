package strategies.winningStrategies;

import models.Board;
import models.Move;
import models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class ColWinningStrategy implements WinningStrategy {

    Map<Integer, Map<Symbol, Integer>> colMaps = new HashMap<>();

    @Override
    public boolean checkWinner(Move move, Board board) {
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        if(!colMaps.containsKey(col)) {
            colMaps.put(col, new HashMap<>());
        }

        Map<Symbol, Integer> currColMap = colMaps.get(col);
        if(!currColMap.containsKey(symbol)) {
            currColMap.put(symbol, 0);
        }
        currColMap.put(symbol, currColMap.get(symbol) + 1);
        return currColMap.get(symbol) == board.getDimension();
    }
}
