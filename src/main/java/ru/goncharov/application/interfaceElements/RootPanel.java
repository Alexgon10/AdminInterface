package ru.goncharov.application.interfaceElements;

import javafx.scene.layout.BorderPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.goncharov.application.controller.Controller;

/**
 * Created on 21.02.2020.
 */
@Component
public class RootPanel extends BorderPane {
    private Controller controller;
    private RootPanelStructure rootPanelStructure;

    @Autowired
    RootPanel(Controller controller, RootPanelStructure rootPanelStructure) {
        this.controller = controller;
        this.rootPanelStructure = rootPanelStructure;
        controller.setUsersList();
    }

    public BorderPane createRootPane() {
        return rootPanelStructure.createPane();
    }

}
