package sample.gui.MainContainer;

import de.saxsys.mvvmfx.*;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sample.SequencePane.ClippingPane;
import sample.SequencePane.SceneGestures;
import sample.SequencePane.SequencePane;
import sample.SequencePane.NodeGestures;
import sample.gui.ChapterOverview.ChapterOverviewView;
import sample.gui.GameSceneOverview.GSceneOverviewView;
import sample.gui.TopBar.TopBarView;
import sample.gui.TopBar.TopBarViewModel;

import java.net.URL;
import java.util.ResourceBundle;

public class MainContainerView implements FxmlView<MainContainerViewModel> , Initializable{

    @FXML
    private Label helloLabel, lblSeqHeight,lblSeqWidth,lblBoundsParent,lblBoundsLocal;

    @FXML
    private BorderPane mainContainer;

    @FXML
    private HBox leftContainer;

    @InjectViewModel
    private MainContainerViewModel viewModel;

    @InjectContext
    private Context context;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Scope scope = viewModel.getTVMScope();



        ViewTuple sceneOverviewTuple = FluentViewLoader.fxmlView(GSceneOverviewView.class).providedScopes(scope).context(context).load();
        ViewTuple chapterOverviewTuple = FluentViewLoader.fxmlView(ChapterOverviewView.class).providedScopes(scope).context(context).load();
        leftContainer.getChildren().add(chapterOverviewTuple.getView());
        leftContainer.getChildren().add(sceneOverviewTuple.getView());



        ViewTuple<TopBarView,TopBarViewModel> topbarTuple = FluentViewLoader.fxmlView(TopBarView.class).providedScopes(scope).context(context).load();
        mainContainer.setTop(topbarTuple.getView());


        helloLabel.textProperty().bind(viewModel.helloMessageProperty());

        lblSeqHeight.textProperty().bind(Bindings.createObjectBinding(()-> {return "SeqPaneH: " + viewModel.seqPaneHeight().getValue().toString();},viewModel.seqPaneHeight()));
        lblSeqWidth.textProperty().bind(Bindings.createObjectBinding(()-> {return "SeqPaneW: " + viewModel.seqPaneWidth().getValue().toString();},viewModel.seqPaneWidth()));


        final Pane stackpane = new ClippingPane();



        final SequencePane seqPane = new SequencePane();

        viewModel.seqPaneWidth().bind(seqPane.widthProperty());
        viewModel.seqPaneHeight().bind(seqPane.heightProperty());
        //lblBoundsParent.textProperty().bind(Bindings.createObjectBinding(()-> {return "BoundParent: " + seqPane.getBoundsInParent().toString();},seqPane.getBoundsInParent()));
        //seqPane.getBoundsInParent().;

        seqPane.setBackground(new Background(new BackgroundFill(Color.BEIGE,new CornerRadii(2),new Insets(2))));



        Button myButton = new Button();
        myButton.setText("CenterButton");

        seqPane.getChildren().add(myButton);

        stackpane.setBackground(new Background(new BackgroundFill(Color.BLANCHEDALMOND,new CornerRadii(2),new Insets(2))));
        stackpane.setPadding(new Insets(20));
        stackpane.getChildren().add(seqPane);
        mainContainer.setCenter(stackpane);

        NodeGestures nodeGestures = new NodeGestures(seqPane);
        SceneGestures sceneGestures = new SceneGestures(seqPane);
        seqPane.addEventFilter( MouseEvent.MOUSE_PRESSED, sceneGestures.getOnMousePressedEventHandler());
        seqPane.addEventFilter( MouseEvent.MOUSE_DRAGGED, sceneGestures.getOnMouseDraggedEventHandler());
        seqPane.addEventFilter( ScrollEvent.ANY, sceneGestures.getOnScrollEventHandler());

        //Rectangle
        Rectangle rect1 = new Rectangle(100,100);
        rect1.setTranslateX(450);
        rect1.setTranslateY(450);
        rect1.setStroke(Color.BLUE);
        rect1.setFill(Color.BLUE.deriveColor(1, 1, 1, 0.5));
        rect1.addEventFilter( MouseEvent.MOUSE_PRESSED, nodeGestures.getOnMousePressedEventHandler());
        rect1.addEventFilter( MouseEvent.MOUSE_DRAGGED, nodeGestures.getOnMouseDraggedEventHandler());

        myButton.addEventFilter( MouseEvent.MOUSE_PRESSED, nodeGestures.getOnMousePressedEventHandler());
        myButton.addEventFilter( MouseEvent.MOUSE_DRAGGED, nodeGestures.getOnMouseDraggedEventHandler());

        seqPane.getChildren().add(rect1);






    }

    public BorderPane getMainContainer() {
        return mainContainer;
    }
}
