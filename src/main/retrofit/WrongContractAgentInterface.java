package main.retrofit;

import main.model.WrongContractAgentModel;
import main.model.WrongContractAgentModel;
import retrofit.http.GET;

public interface WrongContractAgentInterface {
    @GET("/WrongAgentType")
    WrongContractAgentModel getWrongContract();
}