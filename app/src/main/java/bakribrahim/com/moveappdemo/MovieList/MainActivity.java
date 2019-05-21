package bakribrahim.com.moveappdemo.MovieList;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import bakribrahim.com.moveappdemo.MovieList.MoveListPresenter;
import bakribrahim.com.moveappdemo.R;

public class MainActivity extends AppCompatActivity   {

    MoveListPresenter presenter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      }



}
