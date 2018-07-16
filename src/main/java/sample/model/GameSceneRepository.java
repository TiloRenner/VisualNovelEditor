package sample.model;

import java.util.Optional;
import java.util.Set;

public interface GameSceneRepository {


    Set<GameScene> findAll();

    Optional<GameScene> findById(String id);

    void save(GameScene gameScene);

    void delete(GameScene gameScene);

}
