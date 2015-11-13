package controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import query.*;

import javax.swing.*;


public class MainFrameController {
    @FXML
    private ComboBox queryComboBox = new ComboBox();
    @FXML
    private TableView queryTable = new TableView();
    private RestController restController = new RestController();
    private AllQueries allQueries = new AllQueries(new GetTypesInsurance(),
            new PrizeSumCabinet(), new ClientContractInfo(),
            new SumPriceClientYear(), new WrongContractAgent());;
    @FXML
    private Pane queryParameterPane = new Pane();
    @FXML
    private void onClickStartButton(Event event) {

    }

    @FXML
    public void onActionComboBox(ActionEvent actionEvent) { // метод, изменяющий окно в зависимости от выбранного запроса
        try {
            Query temp = (Query) queryComboBox.getValue();
            temp.paneChange(queryParameterPane);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void onComboBoxClick(Event event) { // заполнение комбобокса
        queryComboBox.setItems(allQueries.getQueryList());
    }
}
