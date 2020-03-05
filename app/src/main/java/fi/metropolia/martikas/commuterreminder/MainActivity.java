package fi.metropolia.martikas.commuterreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView originTextView;
    private TextView destinationTextView;
    private double[] originCoordinates, destinationCoordinates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        originTextView = findViewById(R.id.searchOrigin);
        destinationTextView = findViewById(R.id.searchDestination);
    }


    public void onClickOrigin(View v) {
        Intent intent = new Intent("SEARCH");
        intent.putExtra("fi.metropolia.martikas.searchbar.TYPE", "origin");
        startActivityForResult(intent, 1);
    }

    public void onClickDestination(View v) {
        Intent intent = new Intent("SEARCH");
        intent.putExtra("fi.metropolia.martikas.searchbar.TYPE", "destination");
        startActivityForResult(intent, 1);
    }

    public void onClickConfirm() {
        Intent intent = new Intent("");
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String result = data.getStringExtra("selectedAddress");
                double[] coordinates = data.getDoubleArrayExtra("coordinates");
                if (data.getStringExtra("fi.metropolia.martikas.searchbar.TYPE").equals("origin")) {
                    originTextView.setText(result);
                    originCoordinates = coordinates;
                }
                if (data.getStringExtra("fi.metropolia.martikas.searchbar.TYPE").equals("destination")) {
                    destinationTextView.setText(result);
                    destinationCoordinates = coordinates;
                }
            }
        }
    }
}

