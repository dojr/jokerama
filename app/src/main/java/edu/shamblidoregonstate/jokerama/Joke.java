package edu.shamblidoregonstate.jokerama;

import java.util.ArrayList;
import java.util.UUID;

public class Joke {
    private String mName;
    private String mDescription;
    private Boolean mSeen;
    private UUID mJokeID;

    private static String[] JokeNames = {"Dope Joke", "Not dope joke"};
    private static String[] JokeDescriptions = {"Knock knock", "Yolo"};

    Joke(String name, String description){
        mName = name;
        mDescription = description;
        mSeen = false;
        mJokeID = UUID.randomUUID();
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Boolean getSeen() {
        return mSeen;
    }

    public void setSeen(Boolean seen) {
        mSeen = seen;
    }

    public static ArrayList<Joke> JokeList(){
        ArrayList<Joke> Jokes = new ArrayList<>();
        for (int i = 0; i < JokeDescriptions.length; i++) {
            Jokes.add(new Joke(JokeNames[i], JokeDescriptions[i]));
        }
        return Jokes;
    };
}
