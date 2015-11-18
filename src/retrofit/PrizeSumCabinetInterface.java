package retrofit;

import model.PrizeSumCabinetModel;
import query.PrizeSumCabinet;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;

import java.sql.Date;
import java.time.LocalDate;

public interface PrizeSumCabinetInterface {
    @GET("/cabinet/{id}")
    PrizeSumCabinetModel getCabinetInfo(@Path("id") int cabinetId, LocalDate startDate, LocalDate finishDate);
}
