package bakribrahim.com.moveappdemo.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import bakribrahim.com.moveappdemo.Models.Cast.Cast;

public class Credits {

    @SerializedName("cast")
    private List<Cast> cast;

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }
}
