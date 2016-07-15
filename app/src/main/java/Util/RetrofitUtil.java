package Util;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by linxj on 16/7/14.
 */

public class RetrofitUtil {
    private static final int DEFAULT_TIMEOUT = 5;
    public static<T> T createApi(@NonNull Class<T> tClass,@NonNull String endPoint){

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        return new Retrofit.Builder()
                .baseUrl(endPoint)
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(tClass);
    }
    public static<T> T call(@NonNull String callDesc, @NonNull Call<T> call) throws Exception {

        Log.d("RetrofitUtil.call", callDesc);
        long last = System.currentTimeMillis();
        //AppException appException;
        try {
            Response<T> t = call.execute();


            if (t != null && t.isSuccessful()) {
                return t.body();
            } else if (t != null) {
                throw new RuntimeException();
            } else {
                NullPointerException exception = new NullPointerException("response is null.");
                throw exception;
            }
        } catch (Exception e) {
           throw new RuntimeException();
        }
    }
}
