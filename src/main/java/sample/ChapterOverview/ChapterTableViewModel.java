package sample.ChapterOverview;

import javafx.scene.control.Button;
import sample.model.GameChapter;

public class ChapterTableViewModel
{

    private GameChapter gameChapter;

    private Button button;

    public ChapterTableViewModel(GameChapter gameChapter) {
        this.gameChapter = gameChapter;
        button = new Button();


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
}
