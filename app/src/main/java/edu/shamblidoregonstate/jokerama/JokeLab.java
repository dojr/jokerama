package edu.shamblidoregonstate.jokerama;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

public class JokeLab {
    private static JokeLab sJokeLab;
    ArrayList<Joke> mJokes;
    private static String[] jokePunchlineRandomize = {"Knock knock", "Yolo", "Noice", "Who's there", "Huh?", "Okay"};

    public static JokeLab get(Context context) {
        if (sJokeLab == null) {
            sJokeLab = new JokeLab(context);
        } return sJokeLab;
    }

    private JokeLab(Context context) {
        mJokes = new ArrayList<>();
    }

    public ArrayList<Joke> getJokes(){
        for (int i = 0; i < 20; i++) {
            String jokeName = "Joke " + i;
            String jokeDescription = "Noice" + i;
            ArrayList<String> jokePunchline = new ArrayList<>();
            for(int j = 0; j < jokePunchlineRandomize.length; j++) {
                int k = (i + j) % jokePunchlineRandomize.length;
                jokePunchline.add(jokePunchlineRandomize[k]);
            }

            mJokes.add(new Joke(jokeName, jokeDescription, jokePunchline));
        }
        return mJokes;
    };

    public Joke getJoke(UUID jokeId) {
        for (Joke joke : mJokes) {
            if (joke.getJokeID().equals(jokeId)){
                return joke;
            }
        }
        return mJokes.get(0);
    }
}
