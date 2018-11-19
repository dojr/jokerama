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
import android.widget.TextView;

import java.util.ArrayList;

public class JokeListFragment extends Fragment {
    private RecyclerView mJokeListRecyclerView;
    private JokeAdapter mAdapter;

    private void updateUI() {
        ArrayList<Joke> jokes = Joke.JokeList();
        mAdapter = new JokeAdapter(jokes);
        mJokeListRecyclerView.setAdapter(mAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.joke_list_fragment, container, false);
        mJokeListRecyclerView = (RecyclerView) view
                    .findViewById(R.id.joke_recycler_view);
        mJokeListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private class JokeHolder extends RecyclerView.ViewHolder {
        private TextView mJokeTitle;
        private Joke mJoke;

        public JokeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_joke, parent, false));
            mJokeTitle = itemView.findViewById(R.id.joke_title);
        }

        public void bind(Joke joke){
            mJoke = joke;
            mJokeTitle.setText(mJoke.getName());

        }
    }

    private class JokeAdapter extends RecyclerView.Adapter<JokeHolder> {
        private ArrayList<Joke> mJokes;

        public JokeAdapter(ArrayList<Joke> jokes) {
            mJokes = jokes;
        }

        @NonNull
        @Override
        public JokeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new JokeHolder(LayoutInflater.from(getActivity()), viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull JokeHolder jokeHolder, int i) {
            Joke joke = mJokes.get(i);
            jokeHolder.bind(joke);
        }

        @Override
        public int getItemCount() {
            return mJokes.size();
        }
    }
}
