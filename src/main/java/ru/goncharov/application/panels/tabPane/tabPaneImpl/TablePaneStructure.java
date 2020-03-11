package ru.goncharov.application.panels.tabPane.tabPaneImpl;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.goncharov.application.constants.CommonConstant;
import ru.goncharov.application.panels.tabPane.TablePane;

/**
 * Created on 28.02.2020.
 */
@Component
public class TablePaneStructure extends TabPane implements TablePane {
    private Label label2 = new Label( " Вкладка 2");
    private Label label3 = new Label( " Вкладка 3");

    @Autowired
    TablePaneStructure(TabPaneGetPanel tabPaneGetPanel)throws Exception{
        setStyle("-fx-background-color: lightgrey");
        setPrefSize(CommonConstant.sceneWeight/2.7 , CommonConstant.sceneWeight);

        /***/
//        TabPaneGetPanel tabPaneGetPanel = new TabPaneGetPanel();
        addTab(" GET" , tabPaneGetPanel.getTreeView());

        BorderPane bp2 = new BorderPane();
        addTab(" HET" ,label2);

        BorderPane bp3 = new BorderPane();
        addTab(" TECH" ,label3);
    }


    public void addTab(String text, Node obj) {
        Tab tab = new Tab(text);
        tab.setContent(obj);
        getTabs().add(tab);
    }
}
