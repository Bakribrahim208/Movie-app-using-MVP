package bakribrahim.com.moveappdemo.MovieDetails.MVP;

import java.util.List;

import bakribrahim.com.moveappdemo.Models.Cast.Cast;
import bakribrahim.com.moveappdemo.Models.Movie.FavouriteMovie;
import bakribrahim.com.moveappdemo.Models.Movie.Movie;
import bakribrahim.com.moveappdemo.Models.Reviews.Review;
import bakribrahim.com.moveappdemo.Models.Videos.Video;

public interface MovieDetailsContract {
    interface model_details{
        interface onFinishedListener {
            void onFinished_Cast(List<Cast> CastArrayList);
            void onFinished_getCastMovies(List<Movie> CastArrayList);
            void onFinished_Video(List<Video> CastArrayList);
            void onFinished_Reviews(List<Review> CastArrayList);
            void onFinishedgetFavouirteMovie(int MovieID);
            void onFinishedDeleteFavourite();
            void onFailure(Throwable t,String type);


        }

        void getCastList(MovieDetailsContract.model_details.onFinishedListener onFinishedListener, int MovieID);
        void getVideo(MovieDetailsContract.model_details.onFinishedListener onFinishedListener, int MovieID);
        void getReviews(MovieDetailsContract.model_details.onFinishedListener onFinishedListener, int MovieID);
        void getCastMoVies(MovieDetailsContract.model_details.onFinishedListener onFinishedListener, int castID);
        void insertFavouirteMovies(FavouriteMovie Movies);
        void getFavouirteMovie(onFinishedListener onFinishedListener ,int MovieID);
        void DeleteFavouirteMovie(onFinishedListener onFinishedListener,int MovieID);

    }
    interface view {
        void showProgress();
        void hideProgress();
        void setDataToRecyclerView_Cast(List<Cast> movieArrayList);
        void setDataToRecyclerView_Video(List<Video> movieArrayList);
        void setDataToRecyclerView_Reviews(List<Review> movieArrayList);
        void getCastMOVies(List<Movie> movieArrayList);
        void UpdateIFMOVieLikedBefor();
        void onResponseFailure(Throwable throwable,String type);
        void onDeleteFavouirtResponse();

    }


    interface presenter{
        void onDestroy();
        void getMoreData(int pageNo);
        void requestDataFromServer_cast(int MovieID);
        void requestDataFromServer_Video(int MovieID);
        void requestDataFromServer_Reviews(int MovieID);
        void requestDataFromServer_CastMOvies(int castid);
        void insertFavouirteMovies(FavouriteMovie Movies);
        void checkifMovieLiked(int movieID);
        void DeleteFavouirteMovie(int MovieID);


    }
}
