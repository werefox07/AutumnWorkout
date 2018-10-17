package ru.zaharova.oxana.autumnworkout.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import ru.zaharova.oxana.autumnworkout.R;

public class WorkoutListActivity extends AppCompatActivity {
    public static final String TAG = "WorkoutListActivity111";
    Button buttonPullUps;
    Button buttonPushUps;
    Button buttonSquats;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.list_item);
        Log.d(TAG, "Вызван onCreate()");
        //initGUI();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "Вызван onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Вызван onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Вызван onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "Вызван onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "Вызван onRestart()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Вызван onDestroy()");
        Log.e(TAG, "Вызван onDestroy()");
        Log.v(TAG, "Вызван onDestroy()");
        Log.i(TAG, "Вызван onDestroy()");
        Log.v(TAG, "Вызван onDestroy()");
        Log.wtf(TAG, "Вызван onDestroy()");
    }

    private void initGUI() {
        buttonPullUps = findViewById(R.id.button_pull_up);
        buttonPushUps = findViewById(R.id.button_push_up);
        buttonSquats = findViewById(R.id.button_squats);

        buttonPullUps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WorkoutListActivity.this,
                        "Нажата кнопка \"Pull ups\"", Toast.LENGTH_SHORT).show();
                Intent startWorkoutDetailActivity =
                        new Intent(WorkoutListActivity.this, WorkoutDetailActivity.class);
                startActivity(startWorkoutDetailActivity);
            }
        });
    }
}
