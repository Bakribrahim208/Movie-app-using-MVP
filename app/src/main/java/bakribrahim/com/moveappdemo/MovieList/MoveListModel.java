package bakribrahim.com.moveappdemo.MovieList;

import android.util.Log;

import java.util.List;

import bakribrahim.com.moveappdemo.Models.Movie;
import bakribrahim.com.moveappdemo.Models.MovieListResponse;
import bakribrahim.com.moveappdemo.Network.apiclient;
import bakribrahim.com.moveappdemo.Network.networkAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoveListModel implements MovieListContract.Model {
    @Override
    public void getMovieList(final MovieListContract.Model.OnFinishedListener onFinishedListener, int pageNo) {
        networkAPI apiService = apiclient.getInstance().create(networkAPI.class);

        Call<MovieListResponse> call = apiService.getPopularMovies("777660159186d81259c9dcfa910ad0f1", pageNo);
        call.enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
               if (response==null)
               {
                   Log.d("errorrr","response==null");

               }
                   else
               {
                   try {
                       List<Movie> movies = response.body().getResults();

                       onFinishedListener.onFinished(movies);
                       Log.d("errorrr", String.valueOf(response.body().getResults()));

                   }
                   catch (Exception ex){
                       Log.d("errorrr", ex.getMessage());

                   }
               }

            }

            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });


    }


}
