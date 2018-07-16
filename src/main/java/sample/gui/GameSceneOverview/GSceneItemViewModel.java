package sample.GameSceneOverview;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableStringValue;
import javafx.beans.value.ObservableValue;

public class GSceneItemViewModel implements ViewModel {

    private ReadOnlyStringWrapper name = new ReadOnlyStringWrapper();

    public GSceneItemViewModel(String name) {
        //this.name.set(" This Name");
        this.name.set(name);
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
