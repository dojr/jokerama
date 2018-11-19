package edu.shamblidoregonstate.jokerama;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class JokeListFragment extends Fragment {
    private RecyclerView mJokeListRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.joke_list_fragment, container, false);
        mJokeListRecyclerView = (RecyclerView) view
                    .findViewById(R.id.joke_recycler_view);
        mJokeListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }
}
