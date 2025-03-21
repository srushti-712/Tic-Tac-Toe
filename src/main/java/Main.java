import controllers.GameController;
import models.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        GameController gameController = new GameController();
        int dimension = 3;
        List<Player> players = new ArrayList<>();
        players.add(new Player("Srushti", new Symbol('X')));
        players.add(new Bot("Companion", new Symbol('O'), DifficultyLevel.EASY));

        Game game = gameController.startGame(dimension, players);
//        gameController.printBoard(game);
        while(game.getStatus() == GameStatus.INPROGRESS){
            // Print the board
            gameController.printBoard(game);
            // let the player make move
            gameController.makeMove(game);
        }


    }
}
