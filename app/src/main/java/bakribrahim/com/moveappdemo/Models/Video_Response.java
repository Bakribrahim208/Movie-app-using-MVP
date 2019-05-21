package bakribrahim.com.moveappdemo.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Video_Response {
    @SerializedName("results")
    private List< Video> Videos;

    public List<Video> getVideos() {
        return Videos;
    }

    public void setVideos(List<Video> Videos) {
        this.Videos = Videos;
    }
}
