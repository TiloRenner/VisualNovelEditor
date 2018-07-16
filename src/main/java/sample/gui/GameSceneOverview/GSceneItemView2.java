package sample.GameSceneOverview;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GSceneItemView2 implements FxmlView<GSceneItemViewModel> {

    @FXML
    Label lbl_name;

    @InjectViewModel
    GSceneItemViewModel viewModel;

    public void initialize()
    {
        lbl_name.textProperty().bind(viewModel.nameProperty());


    }





}
