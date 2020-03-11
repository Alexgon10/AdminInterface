package ru.goncharov.application.panels.tabPane.tabPaneImpl;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.goncharov.application.constants.CommonConstant;
import ru.goncharov.application.controller.Controller;
import ru.goncharov.application.controller.Main;
import ru.goncharov.application.entities.cadElements.CadElement;
import ru.goncharov.application.entities.cadElements.cadElementsImpl.Ptk;
import ru.goncharov.application.panels.cells.TreeViewCellPtk;
import ru.goncharov.application.panels.tabPane.TablePane;

/**
 * Created on 28.02.2020.
 */
@Component
public class TabPaneGetPanel implements TablePane {

    private TreeView<Ptk> treeView;
    private ObservableList<Ptk> ptkList;
    private Controller controller;
    MultipleSelectionModel<TreeItem<Ptk>> selectionModel;

    @Autowired
    public TabPaneGetPanel(Controller controller) {
        this.controller = controller;
        ptkList = FXCollections.observableArrayList();
        fillPtkTree();

        selectionModel.selectedItemProperty().addListener(new ChangeListener<TreeItem<Ptk>>() {
            public void changed(ObservableValue<? extends TreeItem<Ptk>> changed, TreeItem<Ptk> oldValue, TreeItem<Ptk> newValue) {
                if (newValue == null) {
                    return;
                }
                try {
                    controller.setCurrentPtk(newValue.getValue());
                    if (!newValue.getValue().getUpdateAbonents()) {
                        System.out.println("update");
//                        controller.updateAbonentsList(newValue.getValue());
                    }
                    System.out.println("reload");
//                    controller.reloadAbonentsList(newValue.getValue());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        treeView.setCellFactory(new Callback<TreeView, TreeCell>() {
            @Override
            public TreeCell call(TreeView param) {
                return new TreeViewCellPtk();
            }
        });
    }


    private void fillPtkTree(){
        TreeItem<Ptk> rootItem  = new TreeItem<>(new Ptk("root", ""));
        for (Ptk ptk :  ptkList ) {
            rootItem.getChildren().add(new TreeItem<>(ptk));
        }
        treeView = new TreeView<>(rootItem);
        treeView.setPrefSize(CommonConstant.sceneWeight / 2.7, CommonConstant.sceneHeight);
        treeView.setShowRoot(false);
        selectionModel = treeView.getSelectionModel();
    }

    public TreeView<CadElement> getTreeView() {
        return treeView;
    }
}
