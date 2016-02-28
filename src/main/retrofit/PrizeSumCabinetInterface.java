package main.retrofit;

import main.model.PrizeSumCabinetModel;
import main.query.PrizeSumCabinet;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;

import main.model.PrizeSumCabinetModel;
import java.sql.Date;
import java.time.LocalDate;

public interface PrizeSumCabinetInterface {
    @GET("/cabinet/{id}")
    PrizeSumCabinetModel getCabinetInfo(@Path("id") int cabinetId, LocalDate startDate, LocalDate finishDate);
}
