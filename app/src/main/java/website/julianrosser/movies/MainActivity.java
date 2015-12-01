package website.julianrosser.movies;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Used as reference by Picasso in MyAdapter
    static Context mContext;

    static final String MOVIE_DATA_KEY = "movie_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        FragmentManager fragmentManager = getSupportFragmentManager();
        GridListFragment frag = new GridListFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, frag)
                .commit();

    }


}
