package fi.metropolia.martikas.commuterreminder;


import android.app.Activity;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

import fi.metropolia.martikas.graphql.ItineraryQuery;

public class ReminderInterface extends Thread {

    private final TextView timeLeftText, timeLeftAdditionalText;
    private final double[] origin, destination;
    private final String arrivalTime;
    private final int arrivalHour, arrivalMinute;
    private final ImageView circleImage;
    private Activity activity;

    public ReminderInterface(Activity activity, TextView timeLeftText, TextView timeLeftAdditionalText, ImageView circleImage, double[] origin, double[] destination, String arrivalTime) {
        this.activity = activity;
        this.timeLeftText = timeLeftText;
        this.circleImage = circleImage;
        this.origin = origin;
        this.destination = destination;
        this.arrivalTime = arrivalTime;
        this.timeLeftAdditionalText = timeLeftAdditionalText;

        String[] arrivalHourAndMinute = arrivalTime.split(":");
        arrivalHour = Integer.parseInt(arrivalHourAndMinute[0]);
        arrivalMinute = Integer.parseInt(arrivalHourAndMinute[1]);
    }

    public void run() {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(c.getTime());
        c.add(Calendar.DAY_OF_YEAR, 1);
        String tomorrow = sdf.format(c.getTime());

        while (true) {
            try {
                if (hour >= arrivalHour && minute > arrivalMinute) {
                    getItinerary(origin[1], origin[0], destination[1], destination[0], tomorrow, arrivalTime);
                } else {
                    getItinerary(origin[1], origin[0], destination[1], destination[0], today, arrivalTime);
                }
                sleep(30000);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    public void getItinerary(double originLat, double originLon, double destinationLat, double destinationLon, String date, String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        ApolloConnector.setupApollo().query(
                ItineraryQuery
                        .builder()
                        .originLat(originLat)
                        .originLon(originLon)
                        .destinationLat(destinationLat)
                        .destinationLon(destinationLon)
                        .date(date)
                        .time(time)
                        .build())
                .enqueue(new ApolloCall.Callback<ItineraryQuery.Data>() {

                    @Override
                    public void onResponse(@NotNull Response<ItineraryQuery.Data> response) {
                        try {
                            if (response.data().plan().itineraries().size() > 0) {
                                Log.d("TIME", "" + response.data().plan().itineraries().get(0).legs().get(0).startTime());
                                Calendar now = Calendar.getInstance();
                                Log.d("TIME", "" + now.getTimeInMillis());
                                long difference = response.data().plan().itineraries().get(0).legs().get(0).startTime() - now.getTimeInMillis();
                                int days = (int) (difference / (1000 * 60 * 60 * 24));
                                int hours = (int) ((difference - (1000 * 60 * 60 * 24 * days)) / (1000 * 60 * 60));
                                int min = (int) (difference - (1000 * 60 * 60 * 24 * days) - (1000 * 60 * 60 * hours)) / (1000 * 60);
                                //hours = (hours < 0 ? -hours : hours);
                                Log.d("TIME", days + ":" + hours + ":" + min);

                                if (hours > 0 || min > 30) { //GREEN
                                    activity.runOnUiThread(() -> {
                                        timeLeftText.setTextColor(Color.parseColor("#FF77D353"));
                                        circleImage.setImageResource(R.drawable.circle_green);
                                    });
                                } else if (min > 10) { //YELLOW
                                    activity.runOnUiThread(() -> {
                                        timeLeftText.setTextColor(Color.parseColor("#FFFFBD00"));
                                        circleImage.setImageResource(R.drawable.circle_yellow);
                                    });
                                } else if (min > 0) { //RED
                                    activity.runOnUiThread(() -> {
                                        timeLeftText.setTextColor(Color.parseColor("#FFF95F62"));
                                        circleImage.setImageResource(R.drawable.circle_red);
                                    });
                                } else { //EXCALMATION
                                    activity.runOnUiThread(() -> {
                                        timeLeftText.setTextColor(Color.parseColor("#FFF95F62"));
                                        circleImage.setImageResource(R.drawable.circle_red_exclamation);
                                    });
                                }
                                if (min >= 0) {
                                    activity.runOnUiThread(() ->timeLeftAdditionalText.setText(R.string.reminder_additional_text));
                                    if (hours != 0) {
                                        int finalHours = hours;
                                        activity.runOnUiThread(() -> timeLeftText.setText(finalHours + "h " + min + "min"));
                                    } else {
                                        activity.runOnUiThread(() -> timeLeftText.setText(min + "min"));
                                    }
                                } else {
                                    activity.runOnUiThread(() ->timeLeftAdditionalText.setText(R.string.reminder_additional_text_negative));
                                    if (hours != 0) {
                                        int finalHours = -hours;
                                        activity.runOnUiThread(() -> timeLeftText.setText(finalHours + "h " + -min + "min ago"));
                                    } else {
                                        activity.runOnUiThread(() -> timeLeftText.setText(-min + "min ago"));
                                    }
                                }
                            } else {
                                activity.runOnUiThread(() -> timeLeftText.setText("NO ITINERARIES"));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }


                    @Override
                    public void onFailure(@NotNull ApolloException e) {
                        Log.e("REMINDERTHREAD", e.getMessage());
                    }
                });
    }

    public String convertUNIXToEET(long ts) {
        // convert seconds to milliseconds
        Date date = new java.util.Date(ts);
        // the format of your date
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm");
        // give a timezone reference for formatting (see comment at the bottom)
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+2"));
        return sdf.format(date);
    }
}
