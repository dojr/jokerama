package edu.shamblidoregonstate.jokerama;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.UUID;

public class JokeFragment extends Fragment {
    private TextView mJokeTitle;
    private LinearLayout mPunchline;
    private Joke mJoke;

    private static final String ARG_JOKE_ID = "edu.shamblidoregonstate.jokerama.joke_id";

    public static JokeFragment newInstance(UUID jokeID) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_JOKE_ID, jokeID);

        JokeFragment fragment = new JokeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID jokeId = (UUID) getArguments().getSerializable(ARG_JOKE_ID);
        JokeLab jokeLab = JokeLab.get(getActivity());

        mJoke = jokeLab.getJoke(jokeId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_joke, container, false);
        mPunchline = v.findViewById(R.id.punchline_layout);

        for (int i = 0; i < mJoke.getPunchLine().size() - 1; i++) {
            TextView line = new TextView(getActivity());
            line.setId(i);
            line.setText(mJoke.getPunchLine().get(i));
            if (i % 2 != 0) {
                line.setGravity(Gravity.RIGHT);
            }

            if (i > 0) {
                line.setVisibility(View.INVISIBLE);
            }

            mPunchline.addView(line);
        }

        mPunchline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 1; i < mJoke.getPunchLine().size() - 1; i++){
                    TextView makeVisible = v.findViewById(i);
                    if (makeVisible.getVisibility() == View.INVISIBLE){
                        makeVisible.setVisibility(View.VISIBLE);
                        break;
                    } else {
                        mJoke.setSeen(true);
                    }
                }
            }
        });


        return v;
    }
}
