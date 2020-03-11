package ru.goncharov.application.query;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Component;
import ru.goncharov.application.constants.ConstantQuery;
import ru.goncharov.application.entities.Project;
import ru.goncharov.application.entities.User;
import ru.goncharov.application.entities.cadElements.cadElementsImpl.Ptk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

/**
 * Created on 28.02.2020.
 */
@Component
public class Query {
//    private static final String SCHEMA_NAME="get_sys";
//    private static final String SCHEMA_NAME="public";
//    private Connection con;


    public Query()  {
    }

    public void launch() throws Exception {
//        con = DriverManager.getConnection(ConstantQuery.URL, ConstantQuery.USER, ConstantQuery.PASSWORD);
//        con.setSchema(SCHEMA_NAME);
    }


//    public ObservableList<User> getUsers() throws Exception {
//        ObservableList<User> users = FXCollections.observableArrayList();
//        PreparedStatement preparedStatement = con.prepareStatement(ConstantQuery.GET_USERS);
//        ResultSet resultSet = preparedStatement.executeQuery();
//        while (resultSet.next()) {
//            users.add(new User(resultSet.getInt(1), resultSet.getString(2), resultSet.getBoolean(3)));
//        }
//        return users;
//    }
    public ObservableList<User> getUsers() throws Exception {
        ObservableList<User> users = FXCollections.observableArrayList();
        for ( int i = 0;i <10 ; i ++ ){
            users.add(new User(new Random(10).nextInt(), "User ", false));
        }
        return users;
    }

//    public ObservableList<Project> getProjects(User selectedUser) throws Exception {
//        ObservableList<Project> projects = FXCollections.observableArrayList();
//        PreparedStatement preparedStatement = con.prepareStatement(ConstantQuery.GET_PROJECTS);
//        preparedStatement.setInt(1, selectedUser.getId());
//        ResultSet resultSet = preparedStatement.executeQuery();
//        int count = 0;
//        while (resultSet.next()) {
//            projects.add(new Project(resultSet.getInt(1), resultSet.getString(2), resultSet.getBoolean(3)));
//            if (resultSet.getBoolean(3) == true) {
//                count++;
//            }
//        }
//        if (count==projects.size()){selectedUser.setAllChildElementSelectedFlag(true);}
//        else selectedUser.setAllChildElementSelectedFlag(false);
//        return projects;
//    }


    public ObservableList<Project> getProjects(User selectedUser) throws Exception {
        ObservableList<Project> projects = FXCollections.observableArrayList();
        for (int i=0;i<10;i++) {
            projects.add(new Project(new Random(10).nextInt(), "Project ", true));
        }
        return projects;
    }

    public ObservableList<Ptk> getPtk(Ptk selectedPtk) throws Exception {
        ObservableList<Ptk> ptkList = FXCollections.observableArrayList();
        for (int i=0;i<11;i++) {
            ptkList.add(new Ptk("Ptk ","schema ",new Random(10).nextInt(), false, true));
        }
        return ptkList;
    }
}
