package strategies.winningStrategies;

import models.Board;
import models.Move;
import models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy {
    Map<Integer, Map<Symbol, Integer>> rowMaps = new HashMap<>();

    @Override
    public boolean checkWinner(Move move, Board board) {
        int row = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();

        // if this is the first entry in that row
        if(!rowMaps.containsKey(row)) {
            rowMaps.put(row, new HashMap<>());
        }

        // first entry of this sympbol in the row
        if(!rowMaps.get(row).containsKey(symbol)) {
            rowMaps.get(row).put(symbol, 0);
        }
        Map<Symbol, Integer> currRowMap = rowMaps.get(row);

        // count ++ in that row for that symbol
        currRowMap.put(symbol, currRowMap.get(symbol) + 1);
        System.out.println("Winnnerrr :: "+ (currRowMap.get(symbol) == board.getDimension()));
        // actual winner check
        return currRowMap.get(symbol) == board.getDimension();
    }
}

