package bakribrahim.com.moveappdemo.MovieDetails;

import java.util.List;

import bakribrahim.com.moveappdemo.Models.Cast;
import bakribrahim.com.moveappdemo.Models.Review;
import bakribrahim.com.moveappdemo.Models.Video;

public interface MovieDetailsContract {
    interface model_details{
        interface onFinishedListener {
            void onFinished_Cast(List<Cast> CastArrayList);
            void onFinished_Video(List<Video> CastArrayList);
            void onFinished_Reviews(List<Review> CastArrayList);

            void onFailure(Throwable t);
        }

        void getCastList(MovieDetailsContract.model_details.onFinishedListener onFinishedListener, int MovieID);
        void getVideo(MovieDetailsContract.model_details.onFinishedListener onFinishedListener, int MovieID);
        void getReviews(MovieDetailsContract.model_details.onFinishedListener onFinishedListener, int MovieID);


    }
    interface view {
        void showProgress();
        void hideProgress();
        void setDataToRecyclerView_Cast(List<Cast> movieArrayList);
        void setDataToRecyclerView_Video(List<Video> movieArrayList);
        void setDataToRecyclerView_Reviews(List<Review> movieArrayList);
        void onResponseFailure(Throwable throwable);
    }


    interface presenter{
        void onDestroy();

        void getMoreData(int pageNo);

        void requestDataFromServer_cast(int MovieID);
        void requestDataFromServer_Video(int MovieID);
        void requestDataFromServer_Reviews(int MovieID);


    }
}
