package sample.gui.ChapterOverview;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import sample.model.GameEpisode;

public class EpisodeTableViewModel
{

    private GameEpisode gameEpisode;

    private Button button;

    public EpisodeTableViewModel(GameEpisode gameEpisode) {
        this.gameEpisode = gameEpisode;
        button = new Button();
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

    }

    public String getName()
    {
        return gameEpisode.getName();
    }

    public String getId()
    {
        return gameEpisode.getId();
    }

    public Button getCustomViewA() {
        return button;
    }



}
