package sample;

import de.saxsys.mvvmfx.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import sample.ChapterOverview.ChapterOverviewView;
import sample.GameSceneOverview.GSceneOverviewView;
import sample.scopes.TableViewMasterScope;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloView implements FxmlView<HelloViewModel> , Initializable{

    @FXML
    private Label helloLabel;

    @FXML
    private BorderPane mainContainer;

    @FXML
    private HBox leftContainer;

    @InjectViewModel
    private HelloViewModel viewModel;

    @InjectContext
    private Context context;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Scope scope = viewModel.getTVMScope();


        ViewTuple chapterOverviewTuple = FluentViewLoader.fxmlView(ChapterOverviewView.class).providedScopes(scope).context(context).load();

        leftContainer.getChildren().add(chapterOverviewTuple.getView());

        ViewTuple sceneOverviewTuple = FluentViewLoader.fxmlView(GSceneOverviewView.class).providedScopes(scope).context(context).load();

        leftContainer.getChildren().add(sceneOverviewTuple.getView());


        ViewTuple<TopBarView,TopBarViewModel> topbarTuple = FluentViewLoader.fxmlView(TopBarView.class).providedScopes(scope).context(context).load();

        mainContainer.setTop(topbarTuple.getView());


        helloLabel.textProperty().bind(viewModel.helloMessageProperty());

    }

    public BorderPane getMainContainer() {
        return mainContainer;
    }
}
