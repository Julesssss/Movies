package website.julianrosser.movies;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class GridListFragment extends Fragment {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    MyAdapter mAdapter;

    @SuppressWarnings("unused")
    final String TAG = getClass().getSimpleName();

    final int DEFAULT_COLUMNS = 2;

    public GridListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_grid_list, container, false);

        // RecyclerView reference
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(false); // todo - change when image size is final?????

        // Use GridLayout with 2 columns // todo - change depending on device width?
        mLayoutManager = new GridLayoutManager(getActivity(), DEFAULT_COLUMNS);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Start fetching data
        FetchMoviesTask fmt = new FetchMoviesTask();
        fmt.execute();

        return view;
    }

    public class FetchMoviesTask extends AsyncTask<Void, Void, ArrayList<Movie>> {

        final String TAG = getClass().getSimpleName();

        @Override
        protected ArrayList<Movie> doInBackground(Void... params) {


            Log.d(TAG, "doInBackground");
            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a string.
            String moviesJsonStr = null;

            String sort_by = "popularity.desc";
            String page = "2";

            try {

                final String FETCH_BASE_URL = "http://api.themoviedb.org/3/discover/movie?";
                final String SORT_PARAM = "sort_by";
                final String API_KEY_PARAM = "api_key";
                final String PAGE_PARAM = "page";


                Uri builtUri = Uri.parse(FETCH_BASE_URL).buildUpon()
                        .appendQueryParameter(SORT_PARAM, sort_by)
                        .appendQueryParameter(PAGE_PARAM, page)
                        .appendQueryParameter(API_KEY_PARAM, getString(R.string.movies_api_key))
                        .build();

                URL url = new URL(builtUri.toString());

                // Create the request to MovieDB, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line).append("\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                moviesJsonStr = buffer.toString();
            } catch (IOException e) {
                Log.e(TAG, "Error ", e);
                // If the code didn't successfully get the movie data, there's no point in attemping
                // to parse it.
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(TAG, "Error closing stream", e);
                    }
                }
            }

            try {
                return getMovieDataFromJson(moviesJsonStr); //numDays);
            } catch (JSONException e) {
                Log.e(TAG, e.getMessage(), e);
                e.printStackTrace();
            }

            // This will only happen if there was an error getting or parsing the forecast.
            return null;
        }

        private ArrayList<Movie> getMovieDataFromJson(String moviesJsonStr)
                throws JSONException {

            final String JSON_TITLE = "title";
            final String JSON_POSTER_PATH = "poster_path";
            final String JSON_OVERVIEW = "overview";
            final String JSON_ID = "id";
            final String JSON_RESULTS = "results";

            JSONObject resultsJSON = new JSONObject(moviesJsonStr);
            JSONArray moviesArray = resultsJSON.getJSONArray(JSON_RESULTS);

            ArrayList<Movie> movies = new ArrayList<Movie>();

            for (int i = 0; i < moviesArray.length(); i++) {
                // For now, using the format "Day, overview, hi/low"
                String title;
                String poster_url;
                String overview;
                int id;

                // Get the JSON object representing the day
                JSONObject movie = moviesArray.getJSONObject(i);

                title = movie.getString(JSON_TITLE);
                poster_url = getString(R.string.movies_base_url) + movie.getString(JSON_POSTER_PATH);
                overview = movie.getString(JSON_OVERVIEW);
                id = movie.getInt(JSON_ID);

                movies.add(new Movie(title, poster_url, overview, id));
            }
            return movies;
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> movies) {

            // specify an adapter (see also next example)
            mAdapter = new MyAdapter(movies);
            mRecyclerView.setAdapter(mAdapter);
        }
    }
}


