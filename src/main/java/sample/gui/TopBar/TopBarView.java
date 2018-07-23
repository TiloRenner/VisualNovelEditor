package sample.gui.TopBar;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class TopBarView implements FxmlView<TopBarViewModel>, Initializable{




    @InjectViewModel
    private TopBarViewModel viewModel;

    @FXML
    public Button btn_AddScene, btn_AddSequence, btn_AddSeqPart;

    @FXML
    public Label lbl_SelectedRow,lbl_SelectedEpisode;




    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        lbl_SelectedRow.textProperty().bind(viewModel.selectedRowLabelTextProperty());
        lbl_SelectedEpisode.textProperty().bind(viewModel.selectedEpisodeLabelTextProperty());

    }

    @FXML
    void btn_AddChapter_clicked(final ActionEvent event)
    {
        viewModel.addChapter();
    }

    @FXML
    void btn_AddScene_clicked(final ActionEvent event)
    {

    }

    @FXML
    void btn_AddSequence_clicked(final ActionEvent event)
    {

    }

    @FXML
    void btn_AddSeqPart_clicked(final ActionEvent event)
    {

    }

    @FXML
    void btn_SelectEp1_clicked(final ActionEvent event)
    {
        viewModel.SelectEpisode1();
    }

    @FXML
    void btn_SelectEp2_clicked(final ActionEvent event)
    {
        viewModel.SelectEpisode2();
    }




}
