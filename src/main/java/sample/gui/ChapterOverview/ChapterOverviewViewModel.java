package sample.gui.ChapterOverview;

import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ViewModel;

import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sample.Utils.CustomBinding;
import sample.events.ChaptersUpdatedEvent;
import sample.model.GameChapter;
import sample.model.GameEpisode;
import sample.model.GameStoryRepository;
import sample.scopes.TableViewMasterScope;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;


import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

import static eu.lestard.advanced_bindings.api.ObjectBindings.map;


//@ScopeProvider(scopes = TableViewMasterScope.class)
//@Singleton
@Singleton
public class ChapterOverviewViewModel implements ViewModel {

    private static final Logger LOG = LoggerFactory.getLogger(ChapterOverviewViewModel.class);




    //Mehrere Viewmodel Instanzen bedeutet ein Viewmodel pro Zeile?
    private final ObservableList<ChapterListItemViewModel> chapters = FXCollections.observableArrayList();

    private final ObservableList<EpisodeListItemViewModel> episodes = FXCollections.observableArrayList();

    private final ReadOnlyObjectWrapper<GameChapter> selectedChapter = new ReadOnlyObjectWrapper<>();

    private final ReadOnlyObjectWrapper<GameEpisode> selectedEpisode = new ReadOnlyObjectWrapper<>();

    private GameEpisode initEpisode;

    private final ObjectProperty<ChapterListItemViewModel> selectedTableRow = new SimpleObjectProperty<>();

    private final ObjectProperty<EpisodeListItemViewModel> selectedEpisodeTableRow = new SimpleObjectProperty<>();

    private Consumer<ChapterListItemViewModel> onSelect;

    private Consumer<EpisodeListItemViewModel> onEpisodeSelect;

    private GameEpisode gameEpisode;

    //protected ListProperty<ChapterListItemViewModel> chaptersProperty = new SimpleListProperty<>();

    @Inject
    private Event<ChaptersUpdatedEvent> chaptersUpdatedEvent;

    @Inject
    GameStoryRepository gameStoryRepository;

    @InjectScope
    TableViewMasterScope scope;

    public void onChaptersUpdatedEvent(@Observes ChaptersUpdatedEvent event)
    {
        System.out.println("onChaptersUpdatedEvent");
        updateChapters();
    }

    private void fireUpdateEvent() {
        if (chaptersUpdatedEvent != null) {
            chaptersUpdatedEvent.fire(new ChaptersUpdatedEvent());
        }
    }

    public void initialize()
    {
        //chaptersProperty.set(chapters);

        initEpisode = scope.getSelectedGameEpisode();

        String epname = scope.getSelectedGameEpisode().getName();
        System.out.println("Init COViewModel" + epname);

        updateEpisodes();
        autoSelectEpisode();
        //selectedEpisode.set(scope.getSelectedGameEpisode());

        epname = scope.getSelectedGameEpisode().getName();
        System.out.println("Init COViewModel before Update Episodes: " + epname);
        updateChapters();

        epname = scope.getSelectedGameEpisode().getName();
        System.out.println("Init COViewModel after Update Chapters: " + epname);

        ChangeListener changelistener = new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if(oldValue != null && newValue != null)
                {
                    System.out.println(" CHangeListener called: " + oldValue.toString() + " / " + newValue.toString());

                }
                fireUpdateEvent();
            }
        };


        scope.selectedGameEpisodeProperty().addListener(changelistener);

        selectedEpisode.setValue(initEpisode);

        scope.selectedGameEpisodeProperty().bindBidirectional(selectedEpisode);
        //scope.selectedGameEpisodeProperty().bind(selectedEpisode);
        scope.selectedGameEpisodeProperty().setValue(initEpisode);


      // CustomBinding.<ReadOnlyObjectWrapper<GameEpisode>,ObjectProperty<EpisodeListItemViewModel>>bind(selectedEpisode,selectedEpisodeTableRow,(newthing) -> newthing.set(newthing.getValue()));

       // DoubleProperty price = new SimpleDoubleProperty(10.0);
       // DoubleProperty total = new SimpleDoubleProperty(1.0);
       // int quantity = 2;

        //Source,Target, selected Episode is Ziel

        CustomBinding.<EpisodeListItemViewModel,GameEpisode>bind
                (selectedEpisodeTableRow,selectedEpisode,(currentTableRowObject) ->
                    {
                        System.out.println("CustomSelectBindings for Episode called");
                        if(selectedEpisodeTableRow.get() == null)
                        {
                            return initEpisode;
                        }
                        else
                        {
                            GameEpisode newEpisode = gameStoryRepository.findEpisodeById(selectedEpisodeTableRow.get().getId()).orElse(null);
                            initEpisode = newEpisode;
                            return newEpisode;
                            //return gameStoryRepository.findEpisodeById(selectedEpisodeTableRow.get().getId()).orElse(null);

                        }
                    }
               );



/*
        selectedEpisode.bind(Bindings.createObjectBinding( () -> {
            System.out.println("SelectBindings for Episode called");
            if(selectedEpisodeTableRow.get() == null)
            {
                return initEpisode;
            }
            else
            {
                GameEpisode newEpisode = gameStoryRepository.findEpisodeById(selectedEpisodeTableRow.get().getId()).orElse(null);
                initEpisode = newEpisode;
                return newEpisode;
                //return gameStoryRepository.findEpisodeById(selectedEpisodeTableRow.get().getId()).orElse(null);

            }

        },selectedEpisodeTableRow));
        */

        //Todo Scope
        scope.selectedGameChapterProperty().bindBidirectional(selectedChapter);

        selectedChapter.bind(Bindings.createObjectBinding(()->
        {
            System.out.println("Select Binding called");
            if(selectedTableRow.get() == null)
            {
                return null;
            }
            else
            {
                //Todo hier ansetzen für Quelle?
                //return gameStoryRepository.findById(selectedTableRow.get().getId()).orElse(null);


                //GameChapter gameChapter = scope.getSelectedGameEpisode().findById(selectedTableRow.get().getId()).orElse(null);
                GameChapter gameChapter = scope.selectedGameEpisodeProperty().get().findById(selectedTableRow.get().getId()).orElse(null);
                String id = selectedTableRow.get().getId();

                //GameEpisode episode = scope.getSelectedGameEpisode();

//                System.out.println("Select Binding called for Chapter: " + gameChapter.getName());
                System.out.println("Select Binding for Chapter, getSelectedGameEpisode");
                return scope.getSelectedGameEpisode().findById(selectedTableRow.get().getId()).orElse(null);
            }
        },selectedTableRow));



    }

    public void updateChapters()
    {
        System.out.println("Update CHapter");

        final String selectedChapterId = (selectedTableRow.get() == null) ? null : selectedTableRow.get().getId();

        GameEpisode episode = scope.getSelectedGameEpisode();
        GameChapter chapter = scope.getSelectedGameChapter();


        Set<GameChapter> allChapters;

        allChapters = episode.findAll();

        chapters.clear();

        allChapters.forEach(GameChapter -> chapters.add(new ChapterListItemViewModel(GameChapter)));

        if(selectedChapterId != null)
        {
            Optional<ChapterListItemViewModel> selectedRow = chapters.stream().filter(row -> row.getId().equals(selectedChapterId)).findFirst();

            Optional.of(onSelect).ifPresent(consumer -> consumer.accept(selectedRow.orElse(null)));
        }

    }

    public void updateChapters2()
    {
        GameEpisode episode = scope.getSelectedGameEpisode();

        Set<GameChapter> allChapters;

        if(episode != null) {
            allChapters = episode.findAll();

            chapters.clear();
            allChapters.forEach(GameChapter -> chapters.add(new ChapterListItemViewModel(GameChapter)));
        }
    }

    public void updateEpisodes()
    {

        System.out.println("Update Episodes");
        final String selectedEpisodeId = (selectedEpisodeTableRow.get() == null) ? null : selectedEpisodeTableRow.get().getId();



        Set<GameEpisode> allEpisodes = gameStoryRepository.findAllEpisode();

        episodes.clear();

        allEpisodes.forEach(GameEpisode -> episodes.add(new EpisodeListItemViewModel(GameEpisode)));

        if(selectedEpisodeId != null)
        {
            Optional<EpisodeListItemViewModel> selectedRow = episodes.stream().filter(row -> row.getId().equals(selectedEpisodeId)).findFirst();

            //Automatische Auswahl?
            // Wenn onEpisodeSelect nicht null dann consumer.accept(selectedRow)
            Optional.of(onEpisodeSelect).ifPresent(consumer -> consumer.accept(selectedRow.orElse(null)));
        }

    }


    public ObservableList<ChapterListItemViewModel> getChapters() {

        // Hier die Logic???
        System.out.println("GetChapters called");
        return chapters;
    }

    /*
    public ListProperty<ChapterListItemViewModel> getChaptersProperty() {

        // Hier die Logic???
        System.out.println("GetChapters Property called");
//        updateChapters();
        GameEpisode episode = scope.getSelectedGameEpisode();
        System.out.println("Episode " + episode.getName() + " selected");
        updateChapters2();
        return chaptersProperty;
    }*/

    public ObservableList<EpisodeListItemViewModel> getEpisodes() { return episodes; }

    public void setOnSelect(Consumer<ChapterListItemViewModel> consumer)
    {
        System.out.println("setOnSelect called");
        onSelect = consumer;
    }

    public ObjectProperty<ChapterListItemViewModel> selectedTableRowProperty()
    {
        System.out.println("TableRowProperty called");
        return selectedTableRow;
    }

    public ObjectProperty<EpisodeListItemViewModel> selectedEpisodeTableRowProperty()
    {
        System.out.println("EpisodeTableRowProperty called");
        return selectedEpisodeTableRow;
    }

    public void setOnEpisodeSelect(Consumer<EpisodeListItemViewModel> consumer)
    {
        System.out.println("setOnSelect for Episode called");
        onEpisodeSelect = consumer;
    }

    private ObjectProperty<GameEpisode> getSelectedGameEpisodePropertyFromScope()
    {
        return scope.selectedGameEpisodeProperty();
    }

    private void createEpisodeBinding(ReadOnlyProperty<GameEpisode> gameEpisodeProperty)
    {


        //selectedEpisodeText.bind(emptyStringOnNull(map(gameEpisodeProperty,GameEpisode::getName)));
    }

    public Observable getTestObservable()
    {
        //Observable testObservable = new ObjectProperty<>()
        return selectedEpisode;
    }

    public void autoSelectEpisode()
    {
        //Finde scope.selectedEpisode, finde ID
        String id = scope.getSelectedGameEpisode().getId();
        EpisodeListItemViewModel temp = episodes.stream().filter(item -> item.getId().equals(id)).findFirst().get();
        //selectedEpisodeTableRow.set(episodes.stream().filter(item -> item.getId().equals(id)).findFirst().get());
        //selectedEpisodeTableRow.setValue(temp);


        //Versuche passende ID in allen ViewModels zu finden
        //selectedTableRowProperty().set();
    }



}
