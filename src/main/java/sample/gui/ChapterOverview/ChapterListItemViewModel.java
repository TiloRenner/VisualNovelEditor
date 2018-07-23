package sample.gui.ChapterOverview;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableStringValue;
import javafx.scene.control.Button;
import sample.model.GameChapter;

public class ChapterListItemViewModel implements ViewModel
{

    private GameChapter gameChapter;

    private Button button;

    private ReadOnlyStringWrapper name = new ReadOnlyStringWrapper();

    public ChapterListItemViewModel(GameChapter gameChapter) {
        this.gameChapter = gameChapter;
        button = new Button();
        name.set(gameChapter.getName());


    }

    public String getName()
    {
        return gameChapter.getName();
    }

    public String getId()
    {
        return gameChapter.getId();
    }

    public Button getCustomViewA() {
        return button;
    }

    public ObservableStringValue nameProperty(){
        return name;
    }
}
