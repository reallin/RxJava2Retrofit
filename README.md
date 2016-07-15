# RxJava2Retrofit
RxJava与Retrofit联合使用，并对它们作了下封装。
## 使用效果图
![](https://github.com/reallin/RxJava2Retrofit/blob/master/retrofit.png)
## 导入的lib
```
 compile 'com.google.code.gson:gson:2.6.2'
    compile 'com.squareup.retrofit2:converter-gson:2.0.2'
    compile 'io.reactivex:rxjava:1.1.5'
    compile 'io.reactivex:rxandroid:1.2.0'
    compile 'com.squareup.retrofit2:retrofit:2.0.2'
```
## Retrofit的封装
```
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
```
## Rxjava的封装：
```
public class RxUtil {

    public static <Resp> rx.Observable<AppResponse<Resp>> call(final Getable getable) {
        return Single.fromCallable(getable)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toObservable();

    }

    /**
     * Created by linxj on 16/7/14.
     */

    public static class RestrofitUtil {
    }
}
```
## 两者配合使用：
```
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
```
## gson的使用，简单举个例子：
```
public class AppResponse<T> implements IPojo{

    @SerializedName("count")
    public int resultCode ;
    @SerializedName("start")
    public String resultMessage;
    @SerializedName("subjects")
    public List<T> subjects;
//    @SerializedName("rating")
//    public T rating;
    @Override
    public String toString() {
        String dataStr = resultCode+" "+subjects.get(0).toString()+" " ;

        return dataStr;
    }

}

```
如果返回json的key为count，则用@SerializedName在Model类中标明.如果是jsonArray，就用List<T>,如果是对象，可以先用泛型T。再自己
定义对象。
具体的可以参考代码。
