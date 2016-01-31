package website.julianrosser.movies;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Used as reference by Picasso in MyAdapter
    static Context mContext;

    static String GRID_FRAG_TAG = "grid_fragment_tag";

    static final String MOVIE_DATA_KEY = "movie_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridListFragment gridListFragment;

        mContext = this;

        // Create new GridListFragment if not already in existence
        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            gridListFragment = new GridListFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, gridListFragment, GRID_FRAG_TAG)
                    .commit();

        } else {
            // Find fragment by tag. Although we don't use this reference, we may want to in the future
            gridListFragment = (GridListFragment) getSupportFragmentManager().findFragmentByTag(GRID_FRAG_TAG);
        }
    }


}
