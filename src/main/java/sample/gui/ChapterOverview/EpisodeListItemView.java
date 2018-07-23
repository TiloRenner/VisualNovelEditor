package sample.gui.ChapterOverview;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EpisodeListItemView implements FxmlView {

    @FXML
    Label lblEpisodeName;

    @InjectViewModel
    EpisodeListItemViewModel viewModel;

    public void initialize()
    {
        lblEpisodeName.textProperty().bind(viewModel.nameProperty());


    }


}
