package sample.ChapterOverview;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;



public class ChapterOverviewView implements FxmlView<ChapterOverviewViewModel>
{

    //Table vom Type CHapterTableViewModel
    @FXML
    private TableView<ChapterTableViewModel> chapterTable;

    @FXML
    private TableView<EpisodeTableViewModel> episodeTable;

    @InjectViewModel
    ChapterOverviewViewModel viewModel;


    public void initialize()
    {
        chapterTable.setItems(viewModel.getChapters());
        //chapterTable.itemsProperty().bind(viewModel.getChaptersProperty());
        //chapterTable.itemsProperty().bind(Bindings.createObjectBinding(viewModel::getChaptersProperty,viewModel.getTestObservable()));



        viewModel.selectedTableRowProperty().bind(chapterTable.getSelectionModel().selectedItemProperty());

        viewModel.setOnSelect(vm -> chapterTable.getSelectionModel().select(vm));


        episodeTable.setItems(viewModel.getEpisodes());



        viewModel.selectedEpisodeTableRowProperty().bind(episodeTable.getSelectionModel().selectedItemProperty());

        viewModel.setOnEpisodeSelect(vm -> episodeTable.getSelectionModel().select(vm));

        //chapterTable.getSelectionModel().clearSelection();


    }



}
