package sample.GameSceneOverview;

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

    @FXML
    TableView table;

    @FXML
    TableColumn column1, column2 ;



    @InjectViewModel
    GSceneOverviewViewModel viewModel;

    public void initialize()
    {



        listView.setItems(viewModel.gScenesProperty());
        listView.setCellFactory(CachedViewModelCellFactory.createForFxmlView(GSceneItemView.class));


        table.setItems(viewModel.gScenesProperty());
        //column1.setCellValueFactory(new PropertyValueFactory("name"));
        column1.setCellValueFactory(new PropertyValueFactory("vm"));
        column1.setCellFactory(CachedViewModelColumnCellFactory.createForFxmlView(GSceneItemView.class));
        column2.setCellValueFactory(new PropertyValueFactory("vm"));
        column2.setCellFactory(CachedViewModelColumnCellFactory.createForFxmlView(GSceneItemView2.class));

        //column1.setCellValueFactory(CachedViewModelCellFactory.createForFxmlView(GSceneItemView.class));

       // column1.setCellFactory(CachedViewModelCellFactory.createForFxmlView(GSceneItemView2.class));




    }


}
