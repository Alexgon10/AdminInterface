package ru.goncharov.application.panels.listPane;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import ru.goncharov.application.panels.Pane;

/**
 * Created on 28.02.2020.
 */
public interface ListPane <T>  extends Pane {

    ListView getListView();

    void setEntitiesList( ObservableList<T> t);

    ObservableList <T> getEntitiesList();

//    void reloadList(ListCell cells);


}
