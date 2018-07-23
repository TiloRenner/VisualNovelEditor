package sample.SequencePane;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class SequencePane extends Pane {

    DoubleProperty myScale = new SimpleDoubleProperty(1.0);

    static final Button initseqButton = new Button();
    Button seqButton = new Button();

    public SequencePane() {
        this(initseqButton);
        seqButton = initseqButton;
        seqButton.setText("SeqPane Button1");
        seqButton.setLayoutX(200);
        seqButton.setLayoutY(200);


        setPrefSize(600, 600);
        setStyle("-fx-background-color: lightgrey; -fx-border-color: blue;");

        // add scale transform
        scaleXProperty().bind(myScale);
        scaleYProperty().bind(myScale);
        addGrid();



    }

    public SequencePane(Node... children) {

        super(children);
    }

    /**
     * Add a grid to the canvas, send it to back
     */
    public void addGrid() {

        double w = getBoundsInLocal().getWidth();
        double h = getBoundsInLocal().getHeight();

        // add grid
        Canvas grid = new Canvas(w, h);

        // don't catch mouse events
        grid.setMouseTransparent(true);

        GraphicsContext gc = grid.getGraphicsContext2D();

        gc.setStroke(Color.GRAY);
        gc.setLineWidth(2);

        // draw grid lines
        double offset = 50;
        for( double i=offset; i < w; i+=offset) {
            gc.strokeLine( i, 0, i, h);
            gc.strokeLine( 0, i, w, i);
        }

        getChildren().add( grid);

        grid.toBack();
    }

    public double getScale() {
        return myScale.get();
    }

    public void setScale( double scale) {
        myScale.set(scale);
        printBoundInfo();
    }

    public void setPivot( double x, double y) {
        setTranslateX(getTranslateX()-x);
        setTranslateY(getTranslateY()-y);
    }

    private void printBoundInfo()
    {
        Bounds parentBounds = getBoundsInParent();
        if(parentBounds != null)
        {
           System.out.println(" BoundP Width:" + parentBounds.getWidth() + " BoundP Height:" + parentBounds.getHeight() + " BoundsP X:");
        }

        Bounds localBounds = getBoundsInLocal();
        if(localBounds != null)
        {
            System.out.println(" BoundL Width:" + localBounds.getWidth() + " BoundL Height:" + localBounds.getHeight() + " BoundsL X:");
        }

        System.out.println("X:" + getLayoutX() + "Y: "+ getLayoutY());




    }

}


/**
 * Mouse drag context used for scene and nodes.
 */
class DragContext {

    double mouseAnchorX;
    double mouseAnchorY;

    double translateAnchorX;
    double translateAnchorY;

}



















