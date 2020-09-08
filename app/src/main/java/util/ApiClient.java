package util;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL="https://gadsapi.herokuapp.com";

    private static final String FORM_BASE_URL ="https://docs.google.com";

    private static Retrofit retrofit;

    public static Retrofit getClient(){

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.readTimeout(30, TimeUnit.SECONDS);
        client.connectTimeout(30, TimeUnit.SECONDS);
        client.writeTimeout(30, TimeUnit.SECONDS);
            retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                    .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return  retrofit;
    }

    public static Retrofit getForm(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.readTimeout(30, TimeUnit.SECONDS);
        client.connectTimeout(30, TimeUnit.SECONDS);
        client.writeTimeout(30, TimeUnit.SECONDS);
        client.addInterceptor(interceptor);
            retrofit = new Retrofit.Builder()
                    .baseUrl(FORM_BASE_URL)
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        return  retrofit;
    }
}
