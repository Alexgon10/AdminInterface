package ru.goncharov.application.interfaceElements;

import javafx.scene.Scene;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.goncharov.application.constants.CommonConstant;

@Component("applicationScene")
public class ApplicationScene extends Scene {


    @Autowired
    public ApplicationScene(RootPanel root) {
        super(root.createRootPane(), CommonConstant.sceneWeight, CommonConstant.sceneHeight);
    }

}
