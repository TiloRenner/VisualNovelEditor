package sample.gui.GameSceneOverview;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableStringValue;
import javafx.beans.value.ObservableValue;
import sample.model.GameScene;

public class GSceneItemViewModel implements ViewModel {

    private GameScene gameScene;

    private ReadOnlyStringWrapper name = new ReadOnlyStringWrapper();

    public GSceneItemViewModel(String name) {
        //this.name.set(" This Name");
        this.name.set(name);
    }

    public GSceneItemViewModel(GameScene gameScene) {
        //this.name.set(" This Name");
        this.gameScene = gameScene;
    }

    public String getId()
    {
        return gameScene.getId();
    }

    public ObservableStringValue nameProperty(){
        return name;
    }

    public String nameProperty2(){
        return name.getValue();
    }

    /*public ViewModel vmProperty()
    {
        return this;
    }*/

    public ObservableValue<ViewModel> vmProperty()
    {
        return new ReadOnlyObjectWrapper<>(this);
    }
}
