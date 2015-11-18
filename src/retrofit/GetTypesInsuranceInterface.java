package retrofit;

import model.GetTypesInsuranceModel;
import retrofit.http.GET;
import retrofit.http.Path;

import java.time.LocalDate;

public interface GetTypesInsuranceInterface {
    @GET("/Profitability")
    GetTypesInsuranceModel[] getTypesInfo(@Path("startDate") LocalDate startDate, @Path("expDate") LocalDate finishDate);
}
