package sample.scopes;


import de.saxsys.mvvmfx.Scope;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import sample.model.GameChapter;

public class MasterDetailScope implements Scope {

    private final ObjectProperty<GameChapter> selectedContact = new SimpleObjectProperty<>(this, "selectedContact");

    public ObjectProperty<GameChapter> selectedContactProperty() {
        System.out.println("ContactProperty");
        return this.selectedContact;
    }

    public final GameChapter getSelectedContact() {
        System.out.println("Scope Selected Contactgetter");
        return this.selectedContactProperty().get();
    }

    public final void setSelectedContact(final GameChapter selectedContact) {
        System.out.println("Scope Selected Contactsetter");
        this.selectedContactProperty().set(selectedContact);
    }

}
