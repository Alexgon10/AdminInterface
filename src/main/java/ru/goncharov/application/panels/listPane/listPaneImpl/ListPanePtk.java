package ru.goncharov.application.panels.listPane.listPaneImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.goncharov.application.entities.cadElements.cadElementsImpl.Ptk;

public class ListPanePtk {

    private ObservableList<Ptk> ptkList;

    public ListPanePtk() {
        ptkList = FXCollections.observableArrayList();
    }

    public ObservableList<Ptk> getPtkList() {
        return ptkList;
    }

    public void setPtkList(ObservableList<Ptk> ptk) {
        this.ptkList = ptk;
    }
}
