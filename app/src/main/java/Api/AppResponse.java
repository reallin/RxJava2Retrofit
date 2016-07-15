package Api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by linxj on 16/7/14.
 */

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
