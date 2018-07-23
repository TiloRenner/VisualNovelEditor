package sample.gui.MainContainer;

import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.Scope;
import de.saxsys.mvvmfx.ScopeProvider;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sample.model.GameChapter;
import sample.model.GameEpisode;
import sample.model.GameStoryRepository;
import sample.scopes.TableViewMasterScope;

import javax.inject.Inject;

import java.util.Set;

@ScopeProvider(scopes = {TableViewMasterScope.class})
public class MainContainerViewModel implements ViewModel {

    @Inject
    GameStoryRepository gameStoryRepository;

    @InjectScope
    TableViewMasterScope scope;

    private StringProperty helloMessage = new SimpleStringProperty("Hello WorldX");

    public String getHelloMessage() {
        return helloMessage.get();
    }

    public StringProperty helloMessageProperty() {
        return helloMessage;
    }

    public void setHelloMessage(String helloMessage) {
        this.helloMessage.set(helloMessage);
    }

    private IntegerProperty seqPaneWidth = new SimpleIntegerProperty();

    private IntegerProperty seqPaneHeight = new SimpleIntegerProperty();


    public void initialize()
    {
        setTVMScope();
        helloMessage.bind(Bindings.createObjectBinding(()-> {return "SeqPaneHeight is: " + seqPaneHeight.getValue().toString();},seqPaneHeight
        ));






    }

    public void setTVMScope()
    {
        //TableViewMasterScope scope = new TableViewMasterScope();
        Set<GameEpisode> episodes = gameStoryRepository.findAllEpisode();

        GameEpisode episode = episodes.stream().findAny().get();

        System.out.println(" Startup GameEpisode: " + episode.getName());

        scope.setSelectedGameEpisode(episode);
        GameChapter chapter = episode.findAll().stream().findFirst().get();


        scope.setSelectedGameChapter(chapter);



    }

    public Scope getTVMScope()
    {
        return scope;
    }


    public IntegerProperty seqPaneWidth()
    {
        return seqPaneWidth;
    }

    public IntegerProperty seqPaneHeight()
    {
        return seqPaneHeight;
    }


}
