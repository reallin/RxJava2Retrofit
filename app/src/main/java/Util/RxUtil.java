package Util;

import Api.AppResponse;
import Api.Getable;
import rx.Single;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by linxj on 16/7/14.
 */

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
