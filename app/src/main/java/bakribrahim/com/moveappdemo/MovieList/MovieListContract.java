package bakribrahim.com.moveappdemo.MovieList;

import java.util.List;

import bakribrahim.com.moveappdemo.Models.Cast;
import bakribrahim.com.moveappdemo.Models.Movie;

public interface MovieListContract {

    interface Model{
        interface OnFinishedListener {
            void onFinished(List<Movie> movieArrayList);
            void onFailure(Throwable t);
        }

        void getMovieList(OnFinishedListener onFinishedListener, int pageNo);
    }
    interface view {
        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(List<Movie> movieArrayList);

        void onResponseFailure(Throwable throwable);
    }


    interface presenter{
        void onDestroy();

        void getMoreData(int pageNo);

        void requestDataFromServer();
    }
}
