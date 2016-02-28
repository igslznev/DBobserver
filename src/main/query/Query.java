package main.query;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import retrofit.RestAdapter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Query {
    public abstract void paneChange(Pane pane, Connection connection);

    public abstract String toString();

    public TableView getRightTable(Connection connection, String querySQL) throws SQLException{
        return null;
    }

    public TableView getTable(Connection connection, String querySQL) throws SQLException { // возвращает таблицу, заполненную данными из базы
        ResultSet rs = connection.createStatement().executeQuery(querySQL);

        TableView table = new TableView();
        table.setMinWidth(600);
        ObservableList<ObservableList> data = FXCollections.observableArrayList();


        for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
            final int j = i;
            TableColumn<ObservableList, String> column = new TableColumn(rs.getMetaData().getColumnName(i + 1));
            column.setCellValueFactory(param -> new SimpleStringProperty((String) param.getValue().get(j)));
            table.getColumns().add(column);
        }
        while (rs.next()) {
            ObservableList<Object> row = FXCollections.observableArrayList();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                row.add(rs.getString(i));
            }
            data.add(row);
        }

        //FINALLY ADDED TO TableView
        table.setItems(data);
        return table;
    }
}
