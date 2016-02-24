package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PrizeSumCabinetModel {
    @SerializedName("sumPrize")
    @Expose
    private int sumPrize;

    public int getSumPrize() {
        return sumPrize;
    }

    public void setSumPrize(int sumPrize) {
        this.sumPrize = sumPrize;
    }
}
