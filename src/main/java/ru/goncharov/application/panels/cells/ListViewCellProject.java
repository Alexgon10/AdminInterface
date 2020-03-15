package ru.goncharov.application.panels.cells;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.goncharov.application.constants.CommonConstant;
import ru.goncharov.application.controller.Controller;
import ru.goncharov.application.entities.Project;
import ru.goncharov.application.entities.cadElements.cadElementsImpl.Ptk;

@Component
@Scope("prototype")
public class ListViewCellProject extends ListCell<Project> {

    private Controller controller;
    private CheckBox checkBox;

    @Autowired
    ListViewCellProject(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void updateItem(Project project, boolean empty) {
        super.updateItem(project, empty);
        if (project == null) {
            return;
        }
        if (empty) {
            setGraphic(null);
            return;
        }
        createCell(project);
        checkBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                updateAllChildCheckBoxes(project, checkBox.isSelected());

                //TODO :    controller.checkLastSelectedCheckbox();  //проверка если выбрана последняя то подсветить пользователя
                //TODO: добавить всплывающее окно с подтверждением установки всех галочек во всех проектах у пользователя

            }
        });


    }

    private void updateAllChildCheckBoxes(Project project, Boolean flag) {
        project.setCheckCheckBox(flag);
        project.setFlagTextFont(flag);
        controller.checkLastSelectedCheckbox();
//        if (project.getPtkList()==null){return;}
        //TODO : проверить если не загружены ptk , то загрузить
        for (Ptk ptk :project.getListPanePtk().getPtkList()) {
            ptk.setSelected1(flag);
            ptk.setSelected2(flag);
        }

        controller.reloadPtk(project);
        System.out.println(project.getUsername() + " checkBox нажат " + flag);
    }

    private void createCell(Project project) {
        Label label = new Label();
        HBox hBox = new HBox();
        checkBox = new CheckBox();
        checkBox.setSelected(project.getCheckCheckBox());
        label.setStyle(project.getFlagTextFont() ? CommonConstant.STYLE_BOLD : CommonConstant.STYLE_NORMAL);
        label.setText(project.getUsername());
        hBox.getChildren().addAll(checkBox, label);
        setGraphic(hBox);
    }
//TODO: можно проверять последний элемент счетчиком, добавляя при каждой галочке ++ и потом сравнивать вместо того чтобы пробегаться по коллекции с дочерними элементами!
}


