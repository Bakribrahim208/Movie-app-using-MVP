package bakribrahim.com.moveappdemo.MovieDetails;

import android.util.Log;

import java.util.List;

import bakribrahim.com.moveappdemo.Models.Cast;
import bakribrahim.com.moveappdemo.Models.CastResponse;
import bakribrahim.com.moveappdemo.Models.Review;
import bakribrahim.com.moveappdemo.Models.Review_Response;
import bakribrahim.com.moveappdemo.Models.Video;
import bakribrahim.com.moveappdemo.Models.Video_Response;
import bakribrahim.com.moveappdemo.Network.apiclient;
import bakribrahim.com.moveappdemo.Network.networkAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsModel implements MovieDetailsContract.model_details {


    @Override
    public void getCastList(final onFinishedListener onFinishedListener, int MovieID) {

        networkAPI apiService =
                apiclient.getInstance().create(networkAPI.class);

        Call<CastResponse> call = apiService.getMovieCast
                (MovieID,"777660159186d81259c9dcfa910ad0f1");
        call.enqueue(new Callback<CastResponse>() {
            @Override
            public void onResponse(Call<CastResponse> call, Response<CastResponse> response) {
                if (response==null)
                {
                    Log.d("errorrr","response==null");

                }
                else
                {
                    try {
                        List<Cast> movies = response.body().getCast();
                        onFinishedListener.onFinished_Cast(movies);

                    }
                    catch (Exception ex){
                        Log.d("errorrr", ex.getMessage());

                    }
                }

            }

            @Override
            public void onFailure(Call<CastResponse> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
    }

    @Override
    public void getVideo(final onFinishedListener onFinishedListener, int MovieID) {

        networkAPI apiService = apiclient.getInstance().create(networkAPI.class);

        Call<Video_Response> call = apiService.getMovieVideos
                (MovieID,"777660159186d81259c9dcfa910ad0f1");

        call.enqueue(new Callback<Video_Response>() {
            @Override
            public void onResponse(Call<Video_Response> call, Response<Video_Response> response) {
                if (response==null)
                {
                    Log.d("onFinished_Video","response==null");

                }
                else
                {
                    try {
                        List<Video> Videos = response.body().getVideos();
                        onFinishedListener.onFinished_Video(Videos);
                        Log.d("onFinished_Video","Size????  )"+Videos.size());


                    }
                    catch (Exception ex){
                        Log.d("onFinished_Video","Exception"+ ex.getMessage());
                    }
                }

            }

            @Override
            public void onFailure(Call<Video_Response> call, Throwable t) {
                onFinishedListener.onFailure(t);
                Log.d("onFailure",""+t.getMessage());

            }
        });
    }

    @Override
    public void getReviews(final onFinishedListener onFinishedListener, int MovieID) {
        networkAPI apiService = apiclient.getInstance().create(networkAPI.class);

        Call<Review_Response> call = apiService.getMovieReviews
                (MovieID,"777660159186d81259c9dcfa910ad0f1");

        call.enqueue(new Callback<Review_Response>() {
            @Override
            public void onResponse(Call<Review_Response> call, Response<Review_Response> response) {
                if (response==null)
                {
                    Log.d("onFinished_Video","response==null");

                }
                else
                {
                    try {
                        List<Review> Reviews = response.body().getReviews();
                        onFinishedListener.onFinished_Reviews(Reviews);
                        Log.d("onFinished_Video","Size????  )"+Reviews.size());


                    }
                    catch (Exception ex){
                        Log.d("onFinished_Video","Exception"+ ex.getMessage());
                    }
                }

            }

            @Override
            public void onFailure(Call<Review_Response> call, Throwable t) {
                onFinishedListener.onFailure(t);
                Log.d("onFailure",""+t.getMessage());

            }
        });
    }
}
