package sample.scopes;

import de.saxsys.mvvmfx.Scope;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import sample.model.GameChapter;
import sample.model.GameEpisode;

public class TableViewMasterScope implements Scope{

    private final ObjectProperty<GameChapter> selectedGameChapter = new SimpleObjectProperty<>(this,"selectedGameChapter");

    private final ObjectProperty<GameEpisode> selectedGameEpisode = new SimpleObjectProperty<>(this, "selectedGameEpisode");

    private final IntegerProperty seqPaneWidth= new SimpleIntegerProperty();
    private final IntegerProperty seqPaneHeight = new SimpleIntegerProperty();

    public ObjectProperty<GameChapter> selectedGameChapterProperty() {
        return this.selectedGameChapter;
    }

    public ObjectProperty<GameEpisode> selectedGameEpisodeProperty() {
        System.out.println("Return selectedGameEpisodeProperty");
        return this.selectedGameEpisode; }

    public final GameChapter getSelectedGameChapter()
    {
        return this.selectedGameChapterProperty().get();
    }

    public final void setSelectedGameChapter(final GameChapter selectedGameChapter)
    {
        this.selectedGameChapterProperty().set(selectedGameChapter);
    }

    public GameEpisode getSelectedGameEpisode() {
        System.out.println("Getting selectedGameEpisode");
        return selectedGameEpisodeProperty().get();
    }

    public void setSelectedGameEpisode(GameEpisode selectedGameEpisode) {
        System.out.println("Setting selectedGameEpisode");
        this.selectedGameEpisodeProperty().set(selectedGameEpisode);
    }


    public IntegerProperty seqPaneHeightProperty() {
        System.out.println("PaneHeightProp Returned");
        return seqPaneHeight;
    }

    public IntegerProperty seqPaneWidthProperty() {
        System.out.println("PaneWidthProp Returned");
        System.out.println("Value:?" + seqPaneWidth.getValue().toString());
        return seqPaneWidth;
    }
}
