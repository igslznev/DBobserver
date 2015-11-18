package query;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.GetTypesInsuranceModel;
import retrofit.GetTypesInsuranceInterface;
import retrofit.RestAdapter;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public final class GetTypesInsurance extends Query {
    private static String comboBoxText = "Вывести типы страхований по их " +
            "рентабельности отдельно для каждой возрастной группы клиентов по десятилетиям";
    private static LocalDate startTime, finishTime;
    private static Label startTimeLabel = new Label("C"),
            finishTimeLabel = new Label("По");
    private static DatePicker startTimeField = new DatePicker(),
            finishTimeField = new DatePicker();

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

    @Override
    public TableView getTable(Connection connection, String querySQL) throws SQLException {
        if(!(startTimeField.getValue() == null || finishTimeField.getValue() == null) &&
                (startTimeField.getValue().isBefore(finishTimeField.getValue()))) {
            startTime = startTimeField.getValue();
            finishTime = finishTimeField.getValue();
        } else { return  null; }

        return super.getTable(connection,
                "SELECT type, profitability, bornDecade FROM Profitability WHERE startDate > \"" + startTimeField.getValue().toString() +
                        "\" AND expDate < \"" + finishTimeField.getValue().toString() +
                        "\";");

        /*GetTypesInsuranceInterface service = restAdapter.create(GetTypesInsuranceInterface.class);

        TableView<GetTypesInsuranceModel> table = new TableView<GetTypesInsuranceModel>();
        table.setEditable(false);

        TableColumn typeColumn = new TableColumn("Тип страхования");
        typeColumn.setCellValueFactory(
                new PropertyValueFactory<GetTypesInsuranceModel, String>("type"));
        TableColumn profitColumn = new TableColumn("Рентабельность");
        profitColumn.setCellValueFactory(
                new PropertyValueFactory<GetTypesInsuranceModel, String>("profitability"));
        TableColumn bornYearColumn = new TableColumn("Десятилетие");
        bornYearColumn.setCellValueFactory(
                new PropertyValueFactory<GetTypesInsuranceModel, Integer>("bornYear"));
        table.getColumns().addAll(typeColumn, profitColumn, bornYearColumn);

        ObservableList<GetTypesInsuranceModel> data = FXCollections.observableArrayList();
        try {
            data.setAll(service.getTypesInfo(startTime, finishTime));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Данные не найдены!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
        }

        table.setItems(data);

        return table;*/
    }
}
