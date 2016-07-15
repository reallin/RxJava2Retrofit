package Api.impl;

import Api.AppResponse;
import Api.model.MovieInfo;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by linxj on 16/7/14.
 */

public interface IMovieRestrofitApi {
    /**
     * 获取未处理订单的数字
     */
    @GET("/v2/movie/top250?start=0&count=10")
    Call<AppResponse<MovieInfo>> getMovie();

}
