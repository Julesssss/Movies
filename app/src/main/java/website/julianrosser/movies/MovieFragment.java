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

        View view = getLayoutInflater().inflate(R.layout.fragment_movie, null);

        int i = getIntent().getExtras().getInt(MainActivity.MOVIE_DATA_KEY);
        Movie movie = (Movie) GridListFragment.movies.get(i);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(movie.getTitle());
        }

        TextView date_released = (TextView) view.findViewById(R.id.release_date);
        date_released.setText(movie.getRelease_date());

        TextView rating = (TextView) view.findViewById(R.id.text_rating);
        rating.setText(movie.getVoteString());

        TextView summary = (TextView) view.findViewById(R.id.summary);
        summary.setText(movie.getSummary());

        ImageView posterImageView = (ImageView) view.findViewById(R.id.posterImageView);
        Picasso.with(MainActivity.mContext).load(movie.getPosterUrlSize342()).into(posterImageView);

        setContentView(view);
    }



}
