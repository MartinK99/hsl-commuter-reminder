package fi.metropolia.martikas.commuterreminder;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import org.jetbrains.annotations.NotNull;

import androidx.fragment.app.DialogFragment;

/**
 * Class for showing the TimePicker fragment when clicking the necessary button in the main activity./
 * Returns the selected time in a certain format.
 */

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    private TimePickedListener listener;

    public interface TimePickedListener {
        void onTimePicked(String time);
    }

    @NotNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        listener = (TimePickedListener) getActivity();

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        listener.onTimePicked(String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute));
    }
}
