package website.julianrosser.movies;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<Movie> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        //public TextView mTextView;

        public ViewHolder(RelativeLayout rl) {
            super(rl);

            mImageView = (ImageView) rl.findViewById(R.id.imageView);
            // mTextView = t;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<Movie> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {

        return new ViewHolder((RelativeLayout) LayoutInflater.from(MainActivity.mContext)
                .inflate(R.layout.movie_view, parent, false));
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        String urlImage = mDataset.get(position).getPoster_url();

        // Pass image url to Picasso library
        Picasso.with(MainActivity.mContext).load(urlImage).into(holder.mImageView);

        //holder.mImageView.setText(mDataset[position]); // todo - set film name?
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}