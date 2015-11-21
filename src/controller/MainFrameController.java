package controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import query.*;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;


public class MainFrameController {

    @FXML
    private ComboBox queryComboBox = new ComboBox();
    @FXML
    private Pane paneTable = new Pane();
    private Query currentQuery;
    private static DBController dbController = new DBController();

    private AllQueries allQueries = new AllQueries(new GetTypesInsurance(),
            new PrizeSumCabinet(), new ClientContractInfo(),
            new SumPriceClientYear(), new WrongContractAgent());
    @FXML
    private Pane queryParameterPane = new Pane();
    @FXML
    private Button startButton;
    @FXML
    private Button transactionButton;

    public static DBController getDBController() {
        return dbController;
    }

    @FXML
    private void onClickStartButton(Event event) {
        try {
            if (currentQuery == null) {
                throw new NullPointerException("Выберите запрос");
            }

            paneTable.getChildren().removeAll(paneTable.getChildren());

            /*RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(API)//В метод setEndpoint передаем адрес нашего сайта
                    .build();*/

            if (currentQuery.getTable(dbController.getConnection(), "") != null) {
                paneTable.getChildren().addAll(currentQuery.getTable(dbController.getConnection(), ""));
            } else {
                throw new NullPointerException("Данные не введены или не корректны");
            }

            /*RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(API)//В метод setEndpoint передаем адрес нашего сайта
                    .build();
            testInterface service = restAdapter.create(testInterface.class);

            TableView<testModel> masters = new TableView<testModel>();
            masters.setEditable(false);

            TableColumn idColumn = new TableColumn("Id");
            idColumn.setCellValueFactory(
                    new PropertyValueFactory<testModel, Integer>("iid"));
            TableColumn nameColumn = new TableColumn("Name");
            nameColumn.setCellValueFactory(
                    new PropertyValueFactory<testModel, String>("nname"));
            TableColumn surnameColumn = new TableColumn("Surname");
            surnameColumn.setCellValueFactory(
                    new PropertyValueFactory<testModel, String>("ssurname"));
            TableColumn patronymicColumn = new TableColumn("Patronymic");
            patronymicColumn.setCellValueFactory(
                    new PropertyValueFactory<testModel, String>("ppatronymic"));

            ObservableList<testModel> data = FXCollections.observableArrayList();

            try {
                data.addAll(service.getMaster());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Данные не найдены!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
            }

            masters.getColumns().addAll(idColumn, nameColumn, surnameColumn, patronymicColumn);

            masters.setItems(data);
            paneTable.getChildren().addAll(masters);*/

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Данные не найдены!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка!", JOptionPane.ERROR_MESSAGE);
        }


    }

    @FXML
    public void onActionComboBox(ActionEvent actionEvent) { // метод, изменяющий окно в зависимости от выбранного запроса
        try {
            currentQuery = (Query) queryComboBox.getValue();
            currentQuery.paneChange(queryParameterPane, dbController.getConnection());
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void onComboBoxClick(Event event) { // заполнение комбобокса
        queryComboBox.setItems(allQueries.getQueryList());
    }

    public void onClickConnectionButton(ActionEvent actionEvent) {
        try {
            dbController.connectToDB();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Успех!");
            alert.setHeaderText(null);
            alert.setContentText("Подключение установлено!");
            queryComboBox.setDisable(false);
            startButton.setDisable(false);
            transactionButton.setDisable(false);

            alert.showAndWait();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void onTransactionButtonClick(ActionEvent actionEvent) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/transactionframe.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Транзакции");
            stage.setScene(new Scene(root, 335, 360));
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
