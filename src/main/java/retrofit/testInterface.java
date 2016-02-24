package retrofit;

import model.testModel;
import retrofit.http.GET;
import retrofit.http.Path;

import java.util.ArrayList;
import java.util.List;

public interface testInterface {
    @GET("/Masters")
    testModel[] getMaster();
}
