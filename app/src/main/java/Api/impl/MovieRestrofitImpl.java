package Api.impl;

import Api.AppResponse;
import Api.IMoviceApi;
import Api.Request.MovieRequest;
import Api.model.MovieInfo;
import Util.RetrofitUtil;

/**
 * Created by linxj on 16/7/14.
 */

public class MovieRestrofitImpl implements IMoviceApi {
    private IMovieRestrofitApi retrofitApi;
    IMovieRestrofitApi getRetrofitApi() {
        if (retrofitApi == null) {
            String accountUrl = "https://api.douban.com";
            retrofitApi = RetrofitUtil.createApi(IMovieRestrofitApi.class, accountUrl);
        }
        return retrofitApi;
    }

    @Override
    public AppResponse<MovieInfo> getMovie(MovieRequest request) throws Exception {

        return RetrofitUtil.call(
                "MovieRetrofitImpl.getMovie",
                getRetrofitApi().getMovie()
        );
    }
}
