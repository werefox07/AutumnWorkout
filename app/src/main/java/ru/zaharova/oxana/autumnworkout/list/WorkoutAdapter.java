package ru.zaharova.oxana.autumnworkout.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.zaharova.oxana.autumnworkout.Model.Workout;
import ru.zaharova.oxana.autumnworkout.Model.WorkoutList;
import ru.zaharova.oxana.autumnworkout.R;
import ru.zaharova.oxana.autumnworkout.activities.WorkoutListActivity;


public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutViewHolder> {
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
        workoutViewHolder.bindView(workoutList.get(index), index);
    }

    @Override
    public int getItemCount() {
        return workoutList != null ? workoutList.size() : 0;
    }

    public void addWorkout(Workout workout) {
        this.workoutList.add(workout);
    }
}
