package sample.model;

import java.util.Optional;
import java.util.Set;

public interface GameStoryRepository {

    Set<GameChapter> findAll();

    Optional<GameChapter> findById(String id);

    void save(GameChapter gameChapter);

    void delete(GameChapter gameChapter);

    Set<GameEpisode> findAllEpisode();

    Optional<GameEpisode> findEpisodeById(String id);

    void saveEpisode(GameEpisode gameEpisode);

    void deleteEpisode(GameEpisode gameEpisode);




}
