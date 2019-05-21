package bakribrahim.com.moveappdemo.Network;

import bakribrahim.com.moveappdemo.Models.Cast;
import bakribrahim.com.moveappdemo.Models.CastResponse;
import bakribrahim.com.moveappdemo.Models.Movie;
import bakribrahim.com.moveappdemo.Models.MovieListResponse;
import bakribrahim.com.moveappdemo.Models.Review;
import bakribrahim.com.moveappdemo.Models.Review_Response;
import bakribrahim.com.moveappdemo.Models.Video_Response;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface networkAPI {
    @GET("movie/popular")

    Call<MovieListResponse>
    getPopularMovies(@Query("api_key") String apiKey, @Query("page") int PageNo);

    @GET("movie/{movie_id}")
    Call<Movie>
    getMovieDetails(@Path("movie_id") int movieId, @Query("api_key") String apiKey, @Query("append_to_response") String credits);

    @GET("movie/{movie_id}/casts")
    Call<CastResponse>
    getMovieCast( @Path("movie_id") int id ,@Query("api_key") String apiKEy);


    @GET("movie/{movie_id}/videos")
    Call<Video_Response>
    getMovieVideos( @Path("movie_id") int id ,@Query("api_key") String apiKEy);


    @GET("movie/{movie_id}/reviews")
    Call<Review_Response>
    getMovieReviews( @Path("movie_id") int id ,@Query("api_key") String apiKEy);



}
