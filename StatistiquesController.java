package com.example.tp_camping.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

public class StatistiquesController {

    @FXML private BarChart<String, Number> barChart;

    public void initialize() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Plannings par mois");
        
        series.getData().add(new XYChart.Data<>("Janvier", 10));
        series.getData().add(new XYChart.Data<>("Février", 15));
        series.getData().add(new XYChart.Data<>("Mars", 20));

        barChart.getData().add(series);
    }
}
