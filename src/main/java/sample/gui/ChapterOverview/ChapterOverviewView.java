package sample.gui.ChapterOverview;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.viewlist.CachedViewModelCellFactory;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;



public class ChapterOverviewView implements FxmlView<ChapterOverviewViewModel>
{

    //Table vom Type CHapterTableViewModel
    //@FXML
    //private TableView<ChapterListItemViewModel> chapterTable;

    //@FXML
    //private TableView<EpisodeListItemViewModel> episodeTable;

    @FXML
    private ListView episodeListView, chapterListView;

    @InjectViewModel
    ChapterOverviewViewModel viewModel;


    public void initialize()
    {
        //episodeListView.getSelectionModel().select(viewModel.selectedEpisodeTableRowProperty());



        //chapterTable.setItems(viewModel.getChapters());
        chapterListView.setItems(viewModel.getChapters());
        chapterListView.setCellFactory((CachedViewModelCellFactory.createForFxmlView(ChapterListItemView.class)));
        viewModel.selectedTableRowProperty().bind(chapterListView.getSelectionModel().selectedItemProperty());
        viewModel.setOnSelect(vm -> chapterListView.getSelectionModel().select(vm));

        //viewModel.selectedTableRowProperty().bind(chapterTable.getSelectionModel().selectedItemProperty());

        //viewModel.setOnSelect(vm -> chapterListView.getSelectionModel().select(vm));


        episodeListView.setItems(viewModel.getEpisodes());
        episodeListView.setCellFactory(CachedViewModelCellFactory.createForFxmlView(EpisodeListItemView.class));
        viewModel.selectedEpisodeTableRowProperty().bind(episodeListView.getSelectionModel().selectedItemProperty());
        viewModel.setOnEpisodeSelect(vm -> episodeListView.getSelectionModel().select(vm));

        //episodeListView.getSelectionModel().select(viewModel.selectedEpisodeTableRowProperty());
        //episodeListView.getSelectionModel().selectFirst();
        //chapterListView.getSelectionModel().selectFirst();



    }



}
