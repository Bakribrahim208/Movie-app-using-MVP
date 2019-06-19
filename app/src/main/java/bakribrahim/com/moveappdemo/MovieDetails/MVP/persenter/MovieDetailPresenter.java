package bakribrahim.com.moveappdemo.MovieDetails.MVP.persenter;

import android.app.Application;
import android.util.Log;

import java.util.List;

import bakribrahim.com.moveappdemo.Models.Cast.Cast;
import bakribrahim.com.moveappdemo.Models.Movie.FavouriteMovie;
import bakribrahim.com.moveappdemo.Models.Movie.Movie;
import bakribrahim.com.moveappdemo.Models.Reviews.Review;
import bakribrahim.com.moveappdemo.Models.Videos.Video;
import bakribrahim.com.moveappdemo.MovieDetails.MVP.MovieDetailsContract;
import bakribrahim.com.moveappdemo.MovieDetails.MVP.models.MovieDetailsModel;

public class MovieDetailPresenter implements MovieDetailsContract.presenter
        , MovieDetailsContract.model_details.onFinishedListener {

   MovieDetailsContract.view view ;
    MovieDetailsModel model ;

    public MovieDetailPresenter(MovieDetailsContract.view view, Application application) {
        this.view = view;
        model =new MovieDetailsModel(application);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void getMoreData(int pageNo) {

    }

    @Override
    public void requestDataFromServer_cast(int MovieID) {
        if (view != null) {
            view.showProgress();
        }
        model.getCastList(MovieDetailPresenter.this, MovieID);
    }

    @Override
    public void requestDataFromServer_Video(int MovieID) {
        if (view != null) {
            view.showProgress();
        }
        model.getVideo(MovieDetailPresenter.this, MovieID);
    }

    @Override
    public void requestDataFromServer_Reviews(int MovieID) {
        if (view != null) {
            view.showProgress();
        }
        model.getReviews(MovieDetailPresenter.this, MovieID);
    }

    @Override
    public void requestDataFromServer_CastMOvies(int castid) {
        if (view != null) {
            view.showProgress();
        }
        model.getCastMoVies(MovieDetailPresenter.this, castid);
    }

    @Override
    public void insertFavouirteMovies(FavouriteMovie Movies) {
        model.insertFavouirteMovies(Movies);
    }

    @Override
    public void checkifMovieLiked(int movieID) {
        model.getFavouirteMovie(this, movieID);
    }

    @Override
    public void DeleteFavouirteMovie(int MovieID) {
        model.DeleteFavouirteMovie(this,MovieID);
    }

    @Override
    public void onFinished_Cast(List<Cast> CastArrayList) {

        view.setDataToRecyclerView_Cast(CastArrayList);
    }

    @Override
    public void onFinished_getCastMovies(List<Movie> CastArrayList) {
        view.getCastMOVies(CastArrayList);
    }

    @Override
    public void onFinished_Video(List<Video> CastArrayList) {
        view.setDataToRecyclerView_Video(CastArrayList);
    }

    @Override
    public void onFinished_Reviews(List<Review> CastArrayList) {
        view.setDataToRecyclerView_Reviews(CastArrayList);
    }

    @Override
    public void onFailure(Throwable t,String type) {
        view.onResponseFailure(t, type);

    }

    @Override
    public void onFinishedgetFavouirteMovie(int Movieid) {
         if (Movieid==0)
            Log.e("prsenter", "0");
       else{
            Log.e("prsenter", "ID"+Movieid);
            view.UpdateIFMOVieLikedBefor();
        }

    }

    @Override
    public void onFinishedDeleteFavourite() {
        view.onDeleteFavouirtResponse();
    }
}
