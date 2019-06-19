package bakribrahim.com.moveappdemo.MovieList.MVP.presenter;

import android.app.Application;

import java.util.List;

import bakribrahim.com.moveappdemo.Models.Movie.Movie;
import bakribrahim.com.moveappdemo.MovieList.MVP.MovieListContract;
import bakribrahim.com.moveappdemo.MovieList.MVP.model.MoveListModel;
import bakribrahim.com.moveappdemo.Utilites.Util;

public class MoveListPresenter implements MovieListContract.presenter , MovieListContract.Model.OnFinishedListener{
    MovieListContract.view view ;
    MovieListContract.Model model ;

    public MoveListPresenter(MovieListContract.view view, Application application) {
        this.view = view;
        model =new MoveListModel(application);
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    int offset=0;

    @Override
    public void getMoreData(int pageNo,String moviestype) {
          offset = (pageNo==1)  ? 1: (pageNo-1)*20 ;
          if (moviestype.equals(Util.FAVOUIRTE))
          {
              model.getFavouriteMovieList_fromlocal(this, 20,offset);

          }
          else
          {
              if (view.getCodeSnippets().hasNetworkConnection()  ) {
                  model.getMovieList(this, pageNo,moviestype);
              }
              else
              {
                  model.getMovieList_fromlocal(this, 20,offset);
              }
          }


        view.showProgress();

    }

    @Override
    public void requestDataFromServer(String moviestype) {

        if (view != null ) {
            view.showProgress();

        }

        model.getMovieList(this, 1, moviestype);
    }

    @Override
    public void getDataFromLocalDatabase(int limit, int offset) {
        model.getMovieList_fromlocal(this, limit, offset);
    }

    @Override
    public void onFinished(List<Movie> movieArrayList) {
        if (view != null) {
            view.hideProgress();
            view.setDataToRecyclerView(movieArrayList);

        }
    }

    @Override
    public void onFailure(Throwable t) {
        if (view != null) {
            view.hideProgress();
            view.onResponseFailure(t);
        }
    }
}
