package com.uady.sistemavotaciones;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

public class SistemaVotaciones extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(obtenerPath());
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Sistema de Votaciones");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private URL obtenerPath() {
        URL path = getClass().getResource("view/hello-view.fxml");
        if (path == null) {
            System.out.println("Archivo no encontrado.");
            System.exit(1);
        }
        return path;
    }

    private InputStream obtenerImg() {
        InputStream path = getClass().getResourceAsStream("images/imgProducto1.jpg");
        if (path == null) {
            System.out.println("Archivo no encontrado.");
            System.exit(1);
        }
        System.out.println("existe");
        return path;
    }

}