package sample.model;

import java.util.List;

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

    private List<GameScene> GameScenes;






}
