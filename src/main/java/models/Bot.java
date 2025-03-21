package models;


import factory.BotPlayingStrategyFactory;

public class Bot extends Player {
    DifficultyLevel difficultyLevel;
    String playerName;
    Symbol symbol;
    PlayerType playerType;
    private BotPlayingStrategyFactory botPlayingStrategyFactory;
    private strategies.botPlayingStrategies.BotPlayingStrategy botPlayingStrategy;

    public Bot(String playerName,Symbol symbol, DifficultyLevel difficultyLevel) {
        super(playerName, symbol, PlayerType.BOT );
        this.difficultyLevel = difficultyLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory.createBotPlayingStrategy(difficultyLevel);
    }

    @Override
    public Move makeMove(Board board) {
        Move move = botPlayingStrategy.makeMove(board);
        if (move != null) {
            move.setPlayer(this); // Ensure the player is set in the cell
            move.getCell().setPlayer(this);
            System.out.println("Bot's move: " + move.getPlayer().getPlayerName() + "  " + move.getPlayer().getSymbol().getCharacter() + " " + move.getCell().getRow() + " " + move.getCell().getCol());
        }
        return move;
    }
}
