package sample.TableViewCellFactory;

import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import de.saxsys.mvvmfx.internal.viewloader.View;
import de.saxsys.mvvmfx.utils.viewlist.ViewListCell;
import de.saxsys.mvvmfx.utils.viewlist.ViewTupleMapper;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;



//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//



@FunctionalInterface
public interface TableColumnCellFactory<S,T> extends Callback<TableColumn<S,T>, TableCell<S,T>>, ViewTupleMapper<T> {
    ViewTuple<? extends View, ? extends ViewModel> map(T var1);

    default TableViewCell<S,T> call(TableColumn<S,T> element) {
        return new TableViewCell<S,T>() {
            public ViewTuple<? extends View, ? extends ViewModel> map(T element) {
                return TableColumnCellFactory.this.map(element);
            }
        };
    }
}

