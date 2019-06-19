package bakribrahim.com.moveappdemo.Models.Cast;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import bakribrahim.com.moveappdemo.Models.Movie.Movie;

public class castMovies {

    @SerializedName("cast")
    private ArrayList<Movie> cast;

    public ArrayList<Movie> getCast() {
        return cast;
    }

    public void setCast(ArrayList<Movie> cast) {
        this.cast = cast;
    }
}
