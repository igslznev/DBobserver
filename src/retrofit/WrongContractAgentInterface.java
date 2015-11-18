package retrofit;

import model.WrongContractAgentModel;
import retrofit.http.GET;

public interface WrongContractAgentInterface {
    @GET("/WrongAgentType")
    WrongContractAgentModel getWrongContract();
}