package com.example.linxj.retrofit2rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import Api.AppResponse;
import Api.Getable;
import Api.IMoviceApi;
import Api.Request.MovieRequest;
import Api.impl.MovieRestrofitImpl;
import Api.model.MovieInfo;
import Util.RxUtil;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity {
    IMoviceApi driverApi = new MovieRestrofitImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MovieRequest request = new MovieRequest();
        RxUtil.call(
                new Getable<MovieInfo>() {
                    @Override
                    public AppResponse<MovieInfo> call() throws Exception {
                      return   driverApi.getMovie(request);
                    }
                }
        ).subscribe(
                new Subscriber<AppResponse<Object>>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(MainActivity.this,"commplete",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Toast.makeText(MainActivity.this,"error",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(AppResponse<Object> objectAppResponse) {
                        Toast.makeText(MainActivity.this,objectAppResponse.toString(),Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}
