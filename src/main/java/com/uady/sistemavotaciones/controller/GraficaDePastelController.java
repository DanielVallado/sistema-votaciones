package com.uady.sistemavotaciones.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class GraficaDePastelController implements Initializable {

    @FXML
    private PieChart graficaDePastel;
    private final Map<String, Integer> datos;

    public GraficaDePastelController(Map<String, Integer> datos) {
        this.datos = datos;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colocarDatos(datos);
        graficaDePastel.setTitle("Votos en pasteles");
    }

    public void actualizarDatos(Map<String, Integer> nuevosDatos) {
        colocarDatos(nuevosDatos);
    }

    private void colocarDatos(Map<String, Integer> nuevosDatos) {
        ObservableList<PieChart.Data> graficaDePastelData = FXCollections.observableArrayList();

        for (Map.Entry<String, Integer> entry : nuevosDatos.entrySet()) {
            graficaDePastelData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }

        graficaDePastel.setData(graficaDePastelData);
    }

}