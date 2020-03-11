package ru.goncharov.application.panels.listPane.listPaneImpl;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.goncharov.application.constants.CommonConstant;
import ru.goncharov.application.controller.Controller;
import ru.goncharov.application.controller.Main;
import ru.goncharov.application.entities.User;
import ru.goncharov.application.panels.cells.ListViewCellUser;
import ru.goncharov.application.panels.listPane.ListPane;

/**
 * Created on 28.02.2020.
 */
@Component
public class ListPaneUsers implements ListPane<User> {
    private ListView<User> listView;
    private ObservableList<User> usersList;

    @Autowired
    public ListPaneUsers(Controller controller) {
        usersList = FXCollections.observableArrayList();
        configurationList(usersList);
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {
            @Override
            public void changed(ObservableValue<? extends User> observable, User oldValue, User newValue) {
                try {
                    controller.setCurrentUser(newValue);       // смена текущего пользоавтеля, подгрузка проектов для него
                    controller.reload(newValue);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        reloadList();
    }


    private void configurationList(ObservableList<User> usersList) {
        listView = new ListView<>(usersList);
        listView.setPrefSize(CommonConstant.sceneWeight / 4, CommonConstant.sceneHeight);
        listView.setEditable(true);
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @Override
    public ObservableList<User> getEntitiesList() {
        return usersList;
    }

    @Override
    public void setEntitiesList(ObservableList<User> usersName) {
        this.usersList = usersName;
        listView.setItems(usersList);
    }

    @Override
    public ListView getListView() {
        return listView;
    }

    public void reloadList() {
        listView.setCellFactory(new Callback<ListView<User>, ListCell<User>>() {
            @Override
            public ListCell<User> call(ListView<User> param) {
                return Main.context.getBean("listViewCellUsers", ListViewCellUser.class);
            }
        });
    }

}
