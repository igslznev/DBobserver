package main.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class testModel {
    @SerializedName("id")
    @Expose
    private int iid;

    @SerializedName("name")
    @Expose
    private String nname;

    @SerializedName("surname")
    @Expose
    private String ssurname;

    @SerializedName("patronymic")
    @Expose
    private String ppatronymic;

    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    public String getNname() {
        return nname;
    }

    public void setNname(String nname) {
        this.nname = nname;
    }

    public String getSsurname() {
        return ssurname;
    }

    public void setSsurname(String ssurname) {
        this.ssurname = ssurname;
    }

    public String getPpatronymic() {
        return ppatronymic;
    }

    public void setPpatronymic(String ppatronymic) {
        this.ppatronymic = ppatronymic;
    }
}
