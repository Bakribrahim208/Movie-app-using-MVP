package bakribrahim.com.moveappdemo.MovieList.UI;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import bakribrahim.com.moveappdemo.CallBack.FragmentCallBack;
import bakribrahim.com.moveappdemo.Utilites.CodeSnippets;
import bakribrahim.com.moveappdemo.Models.Movie.Movie;
import bakribrahim.com.moveappdemo.MovieList.MVP.presenter.MoveListPresenter;
import bakribrahim.com.moveappdemo.MovieList.MVP.MovieListContract;
import bakribrahim.com.moveappdemo.MovieList.adapter.adapter;
import bakribrahim.com.moveappdemo.CallBack.setMovieItemListner;
import bakribrahim.com.moveappdemo.R;
import bakribrahim.com.moveappdemo.Utilites.Util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class popularFragment extends Fragment implements MovieListContract.view, setMovieItemListner, SwipeRefreshLayout.OnRefreshListener  {
     RecyclerView mrecylview ;
     LottieAnimationView animation_view;
    SwipeRefreshLayout swipeRefreshLayout;
    View rootview;
    bakribrahim.com.moveappdemo.MovieList.adapter.adapter adapter ;
    List<Movie> movieList =new ArrayList<>();
    MoveListPresenter presenter;
    int itemVisible=0;
    int page =1;
    CodeSnippets CodeSnippet;
    String MoviesType ;
    FragmentCallBack fragmentCallBack;
     public popularFragment() {
        // Required empty public constructor
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentCallBack= (FragmentCallBack) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_popular, container, false);
        setHasOptionsMenu(true);

        //get Fragment type
          Bundle bundle =getArguments();
          if (bundle!= null )
              MoviesType=bundle.getString(Util.Fragment);
          else
              MoviesType=Util.POPULAR_MOVIES;


          //intilize views
        mrecylview = (RecyclerView) rootview.findViewById(R.id.recycleView);
        swipeRefreshLayout= rootview.findViewById(R.id.SwipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        animation_view= rootview.findViewById(R.id.animation_view);

        //intilize class
        RecyclerView.LayoutManager gridLayout = new GridLayoutManager(getActivity(), 2);
        mrecylview.setHasFixedSize(true);
        adapter =new adapter(popularFragment.this);
        CodeSnippet=new CodeSnippets(getContext());
        presenter =new MoveListPresenter(this,getActivity().getApplication());


        mrecylview.setLayoutManager(gridLayout);
        mrecylview.setAdapter(adapter);
        presenter.getMoreData(page,MoviesType);


        mrecylview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = ((LinearLayoutManager) mrecylview.getLayoutManager());
                int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();
                if (lastVisiblePosition + 1 ==itemVisible+20) {
                      presenter.getMoreData(++page,MoviesType);
                    itemVisible += 20;
                }
            }

        });
        return rootview;
    }

    @Override
    public void showProgress(){
        //swipeRefreshLayout.setRefreshing(true);
        animation_view.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        swipeRefreshLayout.setRefreshing(false);
        animation_view.setVisibility(View.GONE);
    }

    @Override
    public void setDataToRecyclerView(List<Movie> movieArrayList) {

         movieList.addAll(movieArrayList);
         adapter.setMovieList(movieList);

    }

    @Override
    public void onResponseFailure(Throwable throwable) {
         noConnectionFragment newFragment = noConnectionFragment.newInstance(
                "noConnectionFragment");
        newFragment.show(getFragmentManager(), "dialog");
    }

    @Override
    public CodeSnippets getCodeSnippets() {
        if (CodeSnippet==null)
            CodeSnippet=new CodeSnippets(getContext());

        return CodeSnippet;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void MovieItemclick(int postion,View viewclick) {

        fragmentCallBack.Show_detialsFramgnet(movieList.get(postion));
    }

      @Override
    public void onRefresh() {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
         page =1;
        itemVisible=0;
        movieList.clear();
        adapter.ResetMovieList();
        presenter.getMoreData(page,MoviesType);

    }


}
