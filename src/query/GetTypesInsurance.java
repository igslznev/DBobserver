package query;

import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.util.Date;

public final class GetTypesInsurance extends Query {
    private static String comboBoxText = "Вывести типы страхований по их рентабельности отдельно для каждой возрастной группы клиентов по десятилетиям";
    private static Date startTime, finishTime;
    private static Label startTimeLabel = new Label("C"),
            finishTimeLabel = new Label("По");
    private static DatePicker startTimeField = new DatePicker(),
            finishTimeField = new DatePicker();

    public static String getComboBoxText() {
        return comboBoxText;
    }

    @Override public void paneChange(Pane pane) {
        pane.getChildren().removeAll(pane.getChildren());
        startTimeLabel.relocate(10, 0);
        startTimeField.relocate(10, 15);
        startTimeField.setEditable(false);
        finishTimeLabel.relocate(10, 40);
        finishTimeField.relocate(10, 55);
        finishTimeField.setEditable(false);
        pane.getChildren().addAll(startTimeLabel, startTimeField,
                finishTimeLabel, finishTimeField);
    }

    public String toString() {
        return comboBoxText;
    }
}
