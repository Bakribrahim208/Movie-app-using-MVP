package bakribrahim.com.moveappdemo.MovieDetails;

import android.util.Log;
import android.widget.Toast;

import java.util.List;

import bakribrahim.com.moveappdemo.Models.Cast;
import bakribrahim.com.moveappdemo.Models.Review;
import bakribrahim.com.moveappdemo.Models.Video;

public class MovieDetailPresenter implements MovieDetailsContract.presenter , MovieDetailsContract.model_details.onFinishedListener {

   MovieDetailsContract.view view ;
    MovieDetailsModel model ;

    public MovieDetailPresenter(MovieDetailsContract.view view) {
        this.view = view;
        model =new MovieDetailsModel();
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
    public void onFinished_Cast(List<Cast> CastArrayList) {

        view.setDataToRecyclerView_Cast(CastArrayList);
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
    public void onFailure(Throwable t) {

    }
}
