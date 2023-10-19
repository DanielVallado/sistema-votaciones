package com.uady.sistemavotaciones.controller;

import com.uady.sistemavotaciones.dao.VotacionDAO;
import com.uady.sistemavotaciones.util.Util;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

public class VotacionesController implements Initializable {

    @FXML
    public Label contOpcion1;
    @FXML
    public Label contOpcion2;
    @FXML
    public Label contOpcion3;
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

    private final Map<String, Integer> datos;
    private final Map<String, String> productos;
    private GraficaDeBarrasController graficaDeBarrasController;
    private GraficaDePastelController graficaDePastelController;


    public VotacionesController() {
        this.datos = new HashMap<>();
        this.productos = new HashMap<>();
        productos.put("Corn Flakes", "C:\\Users\\danie\\OneDrive - Universidad Autonoma de Yucatan\\LIS\\LIS - Quinto Semestre\\Aquitectura de Software\\ADA 8 - MVC Votaciones\\sistema-votaciones\\src\\main\\resources\\com\\uady\\sistemavotaciones\\votaciones\\producto_1.txt");
        productos.put("Oreo's", "C:\\Users\\danie\\OneDrive - Universidad Autonoma de Yucatan\\LIS\\LIS - Quinto Semestre\\Aquitectura de Software\\ADA 8 - MVC Votaciones\\sistema-votaciones\\src\\main\\resources\\com\\uady\\sistemavotaciones\\votaciones\\producto_2.txt");
        productos.put("Nesquik", "C:\\Users\\danie\\OneDrive - Universidad Autonoma de Yucatan\\LIS\\LIS - Quinto Semestre\\Aquitectura de Software\\ADA 8 - MVC Votaciones\\sistema-votaciones\\src\\main\\resources\\com\\uady\\sistemavotaciones\\votaciones\\producto_3.txt");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarImagenes();
        colocarVotos();
        asignarEventosToBotones();
    }

    @FXML
    public void verEstadisticas() {
        String GRAFICA_BARRAS_PATH = "/com/uady/sistemavotaciones/view/GraficaDeBarrasView.fxml";
        mostrarGraficaBarras(Util.obtenerPath(GRAFICA_BARRAS_PATH));

        String GRAFICA_PASTEL_PATH = "/com/uady/sistemavotaciones/view/GraficaDePastelView.fxml";
        mostrarGraficaPasteles(Util.obtenerPath(GRAFICA_PASTEL_PATH));
    }

    private void mostrarGraficaBarras(URL filePath) {
        FXMLLoader fxmlLoader = new FXMLLoader(filePath);
        this.graficaDeBarrasController = new GraficaDeBarrasController(datos);
        fxmlLoader.setController(graficaDeBarrasController);
        Scene scene = getScene(fxmlLoader);
        modificarScene("Sistema de Votaciones | Gráfica de Barras", scene);
    }

    private void mostrarGraficaPasteles(URL filePath) {
        FXMLLoader fxmlLoader = new FXMLLoader(filePath);
        this.graficaDePastelController = new GraficaDePastelController(datos);
        fxmlLoader.setController(graficaDePastelController);
        Scene scene = getScene(fxmlLoader);
        modificarScene("Sistema de Votaciones | Gráfica de Pastel", scene);
    }

    private static void modificarScene(String titulo, Scene scene) {
        Stage stage = new Stage();
        stage.setTitle(titulo);
        stage.setScene(scene);
        stage.show();
    }

    private static Scene getScene(FXMLLoader fxmlLoader) {
        try {
            return new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void asignarEventosToBotones() {
        botonVotar1.setOnAction(event -> votar(productos.get("Corn Flakes")));
        botonVotar2.setOnAction(event -> votar(productos.get("Oreo's")));
        botonVotar3.setOnAction(event -> votar(productos.get("Nesquik")));
    }

    private void cargarImagenes() {
        imgProducto1.setImage(new Image(Objects.requireNonNull(Util.obtenerImg("/com/uady/sistemavotaciones/images/imgProducto1.jpg"))));
        imgProducto2.setImage(new Image(Objects.requireNonNull(Util.obtenerImg("/com/uady/sistemavotaciones/images/imgProducto2.jpg"))));
        imgProducto3.setImage(new Image(Objects.requireNonNull(Util.obtenerImg("/com/uady/sistemavotaciones/images/imgProducto3.jpg"))));
    }

    private void votar(String filePath) {
        System.out.println("Voto registrado");
        String fechaHoraActual = obtenerHora();
        VotacionDAO votacionDAO = new VotacionDAO(filePath);
        votacionDAO.registrar(fechaHoraActual);
        colocarVotos();
    }

    private String obtenerHora() {
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return fechaHoraActual.format(formatoFechaHora);
    }

    private void colocarVotos() {
        for (Map.Entry<String, String> entry : productos.entrySet()) {
            String producto = entry.getKey();
            int cantVotos =  contarVotos(entry.getValue());
            datos.put(producto, cantVotos);
            actualizarEtiqueta(producto, cantVotos);
            actualizarGraficas();
        }
    }

    private void actualizarEtiqueta(String producto, int cantidadVotos) {
        switch (producto) {
            case "Corn Flakes" -> contOpcion1.setText(String.valueOf(cantidadVotos));
            case "Oreo's" -> contOpcion2.setText(String.valueOf(cantidadVotos));
            case "Nesquik" -> contOpcion3.setText(String.valueOf(cantidadVotos));
        }
    }

    private void actualizarGraficas() {
        if (graficaDeBarrasController != null) {
            graficaDeBarrasController.actualizarDatos(datos);
        }
        if (graficaDePastelController != null) {
            graficaDePastelController.actualizarDatos(datos);
        }

    }


    private int contarVotos(String filePath) {
        VotacionDAO votacionDAO = new VotacionDAO(filePath);
        return votacionDAO.obtenerTodos().size();
    }

}