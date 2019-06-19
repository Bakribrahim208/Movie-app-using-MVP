package bakribrahim.com.moveappdemo.MovieDetails.MVP.models;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import bakribrahim.com.moveappdemo.Models.Cast.Cast;
import bakribrahim.com.moveappdemo.Models.Cast.CastResponse;
import bakribrahim.com.moveappdemo.Models.Movie.FavouriteMovie;
import bakribrahim.com.moveappdemo.Models.Movie.Movie;
import bakribrahim.com.moveappdemo.Models.Reviews.Review;
import bakribrahim.com.moveappdemo.Models.Reviews.Review_Response;
import bakribrahim.com.moveappdemo.Models.Videos.Video;
import bakribrahim.com.moveappdemo.Models.Videos.Video_Response;
import bakribrahim.com.moveappdemo.Models.Cast.castMovies;
import bakribrahim.com.moveappdemo.MovieDetails.MVP.MovieDetailsContract;
import bakribrahim.com.moveappdemo.Network.apiclient;
import bakribrahim.com.moveappdemo.Network.networkAPI;
import bakribrahim.com.moveappdemo.Utilites.Util;
import bakribrahim.com.moveappdemo.localDatabase.localDatabase;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsModel implements MovieDetailsContract.model_details {
   localDatabase localDatabase;
   public MovieDetailsModel(Application application){
       localDatabase=new localDatabase(application);
   }
    @Override
    public void getCastList(final onFinishedListener onFinishedListener, int MovieID) {

        networkAPI apiService =
                apiclient.getInstance().create(networkAPI.class);

        Call<CastResponse> call = apiService.getMovieCast
                (MovieID, Util.ApiKey);
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
                        onFinishedListener.onFailure(ex, Util.Error_cast);

                    }
                }

            }

            @Override
            public void onFailure(Call<CastResponse> call, Throwable t) {
                onFinishedListener.onFailure(t, Util.Error_cast);
            }
        });
    }

    @Override
    public void getVideo(final onFinishedListener onFinishedListener, int MovieID) {

        networkAPI apiService = apiclient.getInstance().create(networkAPI.class);

        Call<Video_Response> call = apiService.getMovieVideos
                (MovieID, Util.ApiKey);

        call.enqueue(new Callback<Video_Response>() {
            @Override
            public void onResponse(Call<Video_Response> call, Response<Video_Response> response) {
                if (response==null)
                {
                    Log.d("onFinished_Video","response==null");
                    onFinishedListener.onFailure(new Exception(), Util.Error_Trails);

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
                        onFinishedListener.onFailure(ex, Util.Error_Trails);

                    }
                }

            }

            @Override
            public void onFailure(Call<Video_Response> call, Throwable t) {
                onFinishedListener.onFailure(t, Util.Error_Trails);
                Log.d("onFailure",""+t.getMessage());

            }
        });
    }

    @Override
    public void getReviews(final onFinishedListener onFinishedListener, int MovieID) {
        networkAPI apiService = apiclient.getInstance().create(networkAPI.class);

        Call<Review_Response> call = apiService.getMovieReviews
                (MovieID, Util.ApiKey);

        call.enqueue(new Callback<Review_Response>() {
            @Override
            public void onResponse(Call<Review_Response> call, Response<Review_Response> response) {
                if (response==null)
                {
                    Log.d("onFinished_Video","response==null");
                    onFinishedListener.onFailure(new Exception(), Util.Error_Review);

                }
                else
                {
                    try {
                        List<Review> Reviews = response.body().getReviews();
                        onFinishedListener.onFinished_Reviews(Reviews);
                        Log.d("onFinished_Video","Size????  )"+Reviews.size());


                    }
                    catch (Exception ex){
                        onFinishedListener.onFailure(ex, Util.Error_Review);
                    }
                }

            }

            @Override
            public void onFailure(Call<Review_Response> call, Throwable t) {
                  onFinishedListener.onFailure(t, Util.Error_Review);

            }
        });
    }

    @Override
    public void getCastMoVies(final onFinishedListener onFinishedListener, int castID) {

        networkAPI apiService =
                apiclient.getInstance().create(networkAPI.class);

        Call<castMovies> call = apiService.getCastMovies(castID,  Util.ApiKey);
        call.enqueue(new Callback<castMovies>() {
            @Override
            public void onResponse(Call<castMovies> call, Response<castMovies> response) {
                if (response==null)
                {
                    Log.d("errorrr","response==null");
                    onFinishedListener.onFailure(new Exception(), Util.Error_cast);

                }
                else
                {
                    try {
                        List<Movie> movies = response.body().getCast();
                        onFinishedListener.onFinished_getCastMovies(movies);

                    }
                    catch (Exception ex){
                        onFinishedListener.onFailure( ex, Util.Error_cast);
                    }
                }

            }

            @Override
            public void onFailure(Call<castMovies> call, Throwable t) {
                onFinishedListener.onFailure( t, Util.Error_cast);
            }
        });
    }

    @Override
    public void insertFavouirteMovies(FavouriteMovie Movies) {
         localDatabase.insert_favouite(Movies);
    }

    int id;
    @Override
    public void getFavouirteMovie(onFinishedListener onFinishedListener ,int MovieID)
    {
         Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception
            {
            id= localDatabase.GetfavouriteMovie(MovieID)  ;
                 }
                }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onComplete() {
                         onFinishedListener.onFinishedgetFavouirteMovie(id);

                    }

                    @Override
                    public void onError(Throwable e) {
                        onFinishedListener.onFailure( e, Util.Error_cast);
                    }
                });

    }

    @Override
    public void DeleteFavouirteMovie(onFinishedListener onFinishedListener ,int MovieID) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                localDatabase.Delete_favouite(MovieID);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                     }

                    @Override
                    public void onComplete() {
                         onFinishedListener.onFinishedDeleteFavourite();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });


    }

    List<FavouriteMovie>data =new ArrayList<>();


    public List<FavouriteMovie> getFavouirteMovies( ) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                data.clear();
                   data =localDatabase.GetAllFavouriteMovies();
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
        return data;
    }

}
