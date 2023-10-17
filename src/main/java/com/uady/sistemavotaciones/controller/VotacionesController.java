package com.uady.sistemavotaciones.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class VotacionesController implements Initializable {

    @FXML
    private Button botonVotar1;
    @FXML
    private Button botonVotar2;
    @FXML
    private Button botonVotar3;
    @FXML
    private ImageView imgProducto1;
    @FXML
    private ImageView imgProducto2;
    @FXML
    private ImageView imgProducto3;
    @FXML
    private Label welcomeText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarImagenes();
        asignarEventosToBotones();
    }

    private void asignarEventosToBotones() {
        botonVotar1.setOnAction(event -> votar("Producto 1"));
        botonVotar2.setOnAction(event -> votar("Producto 2"));
        botonVotar3.setOnAction(event -> votar("Producto 3"));
    }

    private void cargarImagenes() {
        imgProducto1.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/uady/sistemavotaciones/images/imgProducto1.jpg"))));
        imgProducto2.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/uady/sistemavotaciones/images/imgProducto2.jpg"))));
        imgProducto3.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/uady/sistemavotaciones/images/imgProducto3.jpg"))));
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    private void votar(String producto) {
        System.out.println("Voto " + producto);
    }

}