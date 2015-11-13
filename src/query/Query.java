package query;

import javafx.scene.layout.Pane;

public abstract class Query {
    public abstract void paneChange(Pane pane);

    public abstract String toString();
}
