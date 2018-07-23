package sample.model.factories;


import sample.model.GameChapter;
import sample.model.GameScene;
import sample.model.GameSceneSequence;

import javax.inject.Singleton;

@Singleton
public class GameChapterFactory {

    public GameChapter createGameChapter()
    {
        GameChapter gameChapter = new GameChapter();
        return gameChapter;

    }

    public GameScene createGameScene()
    {
        GameScene gameScene = new GameScene("test");
        return gameScene;
    }

    public GameSceneSequence createGameSceneSequence()
    {
        GameSceneSequence gameSceneGameSceneSequence = new GameSceneSequence();
        return gameSceneGameSceneSequence;
    }






}
