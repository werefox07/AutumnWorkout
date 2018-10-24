package ru.zaharova.oxana.autumnworkout.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Date;

import ru.zaharova.oxana.autumnworkout.Model.Workout;
import ru.zaharova.oxana.autumnworkout.R;
import ru.zaharova.oxana.autumnworkout.list.WorkoutAdapter;

public class WorkoutListActivity extends AppCompatActivity {
    public static final String TAG = "WorkoutListActivityLog";
    private RecyclerView recyclerView;
    WorkoutAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_workout_list);
        Log.d(TAG, "Вызван onCreate()");

        adapter = new WorkoutAdapter();

        recyclerView = findViewById(R.id.recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.workout_list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Workout workout = new Workout("Новое упражнение");
                workout.setDescription("Описание нового упражнения");
                workout.setRecordDate(new Date());
                workout.setRecordRepsCount(0);
                workout.setRecordWeight(0);
                adapter.addWorkout(workout);
                recreate();
                return true;
            case R.id.action_quit:
                Toast.makeText(this, "Quit", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
