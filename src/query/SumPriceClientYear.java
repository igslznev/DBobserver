package query;

import formElement.NumberTextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.util.Date;

/**
 * Created by igor on 13.11.15.
 */
public final class SumPriceClientYear extends Query {
    private static String comboBoxText = "Суммарную стоимость договоров с клиентами, которые старше заданного возраста по каждому виду страхования";
    private static Date startTime, finishTime;
    private static Label startTimeLabel = new Label("C"),
            finishTimeLabel = new Label("По"), yearLabel = new Label("Старше указанного \nгода рождения");
    private static DatePicker startTimeField = new DatePicker(),
            finishTimeField = new DatePicker();
    private static NumberTextField yearField = new NumberTextField();


    @Override
    public void paneChange(Pane pane) {
        pane.getChildren().removeAll(pane.getChildren());
        startTimeLabel.relocate(10, 0);
        startTimeField.relocate(10, 15);
        startTimeField.setEditable(false);
        finishTimeLabel.relocate(10, 40);
        finishTimeField.relocate(10, 55);
        finishTimeField.setEditable(false);
        yearLabel.relocate(10, 80);
        yearField.relocate(10, 115);
        pane.getChildren().addAll(startTimeLabel, startTimeField,
                finishTimeLabel, finishTimeField, yearLabel, yearField);
    }

    @Override
    public String toString() {
        return comboBoxText;
    }
}
