package sample;


import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.ScopeProvider;
import de.saxsys.mvvmfx.ViewModel;
import de.saxsys.mvvmfx.utils.commands.Action;
import de.saxsys.mvvmfx.utils.commands.DelegateCommand;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.*;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import sample.events.ChaptersUpdatedEvent;
import sample.model.GameChapter;
import sample.model.GameEpisode;
import sample.scopes.MasterDetailScope;
import sample.scopes.TableViewMasterScope;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import static eu.lestard.advanced_bindings.api.ObjectBindings.map;


public class TopBarViewModel implements ViewModel
{

    //ObservableBooleanValue
    private final ReadOnlyStringWrapper selectedRowText = new ReadOnlyStringWrapper();

    private final ReadOnlyStringWrapper selectedEpisodeText = new ReadOnlyStringWrapper();

    @InjectScope
    TableViewMasterScope scope;

    @Inject
    private Event<ChaptersUpdatedEvent> chaptersUpdatedEvent;




    private DelegateCommand createNewSceneCommand;
    private DelegateCommand createNewSequenceCommand;
    private DelegateCommand createNewSequencePartCommand;



    public void initialize()
    {



        System.out.println("InitCalled");

        createNewSceneCommand = new DelegateCommand(()-> new Action()
        {
            @Override
            protected void action() throws Exception {
                System.out.println("hello World");
            }
        });
        ReadOnlyProperty<GameChapter> gameChapterProperty = getSelectedGameChapterPropertyFromScope();
        createBindingsForLabels(gameChapterProperty);
        ReadOnlyProperty<GameEpisode> gameEpisodeProperty = getSelectedGameEpisodePropertyFromScope();
        createEpisodeBinding(gameEpisodeProperty);

    }

    private void createBindingsForLabels(ReadOnlyProperty<GameChapter> gameChapterPropterty)
    {
        //email.bind(emptyStringOnNull(map(contactProperty, Contact::getEmailAddress)));

        selectedRowText.bind(emptyStringOnNull(map(gameChapterPropterty, GameChapter::getName)));

        //ObservableValue<String> observableString = new ReadOnlyStringWrapper(gameChapterPropterty.getName());

        //selectedRowText.bind(emptyStringOnNull(observableString));
    }

    private void createEpisodeBinding(ReadOnlyProperty<GameEpisode> gameEpisodeProperty)
    {
        selectedEpisodeText.bind(emptyStringOnNull(map(gameEpisodeProperty,GameEpisode::getName)));
    }

    /**
     * When the given source binding has a value of <code>null</code> an empty
     * string is used for the returned binding. Otherwise the value of the
     * source binding is used.
     */
    private StringBinding emptyStringOnNull(ObservableValue<String> source) {
        return Bindings.createStringBinding(() -> {
            if (source.getValue() == null) {
                return "";
            } else {
                return source.getValue();
            }
        }, source);
    }

    private ObjectProperty<GameChapter> getSelectedGameChapterPropertyFromScope()
    {
        return scope.selectedGameChapterProperty();
    }

    private ObjectProperty<GameEpisode> getSelectedGameEpisodePropertyFromScope()
    {
        return scope.selectedGameEpisodeProperty();
    }


    public ReadOnlyStringProperty selectedRowLabelTextProperty() {
        return selectedRowText.getReadOnlyProperty();
    }

    public ReadOnlyStringProperty selectedEpisodeLabelTextProperty()
    {
        return selectedEpisodeText.getReadOnlyProperty();
    }

    private void fireUpdateEvent() {
        if (chaptersUpdatedEvent != null) {
            chaptersUpdatedEvent.fire(new ChaptersUpdatedEvent());
        }
    }

    public void addChapter()
    {
        fireUpdateEvent();
    }



}
