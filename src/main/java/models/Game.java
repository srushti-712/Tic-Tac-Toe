package models;

import exceptions.InvalidBotException;
import exceptions.InvalidMoveException;
import exceptions.InvalidSymbolsException;
import strategies.winningStrategies.ColWinningStrategy;
import strategies.winningStrategies.DiagonalWinningStrategy;
import strategies.winningStrategies.RowWinningStrategy;
import strategies.winningStrategies.WinningStrategy;

import java.util.*;

public class Game {
    private Board board;
    private List<Player> players;
    private GameStatus status;
    private List<Move> moves = new ArrayList<>();
    private int nextPlayerIndex;
    private Player winner;
    private List<WinningStrategy> winningStrategies;

    public static GameBuilder getBuilder(){
        return new GameBuilder();
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    private Game(int dimension, List<Player> players, List<WinningStrategy> winningStrategies) {
        this.board = new Board(dimension);
        this.players = players;
        this.winningStrategies = winningStrategies;
    }


    public GameStatus getStatus() {
        return status;
    }


    public void makeMove() throws InvalidMoveException {
        // tell the correct player to enter their rol , col
        Player currentPlayer = players.get(nextPlayerIndex);
        System.out.println("It is " + players.get(nextPlayerIndex).getPlayerName() + "'s move: ");
        Move move = currentPlayer.makeMove(board);
        System.out.println("Move player in make Move: " + move.getCell().getPlayer().getPlayerName());
        // if valid make move else stop the game
        Move validMove = this.isValidMove(move);
        System.out.println("hh "+ validMove.getCell().getPlayer().getSymbol().getCharacter());

        // add it to move history
        moves.add(validMove);

        // else set next player
        nextPlayerIndex = (nextPlayerIndex + 1) % players.size();

        // check winner / tie  -> if yes change game status to Tie or Ended
        if(checkWinner(move, board)){
            winner = currentPlayer;
            setStatus(GameStatus.END);
            System.out.println("Player " + currentPlayer.getPlayerName() + " has won!");
        }else if(moves.size() == board.getDimension() * board.getDimension()){
            setStatus(GameStatus.DRAW);
        }

    }

    public boolean checkWinner(Move move, Board board){
        for(WinningStrategy winningStrategy : winningStrategies){
            if(winningStrategy.checkWinner(move, board)){

                return true;
            }
        }
        return false;
    }

    public Move isValidMove(Move move) throws InvalidMoveException {
        // move cell should be in the dimensions
        // cell should be empty
        Cell playerCell = move.getCell();
        System.out.println("cell xx"+ playerCell.getRow() + " "+ playerCell.getCol() + " v "+ playerCell.getPlayer().getPlayerName());
        if(playerCell.getCol() >= board.getDimension()){
            throw new InvalidMoveException("Move should be in the dimensions of the board");
        }

        // if already filled ,move is invalid
        Cell boardCell = board.getBoard().get(playerCell.getRow()).get(playerCell.getCol());
        if(boardCell.getCellStatus() == CellStatus.FILLED){
            throw new InvalidMoveException("Cell is already occupied");
        }
        // mark cell as filled
        boardCell.cellStatus = CellStatus.FILLED;
        // register move in the board -> add the cell values to boards cell
        boardCell.player = playerCell.getPlayer();
        System.out.println("board 0 0 xx"+ boardCell.getRow() + " "+ boardCell.getCol() + boardCell.getPlayer().getPlayerName());
        Move validMove = new Move();
        validMove.setCell(boardCell);
        validMove.setPlayer(playerCell.getPlayer());
        return validMove;

    }

    public void printBoard(){
        this.board.displayBoard();
    }

    public static class GameBuilder{
        private List<Player> players;
        private int dimension;

        public int getDimension() {
            return dimension;
        }

        public List<Player> getPlayers() {
            return players;
        }

        public GameBuilder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public GameBuilder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        private void validatePlayersCount(){
            // players.size = dimension -1
        }

        private void validateBotCount() throws InvalidBotException {
            // max 1 bot allowed in the Game
            int botCount = 0;
            for(Player player : players){
                if(player.getPlayerType() == PlayerType.BOT){botCount++;}
            }
            if(botCount > 1) throw new InvalidBotException("Bot count shouldn't be greater than 1");
        }

        private void validateUniqueSymbols(){
            Set<Symbol> uniqueSymbols = new HashSet<>();
            for(Player player : players){
                uniqueSymbols.add(player.getSymbol());
            }
            if(players.size() != uniqueSymbols.size()){
                throw new InvalidSymbolsException("Unique symbol must be chosen by every Player");
            }
        }

        private void validateGame(){
            validatePlayersCount();
            validateBotCount();
            validateUniqueSymbols();
        }

        public Game build(){
            validateGame();
            List<WinningStrategy> winningStrategies = new ArrayList<>();
            winningStrategies.add(new RowWinningStrategy());
            winningStrategies.add(new ColWinningStrategy());
            winningStrategies.add(new DiagonalWinningStrategy());
            Game game = new Game(dimension, players, winningStrategies);

            return game;
        }
    }



}

