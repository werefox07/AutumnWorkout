package ru.zaharova.oxana.autumnworkout.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

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
   private Button shareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_detail);

        workout = new Workout(getString(R.string.pull_ups_text),
                getString(R.string.pull_ups_description), 0,
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
                String respCountString = repsCountEditText.getText().toString();
                int respCount;
                if(!respCountString.equals("")) {
                    respCount = Integer.parseInt(respCountString);

                    int newWeight = Integer.parseInt(weight.getText().toString());
                    if(respCount > workout.getRecordRepsCount() || newWeight > workout.getRecordWeight()){
                        workout.setRecordRepsCount(respCount);
                        workout.setRecordWeight(newWeight);
                        workout.setRecordDate(new Date());
                        setValues();
                        Toast toast = Toast.makeText(getApplicationContext(),
                                getResources().getString(R.string.saved_record_message),
                                Toast.LENGTH_SHORT
                        );
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                getResources().getString(R.string.not_saved_record_message),
                                Toast.LENGTH_SHORT
                        );
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            getResources().getString(R.string.number_count_message),
                            Toast.LENGTH_SHORT
                    );
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String textToSend = getString(R.string.share_hello_record_text) +
                        "\n" + getString(R.string.exercise_share_text) + " "+ workout.getTitle() +
                        "\n" + getString(R.string.weight_text) + " " + workout.getRecordWeight() +
                        "\n" + getString(R.string.reps_count_text) + " " + workout.getRecordRepsCount();
                intent.putExtra(Intent.EXTRA_TEXT, textToSend);
                try
                {
                    startActivity(Intent.createChooser(intent, getString(R.string.share_record_text)));
                }
                catch (android.content.ActivityNotFoundException ex)
                {
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.share_error_text), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setValues() {
        recordDate.setText(workout.getFormattedRecordDate());
        recordRepsCount.setText(String.valueOf(workout.getRecordRepsCount()));
        recordWeight.setText(String.valueOf(workout.getRecordWeight()));
    }

    private void initGUI(Workout workout) {
        title = findViewById(R.id.workout_detail_title);
        title.setText(workout.getTitle());
        recordDate = findViewById(R.id.workout_detail_record_date);
        recordRepsCount = findViewById(R.id.workout_detail_record_reps_count);
        recordWeight = findViewById(R.id.workout_detail_record_weight);

        weight = findViewById(R.id.workout_detail_weight);
        weightSeekBar = findViewById(R.id.workout_detail_seek_bar);
        repsCountEditText = findViewById(R.id.workout_detail_reps_count_edit_text);
        saveRecordButton = findViewById(R.id.workout_detail_save_button);
        shareButton = findViewById(R.id.share_button);
        setValues();

    }


}
