package bakribrahim.com.moveappdemo.MovieList;

import java.util.List;

import bakribrahim.com.moveappdemo.Models.Movie;

public class MoveListPresenter implements MovieListContract.presenter , MovieListContract.Model.OnFinishedListener{
    MovieListContract.view view ;
    MovieListContract.Model model ;

    public MoveListPresenter(MovieListContract.view view) {
        this.view = view;
        model =new MoveListModel();
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    @Override
    public void getMoreData(int pageNo) {
        if (view != null) {
            view.showProgress();
        }
        model.getMovieList(this, pageNo);
    }

    @Override
    public void requestDataFromServer() {
        if (view != null) {
            view.showProgress();
        }
        model.getMovieList(this, 1);
    }

    @Override
    public void onFinished(List<Movie> movieArrayList) {
        view.setDataToRecyclerView(movieArrayList);
        if (view != null) {
            view.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        view.onResponseFailure(t);
        if (view != null) {
            view.hideProgress();
        }
    }
}
