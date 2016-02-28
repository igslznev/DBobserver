package main.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class ClientContractInfoModel {
    @SerializedName("contract_id")
    @Expose
    private int contractId;

    @SerializedName("isOccured")
    @Expose
    private boolean isOccured;

    @SerializedName("occurDate")
    @Expose
    private Date occurDate;

    @SerializedName("price")
    @Expose
    private int price;

    @SerializedName("prize")
    @Expose
    private int prize;

    @SerializedName("startDate")
    @Expose
    private Date startDate;

    @SerializedName("expDate")
    @Expose
    private Date expDate;

    @SerializedName("Clients_client_id")
    @Expose
    private int clientId;

    @SerializedName("Agents_agent_id")
    @Expose
    private int agentId;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("agentAdress")
    @Expose
    private String agentAdress;

    @SerializedName("cabinetPhone")
    @Expose
    private String agentPhone;

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public boolean isOccured() {
        return isOccured;
    }

    public void setOccured(boolean occured) {
        isOccured = occured;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAgentAdress() {
        return agentAdress;
    }

    public void setAgentAdress(String agentAdress) {
        this.agentAdress = agentAdress;
    }

    public String getAgentPhone() {
        return agentPhone;
    }

    public void setAgentPhone(String agentPhone) {
        this.agentPhone = agentPhone;
    }

    public Date getOccurDate() {
        return occurDate;
    }

    public void setOccurDate(Date occurDate) {
        this.occurDate = occurDate;
    }
}
