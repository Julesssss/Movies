package website.julianrosser.movies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GridListFragment extends Fragment {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    MyAdapter mAdapter;

    final int DEFAULT_COLUMNS = 2;

    public GridListFragment() {
        // Required empty public constructor
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

        String[] myDataset = {"111", "222", "333", "444", "555", "666", "777", "888"};

        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

}
