package models;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private int dimension;
    private List<List<Cell>> board;

    public int getDimension() {
        return dimension;
    }

    public List<List<Cell>> getBoard() {
        return board;
    }

    public Board(int dimension) {
        this.dimension = dimension;
        this.board = new ArrayList<>();
        for(int i=0; i<dimension; i++) {
            board.add(new ArrayList<>());
            for(int j=0; j<dimension; j++) {
                board.get(i).add(new Cell(i, j));
            }
        }
    }

    public void displayBoard(){
        for(List<Cell> row : board){
            for(Cell cell : row){
                if(cell.cellStatus == CellStatus.EMPTY)
                    System.out.print("|   |");
                else{
                    System.out.print("| " +cell.getPlayer().getSymbol().getCharacter()+" |");
                }
            }
            System.out.println();
        }
    }


}
