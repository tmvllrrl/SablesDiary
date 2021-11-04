package com.example.sablesdiary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.PrimaryKey;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sablesdiary.database.DiaryEntry;
import com.example.sablesdiary.database.DiaryEntryViewModel;

public class DetailActivity extends AppCompatActivity {

    private DiaryEntryViewModel mViewModel;

    private EditText foodOriginallyEditText;
    private EditText foodAfterEditText;
    private EditText waterOriginallyEditText;
    private EditText waterAfterEditText;
    private EditText weightEditText;
    private TextView dateTextView;

    private Button saveButton;
    private Button pickDateButton;

    private int id = -1;
    private DiaryEntry entry;

    private String dayOfWeek;
    private String date = "";
    private int month;
    private int day;
    private int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setTitle("Editor");

        mViewModel = new ViewModelProvider(this).get(DiaryEntryViewModel.class);

        foodOriginallyEditText = findViewById(R.id.et_detailActivity_foodOriginally);
        foodAfterEditText = findViewById(R.id.et_detailActivity_foodAfter);
        waterOriginallyEditText = findViewById(R.id.et_detailActivity_waterOriginally);
        waterAfterEditText = findViewById(R.id.et_detailActivity_waterAfter);
        weightEditText = findViewById(R.id.et_detailActivity_weight);
        dateTextView = findViewById(R.id.tv_detailActivity_date);

        saveButton = findViewById(R.id.bt_detailActivity_save);
        pickDateButton = findViewById(R.id.bt_detailActivity_pickDate);

        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("diary_id")) { //Updating an entry
                id = intent.getIntExtra("diary_id", -1);

                mViewModel.getEntryById(id).observe(this, new Observer<DiaryEntry>() {
                    @Override
                    public void onChanged(DiaryEntry diaryEntry) {
                        entry = diaryEntry;
                        setUI(entry);
                    }
                });

            }
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String foodOriginallyStr = foodOriginallyEditText.getText().toString();
                int foodOriginally = Integer.parseInt(foodOriginallyStr);

                String foodAfterStr = foodAfterEditText.getText().toString();
                int foodAfter = Integer.parseInt(foodAfterStr);

                String waterOriginallyStr = waterOriginallyEditText.getText().toString();
                double waterOriginally = Double.parseDouble(waterOriginallyStr);

                String waterAfterStr = waterAfterEditText.getText().toString();
                double waterAfter = Double.parseDouble(waterAfterStr);

                String weightStr = weightEditText.getText().toString();
                int weight = Integer.parseInt(weightStr);

                if (id != -1) { //Updating an entry. No dates in here
                    entry.setFoodOriginally(foodOriginally);
                    entry.setFoodAfter(foodAfter);
                    entry.setWaterOriginally(waterOriginally);
                    entry.setWaterAfter(waterAfter);
                    entry.setWeight(weight);

                    mViewModel.update(entry);

                } else { //Creating a new entry. Dates needed to be saved in here
                    DiaryEntry newEntry = new DiaryEntry(dayOfWeek, date, month, day, year, foodOriginally, foodAfter, waterOriginally, waterAfter,
                            weight);
                    mViewModel.insertEntry(newEntry);
                }

                finish();
            }
        });

        pickDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new DatePickerFragment();
                dialogFragment.show(getSupportFragmentManager(), getString(R.string.date_picker));
            }
        });

    }

    private void setUI(DiaryEntry entry) {
        //Want to set all of the Edit Text's with the data
        foodOriginallyEditText.setText(entry.getFoodOriginally() + "");
        foodAfterEditText.setText(entry.getFoodAfter() + "");
        waterOriginallyEditText.setText(entry.getWaterOriginally() + "");
        waterAfterEditText.setText(entry.getWaterAfter() + "");
        weightEditText.setText(entry.getWeight() + "");
        dateTextView.setText(entry.getDate());
    }

    public void processDatePickerResults(int year, int month, int dayOfMonth, int dayOfWeek) {
        String monthString = Integer.toString(month+1);
        String dayString = Integer.toString(dayOfMonth);
        String yearString = Integer.toString(year);

        this.date = (monthString + "/" + dayString + "/" + yearString);
        dateTextView.setText(this.date);

        String dayOfWeekTemp = "Sunday";
        switch (dayOfWeek) {
            case 1:
                dayOfWeekTemp = "Sunday";
                break;
            case 2:
                dayOfWeekTemp = "Monday";
                break;
            case 3:
                dayOfWeekTemp = "Tuesday";
                break;
            case 4:
                dayOfWeekTemp = "Wednesday";
                break;
            case 5:
                dayOfWeekTemp = "Thursday";
                break;
            case 6:
                dayOfWeekTemp = "Friday";
                break;
            case 7:
                dayOfWeekTemp = "Saturday";
                break;
            default:
                break;
        }

        this.dayOfWeek = dayOfWeekTemp;

        this.month = month;
        this.day = dayOfMonth;
        this.year = year;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(DetailActivity.this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}