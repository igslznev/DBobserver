package controller;

import formElement.NumberTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import transaction.AddContract;
import transaction.ChangeCabinets;

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
    private ChangeCabinets changeCabinets = new ChangeCabinets(dbController);
    private AddContract addContract = new AddContract(dbController);


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
                changeCabinets.changeCabinetsTransaction(
                        Integer.parseInt(firstAgentField.getText()),
                        Integer.parseInt(secondAgentField.getText()));
                alert.show();
            } else {
                JOptionPane.showMessageDialog(null, "Данные не введены или не корректны!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
            }
        }

        if(framegroup.getSelectedToggle() == addContractToggleButton) {
            if(!(startDateField.getValue() == null || expDateField.getValue() == null) &&
                    (startDateField.getValue().isBefore(expDateField.getValue())) && priceField.getText() != null &&
                    clientIdField.getText() != null && agentIdField.getText() != null
                    && typeChoise.getValue().toString() != null) {

                    String type = "";
                    for (int i = 1; i < typeChoise.getValue().toString().length() - 1; i++) {
                        type += typeChoise.getValue().toString().charAt(i);
                    }

                    addContract.addContractTransaction(priceField.getText(), startDateField.getValue(), expDateField.getValue(),
                            clientIdField.getText(), agentIdField.getText(), type);
                    alert.show();
            } else {
                JOptionPane.showMessageDialog(null, "Данные не введены или не корректны!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
            }

        }


    }

    public void onAgentChange(Event event) {
        if (agentIdField.getText() != "") {
            try {
                ObservableList<ObservableList> data = FXCollections.observableArrayList();
                ResultSet rs = dbController.getConnection().createStatement().executeQuery(
                        "SELECT type FROM AgentsManageTypes WHERE agent_id = " + Integer.parseInt(agentIdField.getText()) + ";");
                while (rs.next()) {
                    ObservableList<Object> row = FXCollections.observableArrayList();
                    row.add(rs.getString(1).toString());
                    data.add(row);
                }


                typeChoise.setItems(data);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
