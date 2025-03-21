package models;

import java.util.Scanner;

public class Player {
    String playerName;
    Symbol symbol;
    PlayerType playerType;
    Scanner sc = new Scanner(System.in);

    public Player(String playerName, Symbol symbol) {
        this.playerName = playerName;
        this.symbol = symbol;
        this.playerType = PlayerType.HUMAN;
    }

    public Player(String playerName, Symbol symbol, PlayerType playerType) {
        this.playerName = playerName;
        this.symbol = symbol;
        this.playerType = playerType;
    }

    public Move makeMove(Board board) {
        System.out.println("Enter desired row and column number separated by space: ");
        int row = sc.nextInt();
        int col = sc.nextInt();
        System.out.println("row : "+ row + "col : " + col);
        Cell cell = new Cell(row, col, this);
        Move move = new Move();
        move.setPlayer(this);
        move.setCell(cell);
        return move;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Symbol getSymbol() {
        return symbol;
    }


}


