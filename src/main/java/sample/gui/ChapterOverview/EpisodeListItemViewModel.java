package sample.gui.ChapterOverview;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableStringValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import sample.model.GameEpisode;

public class EpisodeTableViewModel implements ViewModel
{

    private GameEpisode gameEpisode;

    private Button button;

    private ReadOnlyStringWrapper name = new ReadOnlyStringWrapper();

    public EpisodeTableViewModel(GameEpisode gameEpisode) {
        this.gameEpisode = gameEpisode;
        name.set(this.gameEpisode.getName());

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

    public ObservableStringValue nameProperty(){
        return name;
    }



}
