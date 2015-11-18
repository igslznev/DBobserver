package retrofit;

import model.SumPriceClientYearModel;
import retrofit.http.GET;
import retrofit.http.Path;

import java.time.LocalDate;

public interface SumPriceClientYearInterface {
    @GET("Clients/summary")
    SumPriceClientYearModel[] getSum(@Path("bornYear") int bornYear,
                                     @Path("")LocalDate startDate,
                                     @Path("") LocalDate finishDate);
}
