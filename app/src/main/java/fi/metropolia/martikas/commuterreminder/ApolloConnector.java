package fi.metropolia.martikas.commuterreminder;

import com.apollographql.apollo.ApolloClient;

import okhttp3.OkHttpClient;

/**
 * Helper class conforming to the Apollo library.
 * Declaring the endpoint of the data source.
 */

public class ApolloConnector {

    private static final String BASE_URL = "https://api.digitransit.fi/routing/v1/routers/hsl/index/graphql";

    public static ApolloClient setupApollo() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        return ApolloClient.builder().serverUrl(BASE_URL).okHttpClient(okHttpClient).build();
    }


}
