package sample.model;

import java.util.*;

public class GameChapter extends Identity
{
    long id;
    public String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Set<GameScene> gameScenes = new HashSet<>();


    public void addGameScene(GameScene gameScene)
    {
        gameScenes.add(gameScene);

    }

    public Set<GameScene> findAll()
    {
        return Collections.unmodifiableSet(gameScenes);
        //return gameChapters;
    }


    public Optional<GameScene> findById(String id) {
        return gameScenes.stream().filter(gameScene -> gameScene.getId().equals(id)).findFirst();
    }






}
