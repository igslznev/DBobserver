package query;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import formElement.NumberTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.PrizeSumCabinetModel;
import model.testModel;
import retrofit.PrizeSumCabinetInterface;
import retrofit.RestAdapter;
import retrofit.testInterface;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Created by igor on 13.11.15.
 */
public final class PrizeSumCabinet extends Query {
    private static String comboBoxText = "Определить сумму страховки, " +
            "выплаченной фирмой за указанный период времени по договорам агентов из заданного кабинета";
    private static LocalDate startTime, finishTime;
    private static int cabinetNum;
    private static Label startTimeLabel = new Label("C"),
            finishTimeLabel = new Label("По"), cabinetNumLabel = new Label("Кабинет №");
    private static DatePicker startTimeField = new DatePicker(),
            finishTimeField = new DatePicker();
    private static NumberTextField cabinetNumField = new NumberTextField();

    public TableView getTable(Connection connection, String querySQL) throws SQLException {

        if(!cabinetNumField.getText().isEmpty()) {
            cabinetNum = Integer.parseInt(cabinetNumField.getText());
        }
        else { return null; }
        if(!(startTimeField.getValue() == null || finishTimeField.getValue() == null) &&
                (startTimeField.getValue().isBefore(finishTimeField.getValue()))) {
            startTime = startTimeField.getValue();
            finishTime = finishTimeField.getValue();
        } else { return  null; }

        return super.getTable(connection,
                "SELECT SUM(prize) FROM CabinetPrizes WHERE cabinet = \"" + cabinetNumField.getText() +
                        "\" AND occurDate > \"" + startTimeField.getValue().toString() +
                        "\" AND occurDate < \"" + finishTimeField.getValue().toString() +
                        "\";");
        /*PrizeSumCabinetInterface service = restAdapter.create(PrizeSumCabinetInterface.class);

        TableView<PrizeSumCabinetModel> table = new TableView<PrizeSumCabinetModel>();
        table.setEditable(false);

        TableColumn sumColumn = new TableColumn("Сумма выплат");
        sumColumn.setCellValueFactory(
                new PropertyValueFactory<PrizeSumCabinetModel, Integer>("sumPrize"));
        table.getColumns().addAll(sumColumn);


        ObservableList<PrizeSumCabinetModel> data = FXCollections.observableArrayList();
        try {
            data.setAll(service.getCabinetInfo(cabinetNum, startTime, finishTime));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Данные не найдены!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
        }
        table.setItems(data);
        return table;*/
    }

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
