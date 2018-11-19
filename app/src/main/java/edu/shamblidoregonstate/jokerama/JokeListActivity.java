package edu.shamblidoregonstate.jokerama;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public class JokeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new JokeListFragment();
    }
}
