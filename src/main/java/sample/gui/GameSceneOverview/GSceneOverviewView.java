package sample.gui.GameSceneOverview;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.utils.viewlist.CachedViewModelCellFactory;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.TableViewCellFactory.CachedViewModelColumnCellFactory;

public class GSceneOverviewView implements FxmlView<GSceneOverviewViewModel> {

    @FXML
    ListView listView;

    @InjectViewModel
    GSceneOverviewViewModel viewModel;

    public void initialize()
    {

        listView.setItems(viewModel.gScenesProperty());
        listView.setCellFactory(CachedViewModelCellFactory.createForFxmlView(GSceneItemView.class));

        //column1.setCellValueFactory(CachedViewModelCellFactory.createForFxmlView(GSceneItemView.class));

       // column1.setCellFactory(CachedViewModelCellFactory.createForFxmlView(GSceneItemView2.class));




    }


}
