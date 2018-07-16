package sample.TableViewCellFactory;

import de.saxsys.mvvmfx.*;
import de.saxsys.mvvmfx.internal.viewloader.View;
import de.saxsys.mvvmfx.utils.viewlist.ViewListCellFactory;
import javafx.util.Callback;

import java.util.HashMap;
import java.util.Map;



//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

        import de.saxsys.mvvmfx.FluentViewLoader;
        import de.saxsys.mvvmfx.FxmlView;
        import de.saxsys.mvvmfx.JavaView;
        import de.saxsys.mvvmfx.ViewModel;
        import de.saxsys.mvvmfx.ViewTuple;
        import de.saxsys.mvvmfx.internal.viewloader.View;
        import java.util.HashMap;
        import java.util.Map;
        import javafx.util.Callback;

public class CachedViewModelColumnCellFactory<S,V extends View<VM>, VM extends ViewModel> implements TableColumnCellFactory<S,VM> {
    private Map<VM, ViewTuple<V, VM>> cache = new HashMap();
    private Callback<VM, ViewTuple<V, VM>> loadFactory;

    public CachedViewModelColumnCellFactory(Callback<VM, ViewTuple<V, VM>> loadFactory) {
        this.loadFactory = loadFactory;
    }

    public ViewTuple<V, VM> map(VM viewModel) {
        if (!this.cache.containsKey(viewModel)) {
            ViewTuple<V, VM> viewTuple = (ViewTuple)this.loadFactory.call(viewModel);
            this.cache.put(viewModel, viewTuple);
        }

        return (ViewTuple)this.cache.get(viewModel);
    }

    public static <S,V extends View<VM>, VM extends ViewModel> CachedViewModelColumnCellFactory<S, V, VM> create(Callback<VM, ViewTuple<V, VM>> callback) {
        return new CachedViewModelColumnCellFactory(callback);
    }

    public static <S,V extends FxmlView<VM>, VM extends ViewModel> CachedViewModelColumnCellFactory<S ,V, VM> createForFxmlView(Class<V> viewType) {
        return create((vm) -> {
            return FluentViewLoader.fxmlView(viewType).viewModel(vm).load();
        });
    }

    public static <S,V extends JavaView<VM>, VM extends ViewModel> CachedViewModelColumnCellFactory<S, V, VM> createForJavaView(Class<V> viewType) {
        return create((vm) -> {
            return FluentViewLoader.javaView(viewType).viewModel(vm).load();
        });
    }
}
