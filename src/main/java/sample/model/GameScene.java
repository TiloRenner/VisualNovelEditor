package sample.model;

import java.util.*;

public class GameScene extends Identity
{

    private String name;
    long id;
    Set<GameSceneSequence> gameSceneSequences = new HashSet<>();
    GameSceneSequence startSequence;

    GameScene incomingNode; //Probably Not needed


    public  GameScene(String name)
    {
        this.name = name;
    }

    public void addSequence(GameSceneSequence sequence)
    {
        gameSceneSequences.add(sequence);
    }

    public void addSequence(GameSceneSequence sequence, boolean isStartingSequence)
    {
        gameSceneSequences.add(sequence);
        if(isStartingSequence)
        {
            startSequence = sequence;
        }
    }

    public Set<GameSceneSequence> findAll()
    {
        return Collections.unmodifiableSet(gameSceneSequences);
        //return gameChapters;
    }


    public Optional<GameSceneSequence> findById(String id) {
        return gameSceneSequences.stream().filter(gameSceneSeqs -> gameSceneSeqs.getId().equals(id)).findFirst();
    }



}
