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
import ru.goncharov.application.entities.Project;
//import ru.goncharov.application.panels.cells.ListViewCellProject;
import ru.goncharov.application.panels.cells.ListViewCellProject;
import ru.goncharov.application.panels.listPane.ListPane;

/**
 * Created on 28.02.2020.
 */
@Component
public class ListPaneProjects implements ListPane<Project> {

    private ListView<Project> listView;
    private ObservableList<Project> projectsList;

    @Autowired
    ListPaneProjects(Controller controller) {
        projectsList = FXCollections.observableArrayList();
        configurationList(projectsList);

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Project>() {
            @Override
            public void changed(ObservableValue<? extends Project> observable, Project oldValue, Project newValue) {
                if (newValue == null) {
                    return;
                }
                try {
                    controller.setCurrentProject(newValue);
                    if (!newValue.getUpdatePtk()) {
                        System.out.println("loadPtkForUser");
//                        controller.updatePtkList(newValue);
                    }
                    System.out.println("reload");
//                    controller.reloadPtkList(newValue);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        reloadList();
    }

    private void configurationList(ObservableList<Project> projectsList) {
        listView = new ListView<Project>(projectsList);
        listView.setEditable(true);
        listView.setPrefSize(CommonConstant.sceneWeight / 4, CommonConstant.sceneHeight);
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }


    @Override
    public ListView getListView() {
        return listView;
    }

    @Override
    public void setEntitiesList(ObservableList<Project> projectsList) {
        this.projectsList = projectsList;
        listView.setItems(projectsList);
    }

    @Override
    public ObservableList<Project> getEntitiesList() {
        return projectsList;
    }

    public void reloadList() {
        listView.setCellFactory(new Callback<ListView<Project>, ListCell<Project>>() {
            @Override
            public ListCell<Project> call(ListView<Project> param) {
                return Main.context.getBean("listViewCellProject", ListViewCellProject.class);
            }
        });
    }
}
