package strategies.botPlayingStrategies;

import models.Board;
import models.Cell;
import models.CellStatus;
import models.Move;

import java.util.List;

public class EasyBotPlayingStrategy implements strategies.botPlayingStrategies.BotPlayingStrategy {
    @Override
    public Move makeMove(Board board) {
        Move move = new Move();
        for(List<Cell> row : board.getBoard()){
            for(Cell cell : row){
                if(cell.getCellStatus() == CellStatus.EMPTY){
//                    cell.setCellStatus(CellStatus.FILLED);
                    move.setCell(cell);
                    System.out.println("Botttt move : " +  cell.getRow() + " " + cell.getCol() + cell.getPlayer());
                    return move;
                }
            }
        }
        return null;
    }
}
