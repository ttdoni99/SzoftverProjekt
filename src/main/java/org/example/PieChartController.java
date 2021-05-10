package org.example;

import Database.Loader;
import Modells.Distribution;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class PieChartController {
    @FXML
    PieChart pieChart;
    @FXML
    ListView<String> elements;

    public void initialize() {
        addElementsToChart();
    }

    public void addElementsToChart() {
        pieChart.getData().clear();
        elements.getItems().clear();
        Loader.storage.calculateDistributionOfExpenses();
        ArrayList<Distribution> eDistData = Loader.storage.getDist();
        for (int i = 0; eDistData.size() > i; i++) {
            PieChart.Data tmp =
                    new PieChart.Data(
                            eDistData.get(i).getName(),
                            eDistData.get(i).getPercentage());
            pieChart.getData().add(tmp);
            addElementsToListView(eDistData.get(i).toString());
        }

    }

    public void addElementsToListView(String element) {
        elements.getItems().add(element);
    }

}
