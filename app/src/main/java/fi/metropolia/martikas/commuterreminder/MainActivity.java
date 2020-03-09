package fi.metropolia.martikas.commuterreminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements TimePickerFragment.TimePickedListener {

    private TextView originTextView;
    private TextView destinationTextView;
    private double[] originCoordinates, destinationCoordinates;
    private int timeBuffer;
    private TextView destinationTimeTextView;

    private static final String EXTRA_TIME = "fi.metropolia.martikas.commuterreminder.extra.TIME";
    private static final String EXTRA_ORIGIN = "fi.metropolia.martikas.commuterreminder.extra.ORIGIN";
    private static final String EXTRA_DESTINATION = "fi.metropolia.martikas.commuterreminder.extra.DESTINATION";
    private static final String EXTRA_COORDINATES = "fi.metropolia.martikas.commuterreminder.extra.COORDINATES";
    private static final String EXTRA_FIELD_TYPE = "fi.metropolia.martikas.commuterreminder.extra.FIELD_TYPE";
    private static final String EXTRA_SELECTED_ADDRESS = "fi.metropolia.martikas.commuterreminder.extra.SELECTED_ADDRESS";
    private static final String EXTRA_TIMEBUFFER = "fi.metropolia.martikas.commuterreminder.extra.TIMEBUFFER";

    private static final String ACTION_SEARCH = "fi.metropolia.martikas.commuterreminder.action.SEARCH";
    private static final String ACTION_START_REMINDER = "fi.metropolia.martikas.commuterreminder.action.START_REMINDER";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        originTextView = findViewById(R.id.searchOrigin);
        destinationTextView = findViewById(R.id.searchDestination);
        destinationTimeTextView = findViewById(R.id.destinationTime);
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        destinationTimeTextView.setText(String.format("%02d", hour) + ":" + String.format("%02d", minute));
        timeBuffer = 0;

        Spinner spinner = (Spinner) findViewById(R.id.timeBufferSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.time_buffer_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        timeBuffer = 0;
                        break;
                    case 1:
                        timeBuffer = 5;
                        break;
                    case 2:
                        timeBuffer = 10;
                        break;
                    case 3:
                        timeBuffer = 15;
                        break;
                    case 4:
                        timeBuffer = 30;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void onClickOrigin(View v) {
        Intent intent = new Intent(ACTION_SEARCH);
        intent.putExtra(EXTRA_FIELD_TYPE, "origin");
        startActivityForResult(intent, 1);
    }

    public void onClickDestination(View v) {
        Intent intent = new Intent(ACTION_SEARCH);
        intent.putExtra(EXTRA_FIELD_TYPE, "destination");
        startActivityForResult(intent, 1);
    }

    public void onClickConfirm(View v) {
        if (originCoordinates != null && destinationCoordinates != null) {

                Intent intent = new Intent(ACTION_START_REMINDER);
                intent.putExtra(EXTRA_ORIGIN, originCoordinates);
                intent.putExtra(EXTRA_DESTINATION, destinationCoordinates);
                intent.putExtra(EXTRA_TIME, destinationTimeTextView.getText());
                intent.putExtra(EXTRA_TIMEBUFFER, timeBuffer);
                startActivity(intent);

        }
    }

    public void onClickDestinationTimeChange(View v) {
        DialogFragment timePickerFragment = new TimePickerFragment();
        timePickerFragment.show(getSupportFragmentManager(), "timePicker");

    }

    @Override
    public void onTimePicked(String time) {
        destinationTimeTextView.setText(time);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String result = data.getStringExtra(EXTRA_SELECTED_ADDRESS);
                double[] coordinates = data.getDoubleArrayExtra(EXTRA_COORDINATES);
                if (data.getStringExtra(EXTRA_FIELD_TYPE).equals("origin")) {
                    originTextView.setText(result);
                    originCoordinates = coordinates;
                }
                if (data.getStringExtra(EXTRA_FIELD_TYPE).equals("destination")) {
                    destinationTextView.setText(result);
                    destinationCoordinates = coordinates;
                }
            }
        }
    }
}

