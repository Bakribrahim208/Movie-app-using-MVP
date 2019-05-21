package bakribrahim.com.moveappdemo.MovieDetails;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import bakribrahim.com.moveappdemo.Models.Cast;
import bakribrahim.com.moveappdemo.Models.Movie;
import bakribrahim.com.moveappdemo.Models.Review;
import bakribrahim.com.moveappdemo.Models.Video;
import bakribrahim.com.moveappdemo.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class MovieDetailsFragment extends Fragment implements MovieDetailsContract.view {

    Movie movie;
     View view;
    ImageView movieImagePoster;
    TextView movieOriginalTitle;
    TextView movieUserRating;
    TextView movieReleaseDate;
    TextView movieOverview;
    CardView cardMovieDetail;
    CardView cardMovieOverview;
    CardView cardMovieVideos;
    RecyclerView movieCast;
    CardView cardMovieReviews;
    RecyclerView movieReviews;

    RecyclerView movieBakr;


    MovieDetailPresenter presenter;
    MovieDetailPresenter presenter_video;

    CastAdapter castAdapter ;
    VideoAdapter videoAdapter;
    Review_Adapter review_adapter ;
    @SuppressLint("ValidFragment")
    public MovieDetailsFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_movie_details, container, false);
        movie= (Movie) getArguments().getSerializable("Movie");

        intiUI ();
        PutDataToUI();

        presenter =new MovieDetailPresenter(MovieDetailsFragment.this);
        presenter.requestDataFromServer_cast(movie.getId());
        presenter.requestDataFromServer_Video(movie.getId());
        presenter.requestDataFromServer_Reviews(movie.getId());

        return view;
    }


    void intiUI ()
    {
        movieImagePoster =view.findViewById(R.id.image_movie_detail_poster);
        movieOriginalTitle =view.findViewById(R.id.text_movie_original_title);
        movieUserRating =view.findViewById(R.id.text_movie_user_rating);
        movieReleaseDate =view.findViewById(R.id.text_movie_release_date);
        movieOverview =view.findViewById(R.id.text_movie_overview);
        cardMovieDetail =view.findViewById(R.id.card_movie_detail);
        cardMovieOverview =view.findViewById(R.id.card_movie_overview);
        cardMovieVideos =view.findViewById(R.id.card_movie_Cast);

        movieCast =view.findViewById(R.id.movie_cast);
        castAdapter =new CastAdapter(getContext());


        StaggeredGridLayoutManager staggeredGridVertical=new
                StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL);
         movieCast.setHasFixedSize(true);
        movieCast.setLayoutManager(staggeredGridVertical);
        movieCast.setAdapter(castAdapter);


        cardMovieReviews =view.findViewById(R.id.card_movie_reviews);
        movieReviews =view.findViewById(R.id.movie_reviews);
        videoAdapter=new VideoAdapter(getContext());
        movieReviews.setAdapter(videoAdapter);
        movieReviews.setHasFixedSize(true);
        StaggeredGridLayoutManager videolayout=new
                StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL);
        movieReviews.setLayoutManager(videolayout);



        movieBakr=view.findViewById(R.id.movie_);
        RecyclerView.LayoutManager liner =new LinearLayoutManager(getContext());
        movieBakr.setLayoutManager(liner);
        review_adapter=new Review_Adapter(getContext());
        movieBakr.setAdapter(review_adapter);

    }

    void PutDataToUI(){


        Glide.with(getActivity())
                .load("https://image.tmdb.org/t/p/w200/"+ movie.getThumbPath())
                .apply(new RequestOptions().placeholder(R.drawable.placeholder).error(R.drawable.placeholder))
                .into(movieImagePoster);

       // Log.e("bakroopds;",movie.getThumbPath());
        movieOriginalTitle.setText(movie.getTitle());
        movieUserRating.setText(String.valueOf(movie.getRating()));
        movieReleaseDate.setText(movie.getReleaseDate());
        movieOverview.setText(movie.getOverview());


    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setDataToRecyclerView_Cast(List<Cast> movieArrayList) {
         castAdapter.setCastList(movieArrayList);

    }

    @Override
    public void setDataToRecyclerView_Video(List<Video> movieArrayList) {
  videoAdapter.setVideolist(movieArrayList);

    }

    @Override
    public void setDataToRecyclerView_Reviews(List<Review> movieArrayList) {
        Toast.makeText(getContext(), "Size reviews ???? "+ movieArrayList.size(), Toast.LENGTH_SHORT).show();
       review_adapter.setReview(movieArrayList);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {

    }
}
