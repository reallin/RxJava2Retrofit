package Api;

import Api.Request.MovieRequest;
import Api.model.MovieInfo;

/**
 * Created by linxj on 16/7/14.
 */

public interface IMoviceApi {
    /**
     * 获取未处理订单的数字
     */
    AppResponse<MovieInfo> getMovie(MovieRequest request) throws Exception;


}
