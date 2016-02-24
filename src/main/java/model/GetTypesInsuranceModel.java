package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetTypesInsuranceModel {
    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("profitability")
    @Expose
    private int profitability;

    @SerializedName("bornYear")
    @Expose
    private int bornYear;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getProfitability() {
        return profitability;
    }

    public void setProfitability(int profitability) {
        this.profitability = profitability;
    }

    public int getBornYear() {
        return bornYear;
    }

    public void setBornYear(int bornYear) {
        this.bornYear = bornYear;
    }
}
