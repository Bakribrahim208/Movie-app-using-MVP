package bakribrahim.com.moveappdemo.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Review_Response {
    @SerializedName("results")
    private List<Review> Reviews;

    public List<Review> getReviews() {
        return Reviews;
    }

    public void setReviews(List<Review> Reviews) {
        this.Reviews = Reviews;
    }
}
