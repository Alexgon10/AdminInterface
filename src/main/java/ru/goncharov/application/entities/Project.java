package ru.goncharov.application.entities;

import com.sun.istack.internal.NotNull;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jdk.nashorn.internal.objects.annotations.Setter;
import ru.goncharov.application.entities.cadElements.cadElementsImpl.Ptk;
import ru.goncharov.application.panels.listPane.listPaneImpl.ListPanePtk;

/**
 * Created on 28.02.2020.
 */

public class Project implements Entity {
    private SimpleIntegerProperty projectId;
    private SimpleBooleanProperty checkCheckBox;
    private SimpleStringProperty projectName;
    private ListPanePtk listPanePtk;
    private ObservableList<Ptk> ptkList;
    private boolean flagTextFont;
    private boolean updatePtk;

    public Project(int id, String s, boolean flag) {
        projectId = new SimpleIntegerProperty(id);
        projectName = new SimpleStringProperty(s);
        checkCheckBox = new SimpleBooleanProperty(flag);
        listPanePtk = new ListPanePtk();
        ptkList = FXCollections.observableArrayList();
        updatePtk = false;
    }

    public boolean getCheckCheckBox() {
        return checkCheckBox.get();
    }

    public SimpleBooleanProperty checkCheckBoxProperty() {
        return checkCheckBox;
    }


    public void setCheckCheckBox(boolean checkCheckBox) {
        this.checkCheckBox.set(checkCheckBox);
    }

    public String getUsername() {
        return projectName.get();
    }

    public SimpleStringProperty usernameProperty() {
        return projectName;
    }

    public void setUsername(String username) {
        this.projectName.set(username);
    }

    public int getProjectId() {
        return projectId.get();
    }

    public SimpleIntegerProperty projectIdProperty() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId.set(projectId);
    }


    public ListPanePtk getListPanePtk() {
        return listPanePtk;
    }

    public void setListPanePtk(ObservableList listPanePtk) {
        this.listPanePtk.setPtkList(listPanePtk);
    }

    public ObservableList<Ptk> getPtkList() {
        return ptkList;
    }

    public void setPtkList(ObservableList<Ptk> ptkList) {
        this.ptkList = ptkList;
    }


    public boolean getFlagTextFont() {
        return flagTextFont;
    }

    public void setFlagTextFont(boolean flagTextFont) {
        this.flagTextFont = flagTextFont;
    }

    public boolean getUpdatePtk() {
        return updatePtk;
    }

    public void setUpdatePtk(boolean updatePtk) {
        this.updatePtk = updatePtk;
    }
}
