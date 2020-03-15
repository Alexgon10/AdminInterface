package ru.goncharov.application.entities;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created on 09.10.2019.
 */
//TODO : сделать билдер на класс

public class User implements Entity {

    private ObservableList<Project> listProjects;
    private SimpleBooleanProperty isSelectedCheckBox;
    private SimpleStringProperty username;
    private SimpleIntegerProperty userId;
    private boolean allChildElementSelectedFlag;


    public User(int id, String s, boolean flag) {
        userId = new SimpleIntegerProperty(id);
        username = new SimpleStringProperty(s);
        isSelectedCheckBox = new SimpleBooleanProperty(flag);
        listProjects = FXCollections.observableArrayList();
    }

    public boolean getIsSelectedCheckBox() {
        return isSelectedCheckBox.get();
    }

    public void setIsSelectedCheckBox(boolean isSelectedCheckBox) {
        this.isSelectedCheckBox.set(isSelectedCheckBox);
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public void setId(int id) {
        userId.set(id);
    }

    public int getId() {
        return userId.get();
    }

    public ObservableList<Project> getListProjects() {
        return listProjects;
    }

    public void setListProjects(ObservableList listPaneProjects) {
        this.listProjects = listPaneProjects;
    }

    public boolean getAllChildElementSelectedFlag() {
        return allChildElementSelectedFlag;
    }

    public void setAllChildElementSelectedFlag(boolean allChildElementSelectedFlag) {
        this.allChildElementSelectedFlag = allChildElementSelectedFlag;
    }
}

