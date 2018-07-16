package sample.TableViewCellFactory;

import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import de.saxsys.mvvmfx.internal.viewloader.View;
import de.saxsys.mvvmfx.utils.viewlist.ViewTupleMapper;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;




//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


        import de.saxsys.mvvmfx.ViewModel;
        import de.saxsys.mvvmfx.ViewTuple;
        import de.saxsys.mvvmfx.internal.viewloader.View;
        import javafx.scene.Node;
        import javafx.scene.Parent;
        import javafx.scene.control.ListCell;
import javafx.scene.control.TableCell;

public abstract class TableViewCell<S,T> extends TableCell<S,T> implements ViewTupleMapper<T> {
    public TableViewCell() {
    }

    public abstract ViewTuple<? extends View, ? extends ViewModel> map(T var1);

    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            this.setText((String)null);
            this.setGraphic((Node)null);
        } else {
            this.setText((String)null);
            Node currentNode = this.getGraphic();
            Parent view = this.map(item).getView();
            if (currentNode == null || !currentNode.equals(view)) {
                this.setGraphic(view);
            }
        }

    }



}
