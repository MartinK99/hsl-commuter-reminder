package fi.metropolia.martikas.commuterreminder;

import fi.metropolia.martikas.commuterreminder.model.SearchResultStructure;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Helper interface of the retrofit library.
 * Declaring the method of retrieving data from the API.
 */

public interface DigitransitService {

    @GET("geocoding/v1/autocomplete")
    Call<SearchResultStructure> getSearchResult(@Query("text") String searchText,@Query("sources") String sources);
}
