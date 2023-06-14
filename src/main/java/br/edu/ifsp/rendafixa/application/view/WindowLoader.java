package br.edu.ifsp.rendafixa.application.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class WindowLoader extends Application {

    private static Scene scene;
    private static Object controller;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("Main"));
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
<<<<<<< HEAD
        Parent root  = fxmlLoader.load(WindowLoader.class.getResource(fxml + ".fxml").openStream());
        controller = fxmlLoader.getController();
        return root;
=======
        Parent parent = fxmlLoader.load(WindowLoader.class.getResource(fxml + ".fxml").openStream());
        controller = fxmlLoader.getController();
        return parent;
>>>>>>> c34c7321dec0f82c8719a65f955f939bcf823115
    }

    public static void main(String[] args) {
        launch();
    }

    public static Object getController() {
        return controller;
    }
}