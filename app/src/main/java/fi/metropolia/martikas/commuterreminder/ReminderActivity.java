package fi.metropolia.martikas.commuterreminder;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ReminderActivity extends AppCompatActivity {

    private TextView timeLeftText;
    private ReminderInterface reminderInterface;
    private ImageView circleImage;
    private TextView timeLeftAdditionalText;

    private String arrivalTime;
    private double[] origin, destination;
    private int timeBuffer;

    private static final String EXTRA_TIME = "fi.metropolia.martikas.commuterreminder.extra.TIME";
    private static final String EXTRA_ORIGIN = "fi.metropolia.martikas.commuterreminder.extra.ORIGIN";
    private static final String EXTRA_DESTINATION = "fi.metropolia.martikas.commuterreminder.extra.DESTINATION";
    private static final String EXTRA_TIMEBUFFER = "fi.metropolia.martikas.commuterreminder.extra.TIMEBUFFER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        Intent recievedIntent = getIntent();
        arrivalTime = recievedIntent.getStringExtra(EXTRA_TIME);
        origin = recievedIntent.getDoubleArrayExtra(EXTRA_ORIGIN);
        destination = recievedIntent.getDoubleArrayExtra(EXTRA_DESTINATION);
        timeBuffer = recievedIntent.getIntExtra(EXTRA_TIMEBUFFER, 0);

        timeLeftText = findViewById(R.id.timeLeftText);
        circleImage = findViewById(R.id.circleImageView);
        timeLeftAdditionalText = findViewById(R.id.timeLeftAdditionalText);

        reminderInterface = new ReminderInterface(this, timeLeftText, timeLeftAdditionalText, circleImage, origin, destination, arrivalTime, timeBuffer);
        reminderInterface.start();
    }


    public void onClickChangeSettings(View v) {
        reminderInterface.interrupt();
        finish();
    }
}
