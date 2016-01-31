package website.julianrosser.movies;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieFragment extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate layout
        View view = getLayoutInflater().inflate(R.layout.fragment_movie, null);

        // Get movie position and reference passed from GridListFragment
        int i = getIntent().getExtras().getInt(MainActivity.MOVIE_DATA_KEY);
        Movie movie = (Movie) GridListFragment.movies.get(i);

        // Set title of Activity to Movie title
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(movie.getTitle());
        }

        // Get reference to and set released date TextView.
        TextView date_released = (TextView) view.findViewById(R.id.release_date);
        date_released.setText(movie.getRelease_date());

        // Get reference to and set rating TextView.
        TextView rating = (TextView) view.findViewById(R.id.text_rating);
        rating.setText(movie.getVoteString());

        // Get reference to and set summary TextView.
        TextView summary = (TextView) view.findViewById(R.id.summary);
        summary.setText(movie.getSummary());

        // Get reference to and set poster image.
        ImageView posterImageView = (ImageView) view.findViewById(R.id.posterImageView);
        Picasso.with(MainActivity.mContext).load(movie.getPosterUrlSize342()).into(posterImageView);

        setContentView(view);
    }



}
