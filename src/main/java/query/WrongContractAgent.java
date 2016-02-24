package query;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.WrongContractAgentModel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public final class WrongContractAgent extends Query {
    private static String comboBoxText = "Вывести договоры, " +
            "заключённые агентами по тем видам страхования, в которых они не разбираются";

    @Override
    public void paneChange(Pane pane, Connection connection) {
        pane.getChildren().removeAll(pane.getChildren());
    }

    public TableView getRightTable(Connection connection, String querySQL) throws SQLException {
            ArrayList<Integer> data = new ArrayList<>();
            ResultSet rs = connection.createStatement().executeQuery("SELECT Agents_agent_id FROM WrongAgentTypes;");
            while (rs.next()) {
                data.add(rs.getInt(1));
            }
            return super.getTable(connection, "SELECT agent_id,type FROM AgentsManageTypes WHERE agent_id = " + data.get(0) +";");
    }

    //@Override
    public TableView getTable(Connection connection, String querySQL) throws SQLException {
        return super.getTable(connection, "SELECT * FROM WrongAgentTypes;");




        /*
        WrongContractAgentInterface service = restAdapter.create(WrongContractAgentInterface.class);

        TableView<WrongContractAgentModel> table = new TableView<WrongContractAgentModel>();
        table.setEditable(false);

        TableColumn idColumn = new TableColumn("ID");
        idColumn.setCellValueFactory(
                new PropertyValueFactory<WrongContractAgentModel, Integer>("contractId"));
        TableColumn occurColumn = new TableColumn("Произошло");
        occurColumn.setCellValueFactory(
                new PropertyValueFactory<WrongContractAgentModel, Boolean>("isOccured"));
        TableColumn occurDateColumn = new TableColumn("Произошло");
        occurDateColumn.setCellValueFactory(
                new PropertyValueFactory<WrongContractAgentModel, Date>("occurDate"));
        TableColumn priceColumn = new TableColumn("Цена");
        priceColumn.setCellValueFactory(
                new PropertyValueFactory<WrongContractAgentModel, Integer>("price"));
        TableColumn prizeColumn = new TableColumn("Страховая выплата");
        prizeColumn.setCellValueFactory(
                new PropertyValueFactory<WrongContractAgentModel, Integer>("prize"));
        TableColumn startColumn = new TableColumn("Начало");
        startColumn.setCellValueFactory(
                new PropertyValueFactory<WrongContractAgentModel, Date>("startDate"));
        TableColumn expColumn = new TableColumn("Конец");
        expColumn.setCellValueFactory(
                new PropertyValueFactory<WrongContractAgentModel, Date>("expDate"));
        TableColumn clientColumn = new TableColumn("ID клиента");
        clientColumn.setCellValueFactory(
                new PropertyValueFactory<WrongContractAgentModel, Integer>("clientId"));
        TableColumn agentColumn = new TableColumn("ID агента");
        agentColumn.setCellValueFactory(
                new PropertyValueFactory<WrongContractAgentModel, Integer>("agentId"));
        TableColumn typeColumn = new TableColumn("Тип");
        typeColumn.setCellValueFactory(
                new PropertyValueFactory<WrongContractAgentModel, String>("type"));

        table.getColumns().addAll(idColumn, occurColumn, occurDateColumn, priceColumn,
                prizeColumn, startColumn, expColumn, clientColumn, agentColumn,
                typeColumn);


        ObservableList<WrongContractAgentModel> data = FXCollections.observableArrayList();
        try {
            data.setAll(service.getWrongContract());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Данные не найдены!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
        }
        table.setItems(data);
        return table;*/
    }

    @Override
    public String toString() {
        return comboBoxText;
    }
}
