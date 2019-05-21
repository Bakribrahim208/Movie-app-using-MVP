package bakribrahim.com.moveappdemo.MovieDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import bakribrahim.com.moveappdemo.Models.Movie;
import bakribrahim.com.moveappdemo.R;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetailsActivity extends AppCompatActivity {
    Movie movie;
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
        initToolbar();

     }


    private void initToolbar() {
        ((AppCompatActivity)this).setSupportActionBar(toolbar);

             if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
                //toolbar.setNavigationOnClickListener(view -> onBackPressed());
            }
            collapsingToolbarLayout.setTitle(movie.getTitle());
            collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.transparent));
            setTitle("");
            Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w780/" + movie.getBackdropPath())
                .into(movieBackdropImage);
    }

   void initiUI()
   {
       coordinatorLayout=findViewById(R.id.coordinator_layout);
       collapsingToolbarLayout=findViewById(R.id.collapsing_toolbar_layout);

       toolbar=findViewById(R.id.toolbar);

       movieBackdropImage=findViewById(R.id.backdrop_image);

       fab=findViewById(R.id.fab);
       nestedScrollView=findViewById(R.id.nestedScrollView);


   }


     CoordinatorLayout coordinatorLayout;

    CollapsingToolbarLayout collapsingToolbarLayout;

    Toolbar toolbar;

    ImageView movieBackdropImage;
     FloatingActionButton fab;

    NestedScrollView nestedScrollView;
}
