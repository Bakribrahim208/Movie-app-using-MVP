package bakribrahim.com.moveappdemo.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CastResponse {
    @SerializedName("cast")
    private List<Cast> Cast;

    public List<Cast> getCast() {
        return Cast;
    }

    public void setReviews(List<Cast> Cast) {
        this.Cast = Cast;
    }
}
