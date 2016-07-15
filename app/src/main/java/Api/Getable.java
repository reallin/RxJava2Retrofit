package Api;

import java.util.concurrent.Callable;

/**
 * Created by linxj on 16/7/14.
 */

public interface Getable<T> extends Callable<AppResponse<T>> {
}
