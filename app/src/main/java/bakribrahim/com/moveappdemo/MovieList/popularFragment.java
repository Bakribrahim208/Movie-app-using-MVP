package bakribrahim.com.moveappdemo.MovieList;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import bakribrahim.com.moveappdemo.MovieDetails.DetailsActivity;
import bakribrahim.com.moveappdemo.Models.Movie;
import bakribrahim.com.moveappdemo.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class popularFragment extends Fragment implements MovieListContract.view ,setMovieItemListner{
     RecyclerView mrecylview ;
    bakribrahim.com.moveappdemo.MovieList.adapter adapter ;
    List<Movie> movieList =new ArrayList<>();
    View view;
    MoveListPresenter presenter;

    int itemVisible=0;
    int page =1;


    public popularFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view =inflater.inflate(R.layout.fragment_popular, container, false);
        mrecylview = (RecyclerView)view.findViewById(R.id.recycleView);



        RecyclerView.LayoutManager gridLayout = new GridLayoutManager(getActivity(), 2);
        mrecylview.setHasFixedSize(true);
        mrecylview.setLayoutManager(gridLayout);
        adapter =new adapter(popularFragment.this);
        mrecylview.setAdapter(adapter);

        presenter =new MoveListPresenter(this);
        presenter.requestDataFromServer();


        mrecylview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = ((LinearLayoutManager) mrecylview.getLayoutManager());
                int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();
                if (lastVisiblePosition + 1 ==itemVisible+20) {
                    Toast.makeText(getContext(), "last item in recycle ", Toast.LENGTH_SHORT).show();
                    presenter.getMoreData(++page);
                    itemVisible += 20;
                }
            }

        });





        return view ;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setDataToRecyclerView(List<Movie> movieArrayList) {
        movieList.addAll(movieArrayList);
         adapter.setMovieList(movieList);
        Toast.makeText(getActivity(), "size is >>>"+movieList.size(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(getActivity(), "onResponseFailure", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void MovieItemclick(int postion) {
        Toast.makeText(getActivity(), movieList.get(postion).getTitle(), Toast.LENGTH_SHORT).show();
        Intent intent =new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("move",movieList.get(postion));
        startActivity(intent);
    }
}
