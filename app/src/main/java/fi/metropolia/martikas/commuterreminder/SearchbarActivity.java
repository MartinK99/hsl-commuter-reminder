package fi.metropolia.martikas.commuterreminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import fi.metropolia.martikas.commuterreminder.model.Feature;
import fi.metropolia.martikas.commuterreminder.model.SearchResultStructure;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.PublishSubject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import java.util.concurrent.TimeUnit;

public class SearchbarActivity extends AppCompatActivity {

    private String sourceType;
    private EditText searchbar;
    private PublishSubject<String> subject;


    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchbar);

        recyclerView = (RecyclerView) findViewById(R.id.searchRecyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter
        searchAdapter = new SearchAdapter();
        recyclerView.setAdapter(searchAdapter);

        searchAdapter.setOnItemClickListener(new SearchAdapter.ClickListener() {
            @Override
            public void onItemClick(Feature item) {
                onItemSelect(item);
            }
        });


        Intent intent = getIntent();
        sourceType = intent.getStringExtra("fi.metropolia.martikas.searchbar.TYPE");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.digitransit.fi/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final DigitransitService service = retrofit.create(DigitransitService.class);

        searchbar = findViewById(R.id.searchbar);
        searchbar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                subject.onNext(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        subject = PublishSubject.create();
        subject.debounce(500, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(final String value) {
                        try {
                            Call<SearchResultStructure> call = service.getSearchResult(value, "gtfshsl");
                            call.enqueue(new Callback<SearchResultStructure>() {
                                @Override
                                public void onResponse(Call<SearchResultStructure> call, Response<SearchResultStructure> response) {
                                    if (response.body() != null) {
                                        String[] result = new String[response.body().getFeatures().length];
                                        int i = 0;
                                        for (Feature feature : response.body().getFeatures()) {
                                            result[i] = feature.getProperties().getLabel();
                                            i++;
                                        }
                                        searchAdapter.setSearchList(response.body());
                                        searchAdapter.notifyDataSetChanged();
                                    } else {
                                        Log.e("API_CALL", "RESPONSE BODY NULL");
                                    }
                                }

                                @Override
                                public void onFailure(Call<SearchResultStructure> call, Throwable t) {
                                    Log.e("API_CALL", t.getMessage());
                                }
                            });
                        } catch (Exception e) {
                            Log.e("SUBSCRIBER", e.getMessage());
                        }
                    }
                });
    }

    public void onItemSelect(Feature selectedItem) {
        Intent intent = new Intent();
        intent.putExtra("selectedAddress", selectedItem.getProperties().getLabel());
        intent.putExtra("coordinates", selectedItem.getGeometry().getCoordinates());
        intent.putExtra("fi.metropolia.martikas.searchbar.TYPE", sourceType);
        setResult(RESULT_OK, intent);
        finish();
    }
}
