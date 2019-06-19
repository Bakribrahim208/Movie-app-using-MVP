package bakribrahim.com.moveappdemo.MovieList.MVP;

import java.util.List;

import bakribrahim.com.moveappdemo.Utilites.CodeSnippets;
import bakribrahim.com.moveappdemo.Models.Movie.Movie;

public interface MovieListContract {

    interface Model{
        interface OnFinishedListener {
            void onFinished(List<Movie> movieArrayList);
            void onFailure(Throwable t);
        }

        void getMovieList(OnFinishedListener onFinishedListener, int pageNo,String moviestype);
        void getMovieList_fromlocal(OnFinishedListener onFinishedListener, int limit,int offset);
        void getFavouriteMovieList_fromlocal(OnFinishedListener onFinishedListener, int limit,int offset);

    }
    interface view {
        void showProgress();
        void hideProgress();
        void setDataToRecyclerView(List<Movie> movieArrayList);
        void onResponseFailure(Throwable throwable);
        CodeSnippets getCodeSnippets();
    }

    interface presenter{
        void onDestroy();
        void getMoreData(int pageNo,String moviestype);
        void requestDataFromServer(String moviestype);
        void getDataFromLocalDatabase(int limit ,int offset);

    }
}
