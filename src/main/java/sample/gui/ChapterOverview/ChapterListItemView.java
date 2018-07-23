package sample.gui.ChapterOverview;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ChapterListItemView implements FxmlView{
    @FXML
    Label lblChapterName;

    @InjectViewModel
    ChapterListItemViewModel viewModel;

    public void initialize()
    {
        lblChapterName.textProperty().bind(viewModel.nameProperty());


    }


}
