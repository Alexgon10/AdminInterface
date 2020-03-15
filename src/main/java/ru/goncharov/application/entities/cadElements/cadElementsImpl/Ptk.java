package ru.goncharov.application.entities.cadElements.cadElementsImpl;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import ru.goncharov.application.entities.cadElements.CadElement;

/**
 * Created on 09.10.2019.
 */
public class Ptk implements CadElement {

    private SimpleBooleanProperty checkComboBox1;
    private SimpleBooleanProperty checkComboBox2;
    private SimpleIntegerProperty ptkId;
    private SimpleStringProperty ptkName;
    private String schemaName;
//    private ListPaneAbonents listPaneAbonents;
    private boolean updateAbonents;


    public Ptk(String ptkName, String schemaName, int ptkId, boolean selected1, boolean selected2) {
        this.ptkName = new SimpleStringProperty(ptkName);
        this.schemaName = schemaName;
        this.ptkId = new SimpleIntegerProperty(ptkId);
        checkComboBox1 = new SimpleBooleanProperty(false);
        checkComboBox2 = new SimpleBooleanProperty(false);
        this.checkComboBox1 = new SimpleBooleanProperty(selected1);
        this.checkComboBox2 = new SimpleBooleanProperty(selected2);
    }

    public Ptk(String ptkName, String schemaName) {
//        listPaneAbonents = new ListPaneAbonents();
        this.ptkName = new SimpleStringProperty(ptkName);
        this.schemaName=schemaName;
        checkComboBox1= new SimpleBooleanProperty(false);
        checkComboBox2 = new SimpleBooleanProperty(false);
        updateAbonents=false;
    }
    /***18.11.19*/

    public String getName() {
        return ptkName.get();
    }

    public void setName(String ptkName) {
        this.ptkName.set(ptkName);
    }

    public boolean isSelected1() {
        return checkComboBox1.get();
    }

    public void setSelected1(boolean selected1) {
        this.checkComboBox1.set(selected1);
    }

    public boolean isSelected2() {
        return checkComboBox2.get();
    }

    public void setSelected2(boolean selected2) {
        this.checkComboBox2.set(selected2);
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public int getPtkId() {
        return ptkId.get();
    }

    public SimpleIntegerProperty ptkIdProperty() {
        return ptkId;
    }

    public void setPtkId(int ptkId) {
        this.ptkId.set(ptkId);
    }

//    public ListPaneAbonents getListPaneAbonents() {
//        return listPaneAbonents;
//    }

//    public void setListPaneAbonents(ObservableList<Abonent> listPaneAbonents) {
//        this.listPaneAbonents.setAbonentsList(listPaneAbonents);
//    }

    public boolean getUpdateAbonents() {
        return updateAbonents;
    }

    public void setUpdateAbonents(boolean updateAbonents) {
        this.updateAbonents = updateAbonents;
    }

}
