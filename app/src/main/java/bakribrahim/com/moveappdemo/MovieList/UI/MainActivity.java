package bakribrahim.com.moveappdemo.MovieList.UI;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import androidx.fragment.app.FragmentManager;
import bakribrahim.com.moveappdemo.CallBack.FragmentCallBack;
import bakribrahim.com.moveappdemo.Models.Movie.Movie;
import bakribrahim.com.moveappdemo.MovieDetails.UI.DetailsActivity;
import bakribrahim.com.moveappdemo.MovieDetails.UI.MovieDetailsFragment;
import bakribrahim.com.moveappdemo.MovieList.MVP.presenter.MoveListPresenter;
import bakribrahim.com.moveappdemo.R;
import bakribrahim.com.moveappdemo.Utilites.Util;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MainActivity extends AppCompatActivity implements View.OnClickListener,FragmentCallBack  {

    MoveListPresenter presenter ;
    BottomAppBar BottomAppBar;
    FragmentManager fragmentManager;
    private boolean mTwoPane;
    FloatingActionButton fab;
    TextView appname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         appname =findViewById(R.id.your_title);
         BottomAppBar=findViewById(R.id.bottomAppBar);
        BottomAppBar.replaceMenu(R.menu.main_menu);
        fab=findViewById(R.id.fab);
        fab.setOnClickListener(this);
        fragmentManager=getSupportFragmentManager();
         BottomAppBar.setOnMenuItemClickListener(new androidx.appcompat.widget.Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.menu_popular:
                        showFragment(Util.POPULAR_MOVIES);
                         break;
                    case R.id.menu_toprated :
                        showFragment(Util.TOPRATED_MOVIES);

                        break;

                    case R.id.menu_upcoming :
                        showFragment(Util.UPCOMING_MOVIES);
                         break;


                }
                return false;
            }
        });
        if(findViewById(R.id.movie_detail_container) != null){
                 mTwoPane = true;
                 Show_detialsFramgnet(getLastMoview ());
                 showFragment(Util.POPULAR_MOVIES);
         }
        else
            showFragment(Util.POPULAR_MOVIES);

        setuptitil();

    }



    popularFragment fragment;
    MovieDetailsFragment Detialsfragment;
      private  void showFragment(String type )
     {
         fragment =new popularFragment();
        Bundle bundle =new Bundle();
        bundle.putString(Util.Fragment, type);

         fragment.setArguments(bundle);
         fragmentManager.beginTransaction().replace(
                R.id.container, fragment)
                .commit();
     }

     private void setuptitil(){
         String text = "<font color=#f70e18>M</font><font color=#f4eb0c>O</font>" +
                 "<font color=#e066a5>V</font><font color=#72d936>A</font>" +
                 "<font color=#4dbae2>V</font><font color=#ff2ca0>I</font>";
         appname.setText(Html.fromHtml(text));

     }
    @Override
    public void Show_detialsFramgnet(Movie movie) {
        save_shared (movie);
        if (mTwoPane)
          {
              Bundle bundle =new Bundle();
              bundle.putSerializable("Movie",movie);
              Detialsfragment =new MovieDetailsFragment();
              Detialsfragment.setArguments(bundle);
              fragmentManager.beginTransaction().replace(R.id.movie_detail_container,Detialsfragment
                      ,"Detialsfragment").commit();
          }
         else{
         Intent intent =new Intent(MainActivity.this, DetailsActivity.class);
        intent.putExtra("move",movie);
        startActivity(intent);
         }

    }

    public void save_shared (Movie movie){
        SharedPreferences prefs = getSharedPreferences(Util.AppSharedPreferance, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(movie);
        editor.putString(Util.LastMovieSaved, json);
        editor.commit();
    }

    public Movie getLastMoview (){
        SharedPreferences prefs = getSharedPreferences(Util.AppSharedPreferance, MODE_PRIVATE);
         Gson gson = new Gson();
        String json = prefs.getString(Util.LastMovieSaved, "");
        Movie lastmovie = gson.fromJson(json, Movie.class);
        return lastmovie;
    }

    @Override
    public void onClick(View v) {
        showFragment(Util.FAVOUIRTE);
    }
}
