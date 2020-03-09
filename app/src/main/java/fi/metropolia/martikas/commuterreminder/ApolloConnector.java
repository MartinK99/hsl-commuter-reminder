package fi.metropolia.martikas.commuterreminder;

import com.apollographql.apollo.ApolloClient;

import okhttp3.OkHttpClient;

public class ApolloConnector {

    private static final String BASE_URL = "https://api.digitransit.fi/routing/v1/routers/hsl/index/graphql";

    public static ApolloClient setupApollo() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        return ApolloClient.builder().serverUrl(BASE_URL).okHttpClient(okHttpClient).build();
    }


}
