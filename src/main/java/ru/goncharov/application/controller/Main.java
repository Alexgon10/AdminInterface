package ru.goncharov.application.controller;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.goncharov.application.interfaceElements.ApplicationScene;

/**
 * Created on 21.02.2020.
 */
 public class Main extends Application {

    private ApplicationScene applicationScene;
    public static ClassPathXmlApplicationContext context;

    @Override
    public void start(Stage primaryStage){
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        applicationScene= context.getBean("applicationScene", ApplicationScene.class);
        primaryStage.setTitle("Редактирование прав доступа к проектным элементам");
        primaryStage.setScene(applicationScene);
        primaryStage.show();
        }
        public static void main(String[] args) {
            launch(args);
        }

}
