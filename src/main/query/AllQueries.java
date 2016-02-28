package main.query;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class AllQueries { //класс множества запросов
    private ObservableList<Query> queryList = FXCollections.observableArrayList();

    public AllQueries (Query... args) {
        for (int i = 0; i < args.length; i++) {
            queryList.add(args[i]);
        }
    }

    public ObservableList<Query> getQueryList() {
        return queryList;
    }
}
