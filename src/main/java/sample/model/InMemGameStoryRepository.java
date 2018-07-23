package sample.model;

import sample.events.ChaptersUpdatedEvent;

import java.util.*;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class InMemGameStoryRepository implements GameStoryRepository {

    private final Set<GameChapter> gameChapters = new HashSet<>();
    private final Set<GameEpisode> gameEpisodes = new HashSet<>();

    @Inject
    private Event<ChaptersUpdatedEvent> chaptersUpdatedEvent;



    @Override
    public Set<GameChapter> findAll() {
        return Collections.unmodifiableSet(gameChapters);
    }

    @Override
    public Optional<GameChapter> findById(String id) {
        return gameChapters.stream().filter(gameChapter -> gameChapter.getId().equals(id)).findFirst();
    }

    @Override
    public void save(GameChapter gameChapter) {
        gameChapters.add(gameChapter);

    }

    @Override
    public void delete(GameChapter gameChapter) {
        gameChapters.remove(gameChapter);
    }

    private void fireUpdateChaptersEvent()
    {
        if(chaptersUpdatedEvent != null)
        {
            chaptersUpdatedEvent.fire(new ChaptersUpdatedEvent());
        }
    }

    @Override
    public Set<GameEpisode> findAllEpisode() {
        return Collections.unmodifiableSet(gameEpisodes);
    }

    @Override
    public Optional<GameEpisode> findEpisodeById(String id) {
        return gameEpisodes.stream().filter(gameEpisode -> gameEpisode.getId().equals(id)).findFirst();
    }

    @Override
    public Optional<GameEpisode> findEpisodeByName(String id) {
        return gameEpisodes.stream().filter(gameEpisode -> gameEpisode.getName().equals(id)).findFirst();
    }

    @Override
    public void saveEpisode(GameEpisode gameEpisode) {
        gameEpisodes.add(gameEpisode);
    }

    @Override
    public void deleteEpisode(GameEpisode gameEpisode) {
        gameEpisodes.remove(gameEpisode);
    }
}
