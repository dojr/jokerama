package edu.shamblidoregonstate.jokerama;

import java.util.ArrayList;
import java.util.UUID;

public class Joke {
    private String mName;
    private String mDescription;
    private Boolean mSeen;

    private UUID mJokeID;
    private ArrayList<String> mPunchline;

    Joke(String name, String description, ArrayList<String> punchline){
        mName = name;
        mDescription = description;
        mSeen = false;
        mJokeID = UUID.randomUUID();
        mPunchline = punchline;
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

    public ArrayList<String> getPunchLine() {
        return mPunchline;
    }

    public void setPunchLine(ArrayList<String> punchLine) {
        mPunchline = punchLine;
    }

    public UUID getJokeID() {
        return mJokeID;
    }

    public void setJokeID(UUID mJokeID) {
        this.mJokeID = mJokeID;
    }
}
