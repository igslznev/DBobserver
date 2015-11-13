package query;

import javafx.scene.layout.Pane;

/**
 * Created by igor on 13.11.15.
 */
public final class WrongContractAgent extends Query {
    private static String comboBoxText = "Вывести договоры, заключённые агентами по тем видам страхования, в которых они не разбираются";

    @Override
    public void paneChange(Pane pane) {
        pane.getChildren().removeAll(pane.getChildren());
    }

    @Override
    public String toString() {
        return comboBoxText;
    }
}
