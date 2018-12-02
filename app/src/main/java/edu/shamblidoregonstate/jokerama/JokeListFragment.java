package edu.shamblidoregonstate.jokerama;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class JokeListFragment extends Fragment {
    private RecyclerView mJokeListRecyclerView;
    private JokeAdapter mAdapter;
    private boolean mSubtitleVisible;

    private void updateUI() {
        JokeLab jokeLab = JokeLab.get(getActivity());
        ArrayList<Joke> jokes = jokeLab.getJokes();
        if (mAdapter == null) {
            mAdapter = new JokeAdapter(jokes);
            mJokeListRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.joke_list_fragment, container, false);
        mJokeListRecyclerView = (RecyclerView) view.findViewById(R.id.joke_recycler_view);
        mJokeListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.joke_list_fragment, menu);
        MenuItem subtitleItem = menu.findItem(R.id.show_jokes);
        subtitleItem.setTitle(R.string.joke_info);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        updateUI();

        switch(item.getItemId()) {
            case R.id.reset_jokes:
                JokeLab.get(getActivity()).resetJokes();
            case R.id.show_jokes:
                mSubtitleVisible = true;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void updateSubtitle() {
        JokeLab jokeLab = JokeLab.get(getActivity());
        int jokesCount = jokeLab.getJokes().size();
        int jokesSeen = jokeLab.getJokesSeen();
        String subtitle = getString(R.string.joke_info, jokesCount, jokesSeen);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }

    private class JokeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mJokeTitle;
        private Joke mJoke;

        public JokeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_joke, parent, false));
            itemView.setOnClickListener(this);

            mJokeTitle = itemView.findViewById(R.id.joke_title);
        }

        public void bind(Joke joke){
            mJoke = joke;
            mJokeTitle.setText(mJoke.getName());
            if (mJoke.getSeen()) {
                mJokeTitle.setBackgroundColor((Color.parseColor("#567845")));
            }
        }

        @Override
        public void onClick(View v) {
            Intent intent = JokeActivity.newIntent(getActivity(), mJoke.getJokeID());
            startActivity(intent);
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
