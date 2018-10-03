package ru.zaharova.oxana.autumnworkout.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Date;

import ru.zaharova.oxana.autumnworkout.Model.Workout;
import ru.zaharova.oxana.autumnworkout.R;

public class WorkoutDetailActivity extends AppCompatActivity {
   private TextView title;
   private TextView description;
   private TextView recordDate;
   private TextView recordRepsCount;
   private TextView recordWeight;
   private TextView weight;
   private ImageView image;
   private SeekBar weightSeekBar;
   private EditText repsCountEditText;
   private Button saveRecordButton;
   private Workout workout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_detail);

        workout = new Workout("Подтягивания", "Подтягивание на перекладине", 0,
                new Date(), 0);
        initGUI(workout);
        addListeners();
    }

    private void addListeners() {
        weightSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                weight.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        saveRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int respCount = Integer.parseInt(repsCountEditText.getText().toString());
                int newWeight = Integer.parseInt(weight.getText().toString());
                if(respCount > workout.getRecordRepsCount() || newWeight > workout.getRecordWeight()){
                    workout.setRecordRepsCount(respCount);
                    workout.setRecordWeight(newWeight);
                    workout.setRecordDate(new Date());
                    recordDate.setText(workout.getFormattedRecordDate());
                    recordRepsCount.setText(String.valueOf(workout.getRecordRepsCount()));
                    recordWeight.setText(String.valueOf(workout.getRecordWeight()));
                }
            }
        });
    }

    private void initGUI(Workout workout) {
        title = findViewById(R.id.workout_detail_title);
        title.setText(workout.getTitle());
        recordDate = findViewById(R.id.workout_detail_record_date);
        recordDate.setText(workout.getFormattedRecordDate());
        recordRepsCount = findViewById(R.id.workout_detail_record_reps_count);
        recordRepsCount.setText(String.valueOf(workout.getRecordRepsCount()));
        recordWeight = findViewById(R.id.workout_detail_record_weight);
        recordWeight.setText(String.valueOf(workout.getRecordWeight()));
        description = findViewById(R.id.workout_detail_description);
        description.setText(workout.getDescription());

        weight = findViewById(R.id.workout_detail_weight);
        weightSeekBar = findViewById(R.id.workout_detail_seek_bar);
        repsCountEditText = findViewById(R.id.workout_detail_reps_count_edit_text);
        saveRecordButton = findViewById(R.id.workout_detail_save_button);

    }


}
