package bakribrahim.com.moveappdemo.MovieDetails.UI;


import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import bakribrahim.com.moveappdemo.CallBack.FragmentCallBack;
import bakribrahim.com.moveappdemo.CallBack.ReviewCallback;
import bakribrahim.com.moveappdemo.CallBack.VideoCallBack;
import bakribrahim.com.moveappdemo.Models.Cast.Cast;
import bakribrahim.com.moveappdemo.Models.Movie.FavouriteMovie;
import bakribrahim.com.moveappdemo.Models.Movie.Movie;
import bakribrahim.com.moveappdemo.Models.Reviews.Review;
import bakribrahim.com.moveappdemo.Models.Videos.Video;
import bakribrahim.com.moveappdemo.Models.genre.genreData;
import bakribrahim.com.moveappdemo.MovieDetails.MVP.persenter.MovieDetailPresenter;
import bakribrahim.com.moveappdemo.MovieDetails.MVP.MovieDetailsContract;
import bakribrahim.com.moveappdemo.MovieDetails.adapters.Review_Adapter;
import bakribrahim.com.moveappdemo.MovieDetails.adapters.CastAdapter;
import bakribrahim.com.moveappdemo.MovieDetails.adapters.VideoAdapter;
import bakribrahim.com.moveappdemo.R;
import bakribrahim.com.moveappdemo.Utilites.Util;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class MovieDetailsFragment extends Fragment implements FragmentCallBack,ReviewCallback, VideoCallBack, MovieDetailsContract.view, View.OnClickListener, CastAdapter.castcallback {

    ImageButton fab;

    LottieAnimationView lottieview;

    ImageView movieImagePoster;

    TextView movieOriginalTitle;
    TextView movieUserRating;
    TextView movieReleaseDate;
    TextView movieOverview;
    TextView text_genre;
    TextView trails_header;
    TextView Reviewsheader ;
    TextView Castheader;

    LinearLayout colored;

    RecyclerView movieCast;
    RecyclerView movieReviews;
    RecyclerView mreviewRecyclerview  ;


    MovieDetailPresenter presenter;

    CastAdapter castAdapter ;
    VideoAdapter videoAdapter;
    Review_Adapter review_adapter ;

    View v_bg_grad;
    View view;

    int textcolor  ,badytexcolor,
                    backgoundcolor;


    Movie movie;

    @SuppressLint("ValidFragment")
    public MovieDetailsFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.newactivity_detials, container, false);
        movie= (Movie) getArguments().getSerializable("Movie");

        intiUI ();
        PutDataToUI();

        presenter =new MovieDetailPresenter(MovieDetailsFragment.this,getActivity().getApplication());
        presenter.checkifMovieLiked(movie.getId());

        // after this method complete automatically get trails and after trails complete get reviews
        getCast ();



        return view;
    }

     private void getCast (){
         Completable.fromAction(new Action() {
             @Override
             public void run() throws Exception {

                 presenter.requestDataFromServer_cast(movie.getId());
             }
         }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                 .subscribe(new CompletableObserver() {
                     @Override
                     public void onSubscribe(Disposable d)
                     {
                     }

                     @Override
                     public void onComplete() {
                         getTrails ();
                     }

                     @Override
                     public void onError(Throwable e) {

                     }
                 });
     }
    private void getTrails (){
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {

                presenter.requestDataFromServer_Video(movie.getId());
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d)
                    {
                    }

                    @Override
                    public void onComplete() {
                        getRevews ();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
    private void getRevews (){
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {

                presenter.requestDataFromServer_Reviews(movie.getId());
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d)
                    {
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
    void intiUI ()
    {
        movieImagePoster =view.findViewById(R.id.image_movie_detail_poster);
        movieOriginalTitle =view.findViewById(R.id.text_movie_original_title);
        movieUserRating =view.findViewById(R.id.text_movie_user_rating);
        movieReleaseDate =view.findViewById(R.id.text_movie_release_date);
        movieOverview =view.findViewById(R.id.text_movie_overview);
        trails_header=view.findViewById(R.id.trails_header);
        Reviewsheader=view.findViewById(R.id.Reviewsheader);
        Castheader=view.findViewById(R.id.Castheader);
        text_genre=view.findViewById(R.id.text_genre);
        v_bg_grad=view.findViewById(R.id.v_bg_grad);
         colored=view.findViewById(R.id.colored);
        fab=view.findViewById(R.id.fab);
        lottieview=view.findViewById(R.id.animation_view);
        lottieview.setVisibility(View.GONE);
        movieCast =view.findViewById(R.id.movie_cast);


        fab.setOnClickListener(this);
        lottieview.setOnClickListener(this);


        // set up cast recyclerview
        castAdapter =new CastAdapter(getContext(),this);
        StaggeredGridLayoutManager staggeredGridVertical=new
                StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL);
         movieCast.setHasFixedSize(true);
        movieCast.setLayoutManager(staggeredGridVertical);
        movieCast.setAdapter(castAdapter);

        // set up trails recyclerview
         movieReviews =view.findViewById(R.id.movie_reviews);
        videoAdapter=new VideoAdapter(getContext(),this);
        movieReviews.setAdapter(videoAdapter);
        movieReviews.setHasFixedSize(true);
        StaggeredGridLayoutManager videolayout=new
                StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL);
        movieReviews.setLayoutManager(videolayout);


        // set up cast recyclerview
        mreviewRecyclerview =view.findViewById(R.id.movie_);
        RecyclerView.LayoutManager liner =new LinearLayoutManager(getContext());
        mreviewRecyclerview.setLayoutManager(liner);
        review_adapter=new Review_Adapter(getContext(),this);
        mreviewRecyclerview.setAdapter(review_adapter);


    }

    void PutDataToUI(){
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                getActivity().runOnUiThread(new Runnable(){
                    @Override
                    public void run(){
                        loadposter ();
                    }
                });
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d)
                    {
                     }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
         movieOriginalTitle.setText(movie.getTitle());
        movieUserRating.setText(String.valueOf(movie.getRating()));
        movieReleaseDate.setText(movie.getReleaseDate());
        movieOverview.setText(movie.getOverview());
        text_genre.setText(ConvertGenToString (movie.getgenre_ids()));
    }


    public void loadposter ()
    {
        Glide.with(this)
                .asBitmap()
                .load("https://image.tmdb.org/t/p/w200/"+ movie.getThumbPath())
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target,
                                                   DataSource dataSource, boolean isFirstResource) {
                        movieImagePoster.setImageBitmap(resource);
                        Palette.from(resource).generate(palette -> {
                            assert palette != null;
                            if (palette.getDarkMutedSwatch() != null) {
                                int rgb = ColorUtils.blendARGB(
                                        palette.getDarkMutedSwatch().getRgb(),
                                        Color.BLACK,
                                        0.3f
                                );
                                backgoundcolor=rgb;
                                ViewCompat.setBackgroundTintList(v_bg_grad, ColorStateList.valueOf(rgb));
                                 colored.setBackgroundColor(getTransparentColor(palette.getDarkMutedSwatch().getRgb()));
                                backgoundcolor=getTransparentColor(palette.getDarkMutedSwatch().getRgb());
                                textcolor=palette.getDarkMutedSwatch().getTitleTextColor();
                                badytexcolor=palette.getDarkMutedSwatch().getBodyTextColor();
                                setupCOLOR(textcolor,badytexcolor);
                                getActivity().getWindow().getDecorView().setBackgroundColor(rgb);
                            } else if (palette.getMutedSwatch() != null) {
                                 int rgb = ColorUtils.blendARGB(
                                        palette.getMutedSwatch().getRgb(),
                                        Color.BLACK,
                                        0.3f
                                );
                                backgoundcolor=rgb;
                                ViewCompat.setBackgroundTintList(v_bg_grad, ColorStateList.valueOf(rgb));
                                colored.setBackgroundColor(getTransparentColor(palette.getMutedSwatch().getRgb()));
                                colored.setBackgroundColor(rgb);
                                textcolor=palette.getMutedSwatch().getTitleTextColor();
                                badytexcolor=palette.getMutedSwatch().getBodyTextColor();
                                setupCOLOR(textcolor,badytexcolor);

                                getActivity().getWindow().getDecorView().setBackgroundColor(rgb);
                            } else if (palette.getDarkVibrantSwatch() != null) {
                                int rgb = ColorUtils.blendARGB(
                                        palette.getDarkVibrantSwatch().getRgb(),
                                        Color.BLACK,
                                        0.3f
                                );
                                backgoundcolor=rgb;

                                ViewCompat.setBackgroundTintList(v_bg_grad, ColorStateList.valueOf(rgb));
                                colored.setBackgroundColor(getTransparentColor(palette.getDarkVibrantSwatch().getRgb()));
                                backgoundcolor=getTransparentColor(palette.getDarkVibrantSwatch().getRgb());
                                textcolor=palette.getDarkVibrantSwatch().getTitleTextColor();
                                badytexcolor=palette.getDarkVibrantSwatch().getBodyTextColor();
                                setupCOLOR(textcolor,badytexcolor);
                                getActivity().getWindow().getDecorView().setBackgroundColor(rgb);
                            }


                        });



                        return false;
                    }
                })
                .into(movieImagePoster);

    }

     public String ConvertGenToString (ArrayList<Integer> genres)
    {
         genreData genreData =new genreData();
        String json =getResources().getString(R.string.genre);
        return genreData.ConvertGenToString(json, genres);
    }

   private void setupCOLOR(int titile,int body){
      movieOriginalTitle.setTextColor(titile);
      trails_header.setTextColor(titile);
      Reviewsheader.setTextColor(titile);
      Castheader.setTextColor(titile);
      movieOverview.setTextColor(body);
      text_genre.setTextColor(body);
      movieReleaseDate.setTextColor(body);
  }

    private int getTransparentColor(int color){
        int alpha = Color.alpha(color);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
         alpha *= 0.1;

        return Color.argb(alpha, red, green, blue);
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

        review_adapter.setReview(movieArrayList);

    }

    @Override
    public void getCastMOVies(List<Movie> movieArrayList) {

    }

    @Override
    public void UpdateIFMOVieLikedBefor() {
        lottieview.setVisibility(View.VISIBLE);
        lottieview.playAnimation();
         fab.setVisibility(View.GONE);

    }


    @Override
    public void onResponseFailure(Throwable throwable,String type) {

         if (type.equals(Util.Error_cast))
        {
            mreviewRecyclerview.setVisibility(View.GONE);
            ((TextView)(view.findViewById(R.id.txtNoCast))).setVisibility(View.VISIBLE);
        }
        else if (type.equals(Util.Error_Trails))
         {
             movieReviews.setVisibility(View.GONE);
             ((TextView)(view.findViewById(R.id.txtNotrails_))).setVisibility(View.VISIBLE);
         }
         else if (type.equals(Util.Error_Review))
         {
             mreviewRecyclerview.setVisibility(View.GONE);
             ((TextView)(view.findViewById(R.id.txtNoReviewsٌٌ))) .setVisibility(View.VISIBLE);
         }
     }

    @Override
    public void onDeleteFavouirtResponse() {
        Toast.makeText(getContext(), "onDeleteFavouirtResponse", Toast.LENGTH_SHORT).show();
        lottieview.playAnimation();
        lottieview.setVisibility(View.GONE);
        fab.setVisibility(View.VISIBLE);
    }

    @Override
    public void showCastDetials(Cast cast) {
         presenter.requestDataFromServer_CastMOvies(cast.getCastId());
         CastBottomsheet bottomSheetFragment = new CastBottomsheet();
        Bundle bundle = new Bundle();
        bundle.putSerializable("cast",cast);
        bundle.putInt("backgroundcolor",  backgoundcolor);
        bottomSheetFragment.setArguments(bundle);
        bottomSheetFragment.show(getActivity().getSupportFragmentManager(),"d");
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==fab.getId())
        {
            FavouriteMovie favouriteMovie= new FavouriteMovie();
            favouriteMovie.setId(movie.getId());
            favouriteMovie.setTitle(movie.getTitle());
            favouriteMovie.setCredits(movie.getCredits());
            favouriteMovie.setBackdropPath(movie.getBackdropPath());
            favouriteMovie.setHomepage(movie.getHomepage());
            favouriteMovie.setThumbPath(movie.getThumbPath());
            favouriteMovie.setRating(movie.getRating());
            favouriteMovie.setRunTime(movie.getRunTime());
            favouriteMovie.setOverview(movie.getOverview());
            favouriteMovie.setReleaseDate(movie.getReleaseDate());
             presenter.insertFavouirteMovies(favouriteMovie);
            Toast.makeText(getContext(), "added to favouite", Toast.LENGTH_SHORT).show();
            fab.setVisibility(View.GONE);
            lottieview.setVisibility(View.VISIBLE);
            lottieview.playAnimation();
        }
        else{
            presenter.DeleteFavouirteMovie(movie.getId());
            Toast.makeText(getContext(), "Removed from favouite", Toast.LENGTH_SHORT).show();

        }
    }



    @Override
    public void VideoSHow(String VideoID) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + VideoID));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + VideoID));
        try {
            startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            String title = "Select App";
             Intent chooser = Intent.createChooser(webIntent, title);
            if (webIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivity(webIntent);
            }
            else
                {
                    Toast.makeText(getContext(), "No App TO OPEN THIS Video", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    public void openReview(Review review) {
        ViewDialog dialog = new ViewDialog();
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        Bundle bundle =new Bundle();
        bundle.putSerializable("review", review);
        bundle.putInt(Util.Textcolor, textcolor);
        bundle.putInt(Util.Backgroundcolor, backgoundcolor);
        bundle.putInt(Util.BodyTextcolor, badytexcolor);
        dialog.setArguments(bundle);
         dialog.show(ft,"commentshow");
    }

    @Override
    public void Show_detialsFramgnet(Movie movie) {

    }
}
