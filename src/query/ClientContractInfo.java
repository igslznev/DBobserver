package query;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public final class ClientContractInfo extends Query {
    private static String comboBoxText = "Вывести полную информацию обо всех договорах данного клиента данного клиента";
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
    public String toString() {
        return comboBoxText;
    }

}
