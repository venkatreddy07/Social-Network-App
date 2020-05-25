package com.task.socialapp.network;


import com.task.socialapp.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    private static RestClientApiService apiService;

    private static RestClient restClient;

    private void setupRestClient(String baseUrl) {
        try {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .readTimeout(180, TimeUnit.SECONDS);

            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                httpClient.interceptors().add(logging);
            }

            Retrofit restAdapter = new Retrofit.Builder()
                    .baseUrl(baseUrl)//passing API_URL
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())//passing OkHttpClient object
                    .build();
            apiService = restAdapter.create(RestClientApiService.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //double checked locking singleTon Design.
    public static RestClientApiService getApiService(String baseUrl) {
        getInstance().setupRestClient(baseUrl);
        return apiService;
    }

    private static RestClient getInstance() {
        if (restClient == null) {
            synchronized (RestClient.class) {
                if (restClient == null) {
                    restClient = new RestClient();
                }
            }
        }
        return restClient;
    }
}
