package query;

import formElement.NumberTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.SumPriceClientYearModel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public final class SumPriceClientYear extends Query {
    private static String comboBoxText = "Суммарную стоимость договоров с клиентами, " +
            "которые старше заданного возраста по каждому виду страхования";
    private static LocalDate startTime, finishTime;
    private static int bornYear;
    private static Label startTimeLabel = new Label("C"),
            finishTimeLabel = new Label("По"), yearLabel = new Label("Старше указанного \nгода рождения");
    private static DatePicker startTimeField = new DatePicker(),
            finishTimeField = new DatePicker();
    private static NumberTextField yearField = new NumberTextField();


    @Override
    public void paneChange(Pane pane, Connection connection) {
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

    @Override
    public TableView getTable(Connection connection, String querySQL) throws SQLException {
        if(!yearField.getText().isEmpty()) {
            bornYear = Integer.parseInt(yearField.getText());
        }
        else { return null; }
        if(!(startTimeField.getValue() == null || finishTimeField.getValue() == null) &&
                (startTimeField.getValue().isBefore(finishTimeField.getValue()))) {
            startTime = startTimeField.getValue();
            finishTime = finishTimeField.getValue();
        } else { return  null; }

        return super.getTable(connection,
                "SELECT sum(price), type FROM PriceSumClientYear WHERE (startDate > \"" + startTimeField.getValue().toString() +
                        "\" AND expDate < \"" + finishTimeField.getValue().toString() +
                        "\" AND bornYear < \"" + bornYear +
                "\") GROUP BY type;");

        /*SumPriceClientYearInterface service = restAdapter.create(SumPriceClientYearInterface.class);

        TableView<SumPriceClientYearModel> table = new TableView<SumPriceClientYearModel>();
        table.setEditable(false);

        TableColumn sumColumn = new TableColumn("Сумма выплат");
        sumColumn.setCellValueFactory(
                new PropertyValueFactory<SumPriceClientYearModel, Integer>("sumPrice"));
        TableColumn typeColumn = new TableColumn("Тип страхования");
        typeColumn.setCellValueFactory(
                new PropertyValueFactory<SumPriceClientYearModel, String>("type"));
        table.getColumns().addAll(sumColumn, typeColumn);


        ObservableList<SumPriceClientYearModel> data = FXCollections.observableArrayList();
        try {
            data.setAll(service.getSum(bornYear, startTime, finishTime));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Данные не найдены!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
        }
        table.setItems(data);
        return table;*/
    }
}
