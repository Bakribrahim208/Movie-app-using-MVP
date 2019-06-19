package bakribrahim.com.moveappdemo.MovieList.MVP.model;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import bakribrahim.com.moveappdemo.Models.Movie.FavouriteMovie;
import bakribrahim.com.moveappdemo.Models.Movie.Movie;
import bakribrahim.com.moveappdemo.Models.Movie.MovieListResponse;
import bakribrahim.com.moveappdemo.MovieList.MVP.MovieListContract;
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

public class MoveListModel implements MovieListContract.Model {
    localDatabase localDatabase ;
    public  MoveListModel(Application application){
        localDatabase=new localDatabase(application );
    }
    @Override
    public void getMovieList(final MovieListContract.Model.OnFinishedListener onFinishedListener, int pageNo ,String moviestype) {
        networkAPI apiService = apiclient.getInstance().create(networkAPI.class);
        Call<MovieListResponse> call = apiService.getPopularMovies
                (moviestype, Util.ApiKey, pageNo );
        call.enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
               if (response==null)
               {

               }
               else
               {
                   try {
                       List<Movie> movies = response.body().getResults();

                       onFinishedListener.onFinished(movies);
                       //update database
                       localDatabase.insert(movies);
                   }
                   catch (Exception ex){
                        onFinishedListener.onFailure(ex);
                   }
               }

            }
            List<Movie>movies=new ArrayList<>();

            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable t) {
                 onFinishedListener.onFailure(t);
            }
        });


    }

    List<Movie>data =new ArrayList<>();
    @Override
    public void getMovieList_fromlocal(OnFinishedListener onFinishedListener, int limit, int offset) {

        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                data.clear();
                 data =localDatabase.GetMoviesFromLocal(limit,offset);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onComplete() {
                        onFinishedListener.onFinished(data);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    @Override
    public void getFavouriteMovieList_fromlocal(OnFinishedListener onFinishedListener, int limit, int offset) {

        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                data.clear();
                List<FavouriteMovie>FavouriteMovies =new ArrayList<>();
                FavouriteMovies =localDatabase.GetAllFavouriteMovies();
            for (int i =0 ;i<FavouriteMovies.size();i++)
                {
                    Movie movie=new Movie(FavouriteMovies.get(i).getId(),
                            FavouriteMovies.get(i).getTitle(),
                            FavouriteMovies.get(i).getReleaseDate(),
                            FavouriteMovies.get(i).getRating(),
                            FavouriteMovies.get(i).getThumbPath(),
                            FavouriteMovies.get(i).getOverview(),
                            FavouriteMovies.get(i).getBackdropPath(),
                            FavouriteMovies.get(i).getCredits(),
                            FavouriteMovies.get(i).getRunTime(),
                            FavouriteMovies.get(i).getTagline(),
                            FavouriteMovies.get(i).getHomepage()
                            );
                    data.add(movie);
                }
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onComplete() {
                        onFinishedListener.onFinished(data);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

}
