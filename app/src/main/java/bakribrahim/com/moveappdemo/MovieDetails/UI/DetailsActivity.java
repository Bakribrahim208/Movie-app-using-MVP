package bakribrahim.com.moveappdemo.MovieDetails.UI;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.widget.NestedScrollView;
import androidx.palette.graphics.Palette;
import bakribrahim.com.moveappdemo.Models.Movie.Movie;
import bakribrahim.com.moveappdemo.R;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetailsActivity extends AppCompatActivity {
    Movie movie;
    CoordinatorLayout coordinatorLayout;

    CollapsingToolbarLayout collapsingToolbarLayout;

    Toolbar toolbar;

    ImageView movieBackdropImage;
    FloatingActionButton fab;

    NestedScrollView nestedScrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent =getIntent();
        movie = (Movie) intent.getSerializableExtra("move");
        Bundle bundle =new Bundle();
        bundle.putSerializable("Movie",movie);
          MovieDetailsFragment fragment =new MovieDetailsFragment();
         fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.movies_grid_container,fragment,"").commit();
        initiUI();
       // initToolbar();

     }


    private void initToolbar() {
        ((AppCompatActivity)this).setSupportActionBar(toolbar);

             if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
                 toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

                 toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      finish();
                  }
              });
            }
            collapsingToolbarLayout.setTitle(movie.getTitle());
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.BLACK);

        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this,
                    android.R.color.transparent));
            setTitle("");


        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                runOnUiThread(new Runnable(){
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



    }

    public void loadposter ()
    {
        Glide.with(this)
                .asBitmap()
                .load("https://image.tmdb.org/t/p/w780/" + movie.getBackdropPath())
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target,
                                                   DataSource dataSource, boolean isFirstResource) {
                        movieBackdropImage.setImageBitmap(resource);
                        Palette.from(resource).generate(palette -> {
                            assert palette != null;
                            if (palette.getDarkMutedSwatch() != null) {
                                int rgb = ColorUtils.blendARGB(
                                        palette.getDarkMutedSwatch().getRgb(),
                                        Color.BLACK,
                                        0.3f
                                );

                                 getWindow().getDecorView().setBackgroundColor(rgb);
                            } else if (palette.getMutedSwatch() != null) {
                                int rgb = ColorUtils.blendARGB(
                                        palette.getMutedSwatch().getRgb(),
                                        Color.BLACK,
                                        0.3f
                                );

                                 getWindow().getDecorView().setBackgroundColor(rgb);
                            } else if (palette.getDarkVibrantSwatch() != null) {
                                int rgb = ColorUtils.blendARGB(
                                        palette.getDarkVibrantSwatch().getRgb(),
                                        Color.BLACK,
                                        0.3f
                                );

                                 getWindow().getDecorView().setBackgroundColor(rgb);
                            }

                            if (palette.getLightMutedSwatch() != null) {
                                int rgb = palette.getLightMutedSwatch().getRgb();
                             } else if (palette.getLightVibrantSwatch() != null) {

                                int rgb = palette.getLightVibrantSwatch().getRgb();
                             }
                        });



                        return false;
                    }
                })
                .into(movieBackdropImage);

    }
   void initiUI()
   {
       coordinatorLayout=findViewById(R.id.coordinator_layout);
//       collapsingToolbarLayout=findViewById(R.id.collapsing_toolbar_layout);
//
//       toolbar=findViewById(R.id.toolbar);
//
//       movieBackdropImage=findViewById(R.id.backdrop_image);
//
//       fab=findViewById(R.id.fab);
//       nestedScrollView=findViewById(R.id.nestedScrollView);
//
//       movieBackdropImage.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               view_imageDialoge alert = new view_imageDialoge();
//                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//               Bundle bundle =new Bundle();
//               bundle.putSerializable("imageurl", "https://image.tmdb.org/t/p/w780/" + movie.getBackdropPath());
//               alert.setArguments(bundle);
//               alert.show(ft,"imageshow");
//           }
//       });
   }



}
