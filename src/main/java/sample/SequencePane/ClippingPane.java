package sample.SequencePane;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class ClippingPane extends Pane {

    public ClippingPane() {
        Rectangle clipRectangle = new Rectangle();
        setClip(clipRectangle);
        layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
            clipRectangle.setWidth(newValue.getWidth());
            clipRectangle.setHeight(newValue.getHeight());
        });
    }
}
