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
import ru.goncharov.application.entities.User;
import ru.goncharov.application.entities.cadElements.cadElementsImpl.Ptk;

/**
 * Created on 28.02.2020.
 */
@Component("listViewCellUsers")
@Scope("prototype")
public class ListViewCellUser extends ListCell<User> {

    private Controller controller;
    private CheckBox checkBox;

    @Autowired
    public ListViewCellUser(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void updateItem(User user, boolean empty) {
        if (user != null) {
            super.updateItem(user, empty);
            createCell(user);
            checkBox.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    updateAllChildCheckBoxes(user, checkBox.isSelected());
                }
            });
        }
    }

       private void updateAllChildCheckBoxes(User user, Boolean flag) {
        user.setIsSelectedCheckBox(flag);
        user.setAllChildElementSelectedFlag(flag);

        for (Project project : user.getListProjects()) {
            project.setCheckCheckBox(flag);
            project.setFlagTextFont(flag);
            for (Ptk ptk :  project.getListPanePtk().getPtkList()) {
                ptk.setSelected1(flag);
                ptk.setSelected2(flag);
            }
        }
        controller.reload();
        System.out.println(user.getUsername() + " checkBox нажат " + flag);
    }



    private void createCell(User user) {
        HBox hBox = new HBox();
        Label label = new Label();
        checkBox = new CheckBox();
        checkBox.setSelected(user.getIsSelectedCheckBox());
        label.setStyle(user.getAllChildElementSelectedFlag() ? CommonConstant.STYLE_BOLD : CommonConstant.STYLE_NORMAL);
        label.setText(user.getUsername());
        hBox.getChildren().addAll(checkBox, label);
        setGraphic(hBox);
    }

}
