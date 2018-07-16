package sample.GameSceneOverview;

import de.saxsys.mvvmfx.ViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.inject.Singleton;


@Singleton
public class GSceneOverviewViewModel implements ViewModel{



    private ObservableList<GSceneItemViewModel> gSceneViewModels = FXCollections.observableArrayList();

    public GSceneOverviewViewModel() {



        gSceneViewModels.add(new GSceneItemViewModel("test01"));
        gSceneViewModels.add(new GSceneItemViewModel("test02"));
        gSceneViewModels.add(new GSceneItemViewModel("test03"));



    }

    public ObservableList<GSceneItemViewModel> gScenesProperty() {
        return gSceneViewModels;
    }



}
