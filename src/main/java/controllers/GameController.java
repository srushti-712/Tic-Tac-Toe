package controllers;

import exceptions.InvalidMoveException;
import models.Game;
import models.GameStatus;
import models.Player;

import java.util.List;

public class GameController {

    Game game;

    public Game startGame(int dimension, List<Player> players) {
        Game game = Game.getBuilder()
                .setDimension(dimension)
                .setPlayers(players)
                .build();
        game.setStatus(GameStatus.INPROGRESS);
        return game;
    }


    public void makeMove(Game game) throws InvalidMoveException {
        game.makeMove();
    }


    public void printBoard(Game game) {
        game.printBoard();
    }
}
