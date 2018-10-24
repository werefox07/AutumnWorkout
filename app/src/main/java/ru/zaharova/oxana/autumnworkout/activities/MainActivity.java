package ru.zaharova.oxana.autumnworkout.activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.zaharova.oxana.autumnworkout.R;
import ru.zaharova.oxana.autumnworkout.fragments.WorkoutDetailFragment;
import ru.zaharova.oxana.autumnworkout.fragments.WorkoutListFragment;
import ru.zaharova.oxana.autumnworkout.interfaces.OnListItemClickListener;

public class MainActivity extends AppCompatActivity implements OnListItemClickListener {
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WorkoutListFragment listFragment = new WorkoutListFragment();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.container, listFragment);
        transaction.commit();
    }

    @Override
    public void onListItemClickListener(int index) {
        WorkoutDetailFragment detailFragment = WorkoutDetailFragment.initFragment(index);
        fragmentManager.beginTransaction().replace(R.id.container, detailFragment).commit();
    }
}
