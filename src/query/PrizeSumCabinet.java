package query;

import formElement.NumberTextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.util.Date;

/**
 * Created by igor on 13.11.15.
 */
public final class PrizeSumCabinet extends Query {
    private static String comboBoxText = "Определить сумму страховки, выплаченной фирмой за указанный период времени по договорам агентов из заданного кабинета";
    private static Date startTime, finishTime;
    private static Label startTimeLabel = new Label("C"),
            finishTimeLabel = new Label("По"), cabinetNumLabel = new Label("Кабинет №");
    private static DatePicker startTimeField = new DatePicker(),
            finishTimeField = new DatePicker();
    private static NumberTextField cabinetNumField = new NumberTextField();

    @Override
    public void paneChange(Pane pane) {
        pane.getChildren().removeAll(pane.getChildren());
        startTimeLabel.relocate(10, 0);
        startTimeField.relocate(10, 15);
        startTimeField.setEditable(false);
        finishTimeLabel.relocate(10, 40);
        finishTimeField.relocate(10, 55);
        finishTimeField.setEditable(false);
        cabinetNumLabel.relocate(10, 80);
        cabinetNumField.relocate(10, 95);
        pane.getChildren().addAll(startTimeLabel, startTimeField,
                finishTimeLabel, finishTimeField, cabinetNumLabel, cabinetNumField);
    }

    public String toString() {
        return comboBoxText;
    }
}
