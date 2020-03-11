package ru.goncharov.application.interfaceElements;

import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.goncharov.application.panels.listPane.ListPane;
import ru.goncharov.application.panels.listPane.listPaneImpl.ListPaneProjects;
import ru.goncharov.application.panels.listPane.listPaneImpl.ListPaneUsers;
import ru.goncharov.application.panels.tabPane.tabPaneImpl.TablePaneStructure;

/**
 * Created on 28.02.2020.
 */
@Component
public class RootPanelStructure extends BorderPane{

    private TablePaneStructure tabPaneStructure;
    private ListPaneProjects listPaneProjects;
    private ListPaneUsers listPaneUsers;

    @Autowired
    RootPanelStructure(ListPaneProjects listPaneProjects, ListPaneUsers listPaneUsers, TablePaneStructure tabPaneStructure){
        this.listPaneProjects=listPaneProjects;
        this.listPaneUsers = listPaneUsers;
        this.tabPaneStructure = tabPaneStructure;
    }

    private Label createLabel(String text){
        Label label = new Label(text);
        label.getStylesheets().add("Fonts.css");
    return label;
    }

    private VBox createVBox(Label label , ListPane listPane){
        VBox vbox = new VBox();
        vbox.getChildren().addAll(label, listPane.getListView());
    return vbox;
    }

    private VBox createVBox(Label label , TabPane tabPane){
        VBox vbox = new VBox();
        vbox.getChildren().addAll(label, tabPane);
        return vbox;
    }


    private AnchorPane vBox(){
        HBox hBox = new HBox(40);
        hBox.getChildren().
                addAll(createVBox(createLabel("Список Пользователей"),listPaneUsers),
                        createVBox(createLabel("Список Проектов"),listPaneProjects),
                        createVBox(createLabel("Структура Проекта"), tabPaneStructure));
        AnchorPane anchorPaneVBox = new AnchorPane();
        AnchorPane.setTopAnchor(hBox,20.0);
        AnchorPane.setBottomAnchor(hBox,30.0);
        AnchorPane.setRightAnchor(hBox,30.0);
        AnchorPane.setLeftAnchor(hBox,30.0);
        anchorPaneVBox.getChildren().add(hBox);

    return anchorPaneVBox;
    }

    private AnchorPane createButton (){
        Button buttonSave = new Button("Сохранить");
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().add(buttonSave);
        AnchorPane.setBottomAnchor(buttonSave,30.0);
        AnchorPane.setRightAnchor(buttonSave,40.0);
    return anchorPane;
    }

    private MenuBar createMenuBar(){
        MenuBar menuBar = new MenuBar(new Menu("File"), new Menu(("Help")));
    return menuBar;}


    public BorderPane createPane (){
        super.setBottom(createButton());
        super.setTop(createMenuBar());
        super.setCenter(vBox());
    return this;}
}
