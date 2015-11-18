package retrofit;

import model.ClientContractInfoModel;
import retrofit.http.GET;
import retrofit.http.Path;

public interface ClientContractInfoInterface {
    @GET("/Clients/{name}")
    ClientContractInfoModel[] getClientInfo(@Path("name") String clientName);

}
