package factory;

import models.DifficultyLevel;
import strategies.botPlayingStrategies.BotPlayingStrategy;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy createBotPlayingStrategy(DifficultyLevel difficultyLevel) {
        if(difficultyLevel == DifficultyLevel.EASY) {
            return (strategies.botPlayingStrategies.BotPlayingStrategy) new strategies.botPlayingStrategies.EasyBotPlayingStrategy();
        }
        if(difficultyLevel == DifficultyLevel.HARD) {
            return (strategies.botPlayingStrategies.BotPlayingStrategy) new strategies.botPlayingStrategies.HardBotPlayingStrategy();
        }
        if(difficultyLevel == DifficultyLevel.MEDIUM) {
            return (strategies.botPlayingStrategies.BotPlayingStrategy) new strategies.botPlayingStrategies.MediumBotPlayingStrategy();
        }
        return null;
    }
}
