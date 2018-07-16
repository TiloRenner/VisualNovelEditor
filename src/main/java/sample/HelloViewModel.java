package sample;

import de.saxsys.mvvmfx.InjectScope;
import de.saxsys.mvvmfx.Scope;
import de.saxsys.mvvmfx.ScopeProvider;
import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sample.model.GameEpisode;
import sample.model.GameStoryRepository;
import sample.scopes.TableViewMasterScope;

import javax.inject.Inject;
import java.util.Set;

@ScopeProvider(scopes = {TableViewMasterScope.class})
public class HelloViewModel implements ViewModel {

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

    public void initialize()
    {
        setTVMScope();

    }

    public void setTVMScope()
    {
        //TableViewMasterScope scope = new TableViewMasterScope();
        Set<GameEpisode> episodes = gameStoryRepository.findAllEpisode();

        GameEpisode episode = episodes.stream().findAny().get();

        System.out.println(" Startup GameEpisode: " + episode.getName());

        scope.setSelectedGameEpisode(episode);



    }

    public Scope getTVMScope()
    {
        return scope;
    }


}
