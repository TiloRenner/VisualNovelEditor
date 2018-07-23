package sample;

import de.saxsys.mvvmfx.*;
import de.saxsys.mvvmfx.cdi.MvvmfxCdiApplication;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.gui.MainContainer.MainContainerView;
import sample.gui.MainContainer.MainContainerViewModel;
import sample.model.GameChapter;
import sample.model.GameEpisode;
import sample.model.GameScene;
import sample.model.GameStoryRepository;

import javax.inject.Inject;



public class Main extends MvvmfxCdiApplication implements ViewModel {

    BorderPane borderPane;

    @Inject
    GameStoryRepository repository;

    @InjectContext
    private Context context;





    @Override
    public void startMvvmfx(Stage primaryStage) throws Exception {



        repository.save(createFakeChapters("Chapter 1"));
        repository.save(createFakeChapters("Chapter 2"));

        repository.saveEpisode(createFakeEpisode("Episode 01"));
        repository.saveEpisode(createFakeEpisode("Episode 02"));

        primaryStage.setTitle("Hallo Welt");
        ViewTuple<MainContainerView,MainContainerViewModel> viewTuple = FluentViewLoader.fxmlView(MainContainerView.class).context(context).load();
        Parent root = viewTuple.getView();
        primaryStage.setScene(new Scene(root));







        borderPane = (BorderPane)root.lookup("#mainContainer");


        //borderPane.getChildren().add(chapterOverviewTuple.getView());


        primaryStage.show();

    }

    private GameChapter createFakeChapters(String name)
    {
        GameChapter chapter = new GameChapter();
        chapter.name = name;

        chapter.addGameScene(createFakeGameScene(name+ " Scene1"));
        chapter.addGameScene(createFakeGameScene(name+ " Scene2"));
        chapter.addGameScene(createFakeGameScene(name+ " Scene3"));

        return chapter;
    }

    private GameEpisode createFakeEpisode(String name)
    {
        GameEpisode episode = new GameEpisode();
        episode.name = name;

        GameChapter chapter1 = new GameChapter();
        chapter1.name = "Chap1" + name;

        GameChapter chapter2 = new GameChapter();
        chapter2.name = "Chap2" + name;

        episode.addGameChapter(chapter1);
        episode.addGameChapter(chapter2);

        return episode;

    }

    private GameScene createFakeGameScene(String name)
    {
        GameScene gameScene = new GameScene(name);
        return gameScene;
    }









    public static void main(String[] args) {
        launch(args);
    }
}
