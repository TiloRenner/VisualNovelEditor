package sample.model;

import java.util.*;

public class GameEpisode extends Identity {
    public int startNodeId;
    public String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<GameChapter> gameChapters = new HashSet<>();;


    public void addGameChapter(GameChapter gameChapter)
    {
        gameChapters.add(gameChapter);
    }

    public Set<GameChapter> findAll()
    {
        return Collections.unmodifiableSet(gameChapters);
        //return gameChapters;
    }

    public Optional<GameChapter> findById(String id) {
        return gameChapters.stream().filter(gameChapter -> gameChapter.getId().equals(id)).findFirst();
    }


    //Requires at least a startNode
    public static class GameEpisodeBuilder{




    }



}
