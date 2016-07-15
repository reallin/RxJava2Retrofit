package Api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by linxj on 16/7/14.
 */

public class MovieInfo {
    @SerializedName("max")
    public int max;
    @Override
    public String toString() {
        return "MovieData{" +
                "max=" + max +
                '}';
    }
}
