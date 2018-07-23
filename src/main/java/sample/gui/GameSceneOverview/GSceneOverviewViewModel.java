package sample.gui.GameSceneOverview;

import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.gui.ChapterOverview.ChapterListItemViewModel;
import sample.model.GameChapter;
import sample.model.GameEpisode;
import sample.model.GameScene;
import sample.model.GameStoryRepository;
import sample.scopes.TableViewMasterScope;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Set;


@Singleton
public class GSceneOverviewViewModel implements ViewModel{

    private final ObjectProperty<GSceneItemViewModel> selectedGameScene = new SimpleObjectProperty<>();

    @Inject
    GameStoryRepository gameStoryRepository;

    @InjectScope
    TableViewMasterScope scope;



    private ObservableList<GSceneItemViewModel> gSceneViewModels = FXCollections.observableArrayList();

    public GSceneOverviewViewModel() {



        //gSceneViewModels.add(new GSceneItemViewModel("test01"));
        //gSceneViewModels.add(new GSceneItemViewModel("test02"));
        //gSceneViewModels.add(new GSceneItemViewModel("test03"));



    }

    public void initialize()
    {

        updateGameScenes();
    }

    private void updateGameScenes()
    {
        final String selectedChapterId = (selectedGameScene.get() == null) ? null : selectedGameScene.get().getId();

        GameChapter chapter = scope.getSelectedGameChapter();
        GameEpisode episode = scope.getSelectedGameEpisode();

        Set<GameScene> allScenes = chapter.findAll();

        //gSceneViewModels.clear();




    }

    public ObservableList<GSceneItemViewModel> gScenesProperty() {
        return gSceneViewModels;
    }



}
