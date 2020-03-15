package ru.goncharov.application.panels.cells;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.layout.HBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.goncharov.application.constants.CommonConstant;
import ru.goncharov.application.controller.Controller;
import ru.goncharov.application.entities.Project;
import ru.goncharov.application.entities.User;
import ru.goncharov.application.entities.cadElements.CadElement;
import ru.goncharov.application.entities.cadElements.cadElementsImpl.Ptk;

@Component("treeViewCellPtk")
@Scope("prototype")
public class TreeViewCellPtk extends TreeCell<CadElement> {
    private Controller controller;
    private CheckBox cb1;
    private CheckBox cb2;

    @Autowired
    TreeViewCellPtk(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void updateItem(CadElement ptk, boolean empty) {
        super.updateItem(ptk, empty);
        if (empty||ptk == null) {
            setGraphic(null);
            return;
        }
        createCell(ptk);

        cb1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                updateAllChildCheckBoxes(ptk, cb1.isSelected());
            }
        });
        cb2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                updateAllChildCheckBoxes(ptk, cb2.isSelected());
            }
        });


    }

    private void createCell(CadElement ptk) {
        cb1 = new CheckBox();
        cb2 = new CheckBox();
        HBox hBox = new HBox();
        cb1.setSelected(ptk.isSelected1());
        cb2.setSelected(ptk.isSelected2());
        Label label = new Label(ptk.getName());
        hBox.getChildren().addAll(cb1, cb2, label);
        setGraphic(hBox);
    }

    private void updateAllChildCheckBoxes(CadElement ptk, Boolean flag) {
        ptk.setSelected1(flag);
        System.out.println(ptk.getName() + " checkBox нажат " + flag);
    }

}
