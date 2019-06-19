package bakribrahim.com.moveappdemo.Network;

import bakribrahim.com.moveappdemo.Models.Cast.CastResponse;
import bakribrahim.com.moveappdemo.Models.Movie.Movie;
import bakribrahim.com.moveappdemo.Models.Movie.MovieListResponse;
import bakribrahim.com.moveappdemo.Models.Reviews.Review_Response;
import bakribrahim.com.moveappdemo.Models.Videos.Video_Response;
import bakribrahim.com.moveappdemo.Models.Cast.castMovies;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface networkAPI {
    @GET("movie/{moviestype}")

    Call<MovieListResponse>
    getPopularMovies(@Path("moviestype") String moviestype,@Query("api_key") String apiKey, @Query("page") int PageNo );

    @GET("movie/{movie_id}")
    Call<Movie>
    getMovieDetails(@Path("movie_id") int movieId, @Query("api_key") String apiKey, @Query("append_to_response") String credits);

    @GET("movie/{movie_id}/casts")
    Call<CastResponse> getMovieCast( @Path("movie_id") int id ,@Query("api_key") String apiKEy);

    @GET("person/{cast_id}/movie_credits")
    Call<castMovies> getCastMovies(@Path("cast_id") int id, @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/videos")
    Call<Video_Response>
    getMovieVideos( @Path("movie_id") int id ,@Query("api_key") String apiKEy);


    @GET("movie/{movie_id}/reviews")
    Call<Review_Response>
    getMovieReviews( @Path("movie_id") int id ,@Query("api_key") String apiKEy);



}
