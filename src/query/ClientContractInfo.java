package query;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import model.ClientContractInfoModel;
import retrofit.ClientContractInfoInterface;
import retrofit.RestAdapter;

import javax.swing.*;
import java.sql.*;

public final class ClientContractInfo extends Query {
    private static String comboBoxText = "Вывести полную информацию обо всех " +
            "договорах данного клиента данного клиента";
    private static Label clientLabel = new Label("Имя клиента");
    private static TextField clientField = new TextField();

    @Override
    public void paneChange(Pane pane) {
        pane.getChildren().removeAll(pane.getChildren());
        clientLabel.relocate(10, 0);
        clientField.relocate(10, 15);
        pane.getChildren().addAll(clientLabel, clientField);
    }

    @Override
    public TableView getTable(Connection connection, String querySQL) throws SQLException {
        if(clientField.getText().isEmpty()) {
            return null;
        }
        return super.getTable(connection,
                "SELECT * FROM ClientContractInfo WHERE name = \"" + clientField.getText() + "\";");







        /*TableView<ClientContractInfoModel> table = new TableView<ClientContractInfoModel>();
        table.setEditable(false);

        TableColumn idColumn = new TableColumn("ID");
        idColumn.setCellValueFactory(
                new PropertyValueFactory<ClientContractInfoModel, Integer>("contractId"));
        TableColumn occurColumn = new TableColumn("Произошло");
        occurColumn.setCellValueFactory(
                new PropertyValueFactory<ClientContractInfoModel, Boolean>("isOccured"));
        TableColumn occurDateColumn = new TableColumn("Произошло");
        occurDateColumn.setCellValueFactory(
                new PropertyValueFactory<ClientContractInfoModel, Date>("occurDate"));
        TableColumn priceColumn = new TableColumn("Цена");
        priceColumn.setCellValueFactory(
                new PropertyValueFactory<ClientContractInfoModel, Integer>("price"));
        TableColumn prizeColumn = new TableColumn("Страховая выплата");
        prizeColumn.setCellValueFactory(
                new PropertyValueFactory<ClientContractInfoModel, Integer>("prize"));
        TableColumn startColumn = new TableColumn("Начало");
        startColumn.setCellValueFactory(
                new PropertyValueFactory<ClientContractInfoModel, Date>("startDate"));
        TableColumn expColumn = new TableColumn("Конец");
        expColumn.setCellValueFactory(
                new PropertyValueFactory<ClientContractInfoModel, Date>("expDate"));
        TableColumn clientColumn = new TableColumn("ID клиента");
        clientColumn.setCellValueFactory(
                new PropertyValueFactory<ClientContractInfoModel, Integer>("clientId"));
        TableColumn agentColumn = new TableColumn("ID агента");
        agentColumn.setCellValueFactory(
                new PropertyValueFactory<ClientContractInfoModel, Integer>("agentId"));
        TableColumn typeColumn = new TableColumn("Тип");
        typeColumn.setCellValueFactory(
                new PropertyValueFactory<ClientContractInfoModel, String>("type"));
        TableColumn agentAdressColumn = new TableColumn("Адрес агента");
        agentAdressColumn.setCellValueFactory(
                new PropertyValueFactory<ClientContractInfoModel, String>("agentAdress"));
        TableColumn agentPhoneColumn = new TableColumn("Телефон агента");
        agentPhoneColumn.setCellValueFactory(
                new PropertyValueFactory<ClientContractInfoModel, String>("agentPhone"));

        table.getColumns().addAll(idColumn, occurColumn, priceColumn,
                prizeColumn, startColumn, expColumn, clientColumn, agentColumn,
                typeColumn, agentAdressColumn, agentPhoneColumn);


        try {
            data.addAll(service.getClientInfo(clientField.getText()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Данные не найдены!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
        }
        table.setItems(data);*/
    }

    @Override
    public String toString() {
        return comboBoxText;
    }

}
