package controller;

import formElement.NumberTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class TransactionFrameController {
    @FXML
    private NumberTextField secondAgentField;
    @FXML
    private NumberTextField firstAgentField;
    @FXML
    private Pane contractPane;
    @FXML
    private Pane cabinetPane;
    @FXML
    private DatePicker startDateField;
    @FXML
    private DatePicker expDateField;
    @FXML
    private NumberTextField priceField;
    @FXML
    private NumberTextField clientIdField;
    @FXML
    private NumberTextField agentIdField;
    @FXML
    private ChoiceBox typeChoise;
    @FXML
    private ToggleButton changeToggleButton;
    @FXML
    private ToggleButton addContractToggleButton;
    @FXML
    private ToggleGroup framegroup;
    private DBController dbController = MainFrameController.getDBController();

    private void changeCabinetsTransaction(int firstAgentId, int secondAgentId) throws SQLException {
        int firstCabinet = 0;
        ResultSet rs = dbController.getConnection().createStatement().
                executeQuery("SELECT Cabinets_cabinet_id FROM Agents WHERE agent_id = " + firstAgentId + ";");
        while (rs.next()) {
            firstCabinet = rs.getInt(1);
        }
        int secondCabinet = 0;
        rs = dbController.getConnection().createStatement().
                executeQuery("SELECT Cabinets_cabinet_id FROM Agents WHERE agent_id = " + secondAgentId + ";");
        while (rs.next()) {
            secondCabinet = rs.getInt(1);
        }

        if(firstCabinet == 0 || secondCabinet == 0) throw new SQLException("Нет агентов с таким ID!");


        dbController.getConnection().setAutoCommit(false);

        PreparedStatement statementFirst = dbController.getConnection().prepareStatement
                ("UPDATE Agents SET Agents.Cabinets_cabinet_id = ? WHERE Agents.agent_id = ?;\n");

        statementFirst.setInt(1, firstCabinet);
        statementFirst.setInt(2, secondAgentId);
        statementFirst.executeUpdate();// данные не обновлены!

        PreparedStatement statementSecond = dbController.getConnection().prepareStatement
                ("UPDATE Agents SET Agents.Cabinets_cabinet_id = ? WHERE Agents.agent_id = ?;");
        statementSecond.setInt(1, secondCabinet);
        statementSecond.setInt(2, firstAgentId);
        statementSecond.executeUpdate();

        dbController.getConnection().commit();
    }

    private void addContractTransaction(String price, LocalDate startDate, LocalDate expDate, String clientId, String agentId, String type) throws SQLException {
        dbController.getConnection().setAutoCommit(false);
        PreparedStatement statement = dbController.getConnection().prepareStatement(
                "INSERT INTO Contracts (isOccured, price, prize, startDate, expDate, Clients_client_id, Agents_agent_id, type)\n" +
                        "VALUES (0, ?, 1.2 * ?, ?, ?, ?, ?, ?);");
        statement.setInt(1, Integer.parseInt(price));
        statement.setInt(2, Integer.parseInt(price));
        //String startDateString = "\"" + startDate.toString() + "\"";
        statement.setString(3, startDate.toString());
        //String expDateString = "\"" + expDate.toString() + "\"";
        statement.setString(4, expDate.toString());
        statement.setInt(5, Integer.parseInt(clientId));
        statement.setInt(6, Integer.parseInt(agentId));
        //String typeString = "\"" + type + "\"";
        statement.setString(7, type);

        statement.executeUpdate();

        dbController.getConnection().commit();
    }

    private void paneChange() {
        contractPane.setVisible(false);
        cabinetPane.setVisible(false);
        if(framegroup.getSelectedToggle() == changeToggleButton) {
            cabinetPane.setVisible(true);
        }
        if(framegroup.getSelectedToggle() == addContractToggleButton) {
            contractPane.setVisible(true);
        }
    }

    public void onAddContractClick(ActionEvent actionEvent) {
        paneChange();
    }

    public void onChangeClick(ActionEvent actionEvent) {
        paneChange();
    }

    public void onStartClick(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Успех!");
        alert.setHeaderText(null);
        alert.setContentText("Транзакция подтверждена!");

        if(framegroup.getSelectedToggle() == changeToggleButton) {
            if(firstAgentField.getText() != null && secondAgentField.getText() != null) {
                try {
                    changeCabinetsTransaction(Integer.parseInt(firstAgentField.getText()), Integer.parseInt(secondAgentField.getText()));
                    alert.show();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка!", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Данные не введены или не корректны!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
            }
        }

        if(framegroup.getSelectedToggle() == addContractToggleButton) {
            if(!(startDateField.getValue() == null || expDateField.getValue() == null) &&
                    (startDateField.getValue().isBefore(expDateField.getValue())) && priceField.getText() != null &&
                    clientIdField.getText() != null && agentIdField.getText() != null
                    && typeChoise.getValue().toString() != null) {
                try {
                    addContractTransaction(priceField.getText(), startDateField.getValue(), expDateField.getValue(),
                            clientIdField.getText(), agentIdField.getText(), typeChoise.getValue().toString());
                    alert.show();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка!", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Данные не введены или не корректны!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
            }

        }


    }
}
