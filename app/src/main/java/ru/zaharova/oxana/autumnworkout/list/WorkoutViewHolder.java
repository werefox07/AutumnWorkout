package ru.zaharova.oxana.autumnworkout.list;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ru.zaharova.oxana.autumnworkout.Model.Workout;
import ru.zaharova.oxana.autumnworkout.R;
import ru.zaharova.oxana.autumnworkout.activities.WorkoutDetailActivity;
import ru.zaharova.oxana.autumnworkout.utils.Constants;

class WorkoutViewHolder extends RecyclerView.ViewHolder {
    private TextView title;
    private TextView description;
    private TextView recordDate;
    private TextView recordRepsCount;
    private TextView recordWeight;
    private CardView cardView;

    public WorkoutViewHolder(@NonNull final View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.list_item_title_text_view);
        description = itemView.findViewById(R.id.list_item_description_text_view);
        recordDate = itemView.findViewById(R.id.list_item_record_date);
        recordRepsCount = itemView.findViewById(R.id.list_item_record_reps_count);
        recordWeight = itemView.findViewById(R.id.list_item_record_weight);
        cardView = itemView.findViewById(R.id.cardView);

    }

    public void bindView(final Workout workout, final int index) {
        title.setText(workout.getTitle());
        description.setText(workout.getDescription());
        recordWeight.setText(String.valueOf(workout.getRecordWeight()));
        recordRepsCount.setText(String.valueOf(workout.getRecordRepsCount()));
        recordDate.setText(workout.getFormattedRecordDate());
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = itemView.getContext();
                Intent intent = new Intent(itemView.getContext(), WorkoutDetailActivity.class);
                intent.putExtra(Constants.WORKOUT_INDEX, index);
                context.startActivity(intent);

            }
        });
    }
}
