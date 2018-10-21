package ru.zaharova.oxana.autumnworkout.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ru.zaharova.oxana.autumnworkout.Model.Workout;
import ru.zaharova.oxana.autumnworkout.Model.WorkoutList;
import ru.zaharova.oxana.autumnworkout.R;

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
                LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private class WorkoutAdapter extends RecyclerView.Adapter<WorkoutViewHolder> {
        private List<Workout> workoutList = WorkoutList.getInstance().getWorkouts();

        @NonNull
        @Override
        public WorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(
                    R.layout.list_item,
                    viewGroup,
                    false
            );
            return new WorkoutViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull WorkoutViewHolder workoutViewHolder, int index) { 
            workoutViewHolder.bindView(workoutList.get(index));

        }

        @Override
        public int getItemCount() {
            return workoutList != null ? workoutList.size() : 0;
        }
    }

    private static class WorkoutViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        TextView recordDate;
        TextView recordRepsCount;
        TextView recordWeight;

        public WorkoutViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.list_item_title_text_view);
            description = itemView.findViewById(R.id.list_item_description_text_view);
            recordDate = itemView.findViewById(R.id.list_item_record_date);
            recordRepsCount = itemView.findViewById(R.id.list_item_record_reps_count);
            recordWeight = itemView.findViewById(R.id.list_item_record_weight);
        }

        public void bindView(Workout workout) {
            title.setText(workout.getTitle());
            description.setText(workout.getDescription());
            recordWeight.setText(String.valueOf(workout.getRecordWeight()));
            recordRepsCount.setText(String.valueOf(workout.getRecordRepsCount()));
            recordDate.setText(workout.getFormattedRecordDate());
        }
    }


}
