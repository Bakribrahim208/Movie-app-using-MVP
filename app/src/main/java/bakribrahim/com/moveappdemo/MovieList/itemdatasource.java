package bakribrahim.com.moveappdemo.MovieList;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import bakribrahim.com.moveappdemo.Models.Movie;

public class itemdatasource extends PageKeyedDataSource <Integer, Movie> {
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Movie> callback) {

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {

    }
}
