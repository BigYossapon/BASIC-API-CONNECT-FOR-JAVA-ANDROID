package Api;

import Model.UserInfo;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface User_service {

    @GET("get/{ID}")
    Call<ResponseBody> getDistributeProfile(@Path("ID") int id);

    @POST("add")
    Call<ResponseBody> postDistributeUserinfo(@Body UserInfo AddUserinfo);

    @PUT("edit")
    Call<ResponseBody> putDistributeUserinfo(@Body UserInfo EditUserinfo);

//    @FormUrlEncoded
//    @HTTP(method = "delete", path = "delete/{ID}",hasBody = false)
//    Call<ResponseBody>  deleteDistributeUserinfo(@Path("ID") int id);

    @DELETE("delete/{ID}")
    Call<ResponseBody> deleteDistributeUserinfo(@Path("ID") int id);
}
