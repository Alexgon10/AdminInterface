package ru.goncharov.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.goncharov.application.entities.Project;
import ru.goncharov.application.entities.User;
import ru.goncharov.application.entities.cadElements.cadElementsImpl.Ptk;
import ru.goncharov.application.panels.listPane.listPaneImpl.ListPaneProjects;
import ru.goncharov.application.panels.listPane.listPaneImpl.ListPaneUsers;
import ru.goncharov.application.panels.tabPane.tabPaneImpl.TabPaneGetPanel;
import ru.goncharov.application.query.Query;

/**
 * Created on 21.02.2020.
 */

@Component
public class Controller {
    private ListPaneUsers listPaneUsers;
    private ListPaneProjects listPaneProjects;
    private Query query;
    private User currentUser;
    private Project currentProject;
    private Ptk currentPtk;
    private TabPaneGetPanel tabPaneGetPanel;

    @Autowired
    public Controller(Query query) {
        this.query = query;
        try {
            query.launch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setUsersList()   {
        try {
            listPaneUsers.setEntitiesList(query.getUsers());  //запрос с БД и заполнение листа users
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (User user : listPaneUsers.getEntitiesList()) {
            updateProjectList(user);
        }
        setCurrentUser(listPaneUsers.getEntitiesList().get(0));
        setCurrentProject(currentUser.getListProjects().get(0));
        listPaneProjects.setEntitiesList(currentUser.getListProjects());
        tabPaneGetPanel.setPtkList(currentProject.getListPanePtk().getPtkList());
    }

    public void updateProjectList(User selectedUser) {
        try {
            selectedUser.setListProjects(query.getProjects(selectedUser));
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Project project :  selectedUser.getListProjects()) {
            try {
                updatePtkList(project);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void updatePtkList(Project selectedProject) throws Exception {
        selectedProject.setListPanePtk(query.getPtk(selectedProject));
        selectedProject.setUpdatePtk(true);
//        for (int i = 0 ; i < selectedProject.getListPanePtk().getPtkList().size();i++){
//            updateAbonentsList(selectedProject.getListPanePtk().getPtkList().get(i));
//        }
    }


    public void checkLastSelectedCheckbox() {
        boolean flagCombobox = false; // если хотя бы одна галочка стоит то true(для того чтобы выйти из проверки заранее если после  поставленной галочки будет хотя бы одна пустая)
        int count = 0;
        if (currentProject == null) {
            System.out.println("ОШИБКА В checkLastSelectedCheckbox ");
            return;
        }
        for ( Project project : currentUser.getListProjects()) {
            if (project.getCheckCheckBox()) {
                currentUser.setIsSelectedCheckBox(true);
                flagCombobox = true;
                count++;
            } else if (flagCombobox) {
                break;
            }
        }
        if (count == currentUser.getListProjects().size()) {
            currentUser.setAllChildElementSelectedFlag(true);
        } else currentUser.setAllChildElementSelectedFlag(false);
        if (!flagCombobox) {
            currentUser.setIsSelectedCheckBox(false);
        }
        listPaneUsers.reloadList();

    }

    public void reload() {
        listPaneUsers.reloadList();
        listPaneProjects.reloadList();
        tabPaneGetPanel.reloadTree();
    }

    public void reload(User selectedUser) {
        listPaneProjects.setEntitiesList(selectedUser.getListProjects());
        currentProject = selectedUser.getListProjects().get(0);
//        tabPaneGetPanel.setPtkList(selectedUser.getListPaneProjects().getProjects().get(0).getListPanePtk().getPtkList());
    }

    public void setCurrentProject(Project project) {
        this.currentProject = project;
    }

    public void reload(Project selectedProject) {
        tabPaneGetPanel.setPtkList(selectedProject.getListPanePtk().getPtkList());
        listPaneProjects.reloadList();
        tabPaneGetPanel.reloadTree();
        if (selectedProject.getPtkList().size() > 0) {
            currentPtk = selectedProject.getPtkList().get(0);
        }
    }

    public void reloadPtk(Project selectedProject) {
        listPaneProjects.reloadList();
        tabPaneGetPanel.reloadTree();
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public void setCurrentPtk(Ptk ptk) { this.currentPtk = ptk; }

    @Autowired
    private void setListPaneUsers(ListPaneUsers listPaneUsers) {
        this.listPaneUsers = listPaneUsers;
    }

    @Autowired
    private void setListPaneProjects(ListPaneProjects listPaneProjects) {
        this.listPaneProjects = listPaneProjects;
    }

    @Autowired
    private void setTabPaneGetPanel(TabPaneGetPanel tabPaneGetPanel) {
        this.tabPaneGetPanel=tabPaneGetPanel;
    }
}


