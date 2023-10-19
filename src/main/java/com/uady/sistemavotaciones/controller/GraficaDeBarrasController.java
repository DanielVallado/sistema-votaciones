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
    private BarChart<String, Integer> barChart;
    @FXML
    private Text graficaDeBarrasTexto;

    private final Map<String, Integer> datos;

    public GraficaDeBarrasController(Map<String, Integer> datos) {
        this.datos = datos;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colocarDatos(this.datos);
        graficaDeBarrasTexto.setText("Gráfica de Barras");
    }

    public void actualizarDatos(Map<String, Integer> nuevosDatos) {
        barChart.getData().clear();
        colocarDatos(nuevosDatos);
    }

    private void colocarDatos(Map<String, Integer> datos) {
        for (Map.Entry<String, Integer> entry : datos.entrySet()) {
            XYChart.Series<String, Integer> series = new XYChart.Series<>();
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
            series.setName(entry.getKey());
            barChart.getData().add(series);
        }
    }

}