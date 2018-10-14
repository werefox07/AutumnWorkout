package ru.zaharova.oxana.autumnworkout.activities;

import android.content.ClipData;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
   public static final String TAG = "WorkoutDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_detail);
        Log.d(TAG, "Вызван onCreate");
        workout = new Workout(getString(R.string.pull_ups_text),
                getString(R.string.pull_ups_description), 0,
                new Date(), 0);
        initGUI(workout);
        addListeners();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.workout_detail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
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
                return true;
            case R.id.action_settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_quit:
                Toast.makeText(this, "Quit", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("repsCount", workout.getRecordRepsCount());
        savedInstanceState.putInt("weight", workout.getRecordWeight());
        savedInstanceState.putString("date", workout.getFormattedRecordDate());
        Log.d(TAG, "Вызван onSaveInstanceState");
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        workout.setRecordRepsCount(savedInstanceState.getInt("repsCount"));
        workout.setRecordWeight(savedInstanceState.getInt("weight"));
        try {
            workout.setRecordDateFromFormattedString(savedInstanceState.getString("date"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "Вызван onRestoreInstanceState");
        setValues();
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



}
