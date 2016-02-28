package main.retrofit;

import main.model.GetTypesInsuranceModel;
import main.model.GetTypesInsuranceModel;
import retrofit.http.GET;
import retrofit.http.Path;

import main.model.GetTypesInsuranceModel;
import java.time.LocalDate;

public interface GetTypesInsuranceInterface {
    @GET("/Profitability")
    GetTypesInsuranceModel[] getTypesInfo(@Path("startDate") LocalDate startDate, @Path("expDate") LocalDate finishDate);
}
