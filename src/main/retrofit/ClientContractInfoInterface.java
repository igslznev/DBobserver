package main.retrofit;

import main.model.ClientContractInfoModel;
import retrofit.http.GET;
import retrofit.http.Path;

import main.model.ClientContractInfoModel;

public interface ClientContractInfoInterface {
    @GET("/Clients/{name}")
    ClientContractInfoModel[] getClientInfo(@Path("name") String clientName);

}
