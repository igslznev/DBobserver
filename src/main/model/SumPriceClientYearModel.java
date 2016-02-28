package main.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SumPriceClientYearModel {
    @SerializedName("sumPrice")
    @Expose
    private int sumPrice;

    @SerializedName("type")
    @Expose
    private String type;

    public int getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(int sumPrice) {
        this.sumPrice = sumPrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
