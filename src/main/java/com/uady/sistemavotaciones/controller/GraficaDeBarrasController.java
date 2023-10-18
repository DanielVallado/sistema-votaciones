package com.uady.sistemavotaciones.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class GraficaDeBarrasController implements Initializable {

    @FXML
    private BarChart barChart;
    @FXML
    private Text graficaDeBarrasTexto;

    private final Map<String, Integer> datos;

    public GraficaDeBarrasController(Map<String, Integer> datos) {
        this.datos = datos;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        XYChart.Series<String, Integer> series = new XYChart.Series<>();

        // Iterar a través del mapa y agregar los datos a la serie
        for (Map.Entry<String, Integer> entry : datos.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        barChart.getData().add(series);
        graficaDeBarrasTexto.setText("Gráfica de Barras");
    }

}