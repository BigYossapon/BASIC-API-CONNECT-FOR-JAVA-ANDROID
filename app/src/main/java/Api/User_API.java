package Api;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import Model.UserInfo;
import Utils.Network;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class User_API {


    //region GetUser
    public interface DistributeProfileListener {
        void onListener(final List<UserInfo> info, Integer http_code);
    }

    DistributeProfileListener distributeProfileListener;

    public void GET_DistributeProfile(final Context context,int id, final DistributeProfileListener listener) {
        this.distributeProfileListener = listener;
        try {
            RestManager restManager = new RestManager();
            Call<ResponseBody> call;

            call = restManager.UserServiceRoom().getDistributeProfile(id);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        if (response.code() == 200) {
                            List<UserInfo> info = new Gson().fromJson(response.body().string(), new TypeToken<List<UserInfo>>() {
                            }.getType());
                            listener.onListener(info, response.code());
                        } else {
                            listener.onListener(null, 0);
                        }
                    } catch (Exception ex) {
                        Log.d("API_ERROR", "" + ex.getMessage());
                        listener.onListener(null, 0);
                    }
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    listener.onListener(null, 0);
                }
            });
        } catch (Exception ex) {
            Log.d("API_ERROR", "" + ex.getMessage());
            listener.onListener(null, 0);
        }
    }
    //endregion

    //region PostUserinfo
    public interface DistributeAddUserinfoListener {
        void onListener( Integer http_code);
    }

    DistributeAddUserinfoListener distributeAddUserinfoListener;

    public void POST_DistributeUserinfo(final Context context,UserInfo addUserinfo, final DistributeAddUserinfoListener listener) {
        this.distributeAddUserinfoListener = listener;
        try {
            RestManager restManager = new RestManager();
            Call<ResponseBody> call;

            call = restManager.UserServiceRoom().postDistributeUserinfo(addUserinfo);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        if (response.code() == 200) {

                            listener.onListener( response.code());
                        } else {
                            listener.onListener( 0);
                        }
                    } catch (Exception ex) {
                        Log.d("API_ERROR", "" + ex.getMessage());
                        listener.onListener( 0);
                    }
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    if(Network.isNetworkConnected(context)){
                        listener.onListener(504);
                    }else{
                        listener.onListener(0);
                    }
                }
            });
        } catch (Exception ex) {
            Log.d("API_ERROR", "" + ex.getMessage());
            listener.onListener( 0);
        }
    }
    //endregion

    //region PutUserinfo
    public interface DistributeEditUserinfoListener {
        void onListener( Integer http_code);
    }

    DistributeEditUserinfoListener distributeEditUserinfoListener;

    public void PUT_DistributeUserinfo(final Context context,UserInfo EditUserinfo, final DistributeEditUserinfoListener listener) {
        this.distributeEditUserinfoListener = listener;
        try {
            RestManager restManager = new RestManager();
            Call<ResponseBody> call;

            call = restManager.UserServiceRoom().putDistributeUserinfo(EditUserinfo);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        if (response.code() == 200) {

                            listener.onListener( response.code());
                        } else {
                            listener.onListener( 0);
                        }
                    } catch (Exception ex) {
                        Log.d("API_ERROR", "" + ex.getMessage());
                        listener.onListener( 0);
                    }
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    if(Network.isNetworkConnected(context)){
                        listener.onListener(504);
                    }else{
                        listener.onListener(0);
                    }
                }
            });
        } catch (Exception ex) {
            Log.d("API_ERROR", "" + ex.getMessage());
            listener.onListener( 0);
        }
    }
    //endregion

    //region DeleteUserinfo
    public interface DistributeDeleteUserinfoListener {
        void onListener( Integer http_code);
    }

    DistributeDeleteUserinfoListener distributeDeleteUserinfoListener;

    public void DELETE_DistributeUserinfo(final Context context,int id, final DistributeDeleteUserinfoListener listener) {
        this.distributeDeleteUserinfoListener = listener;
        try {
            RestManager restManager = new RestManager();
            Call<ResponseBody> call;

            call = restManager.UserServiceRoom().deleteDistributeUserinfo(id);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        if (response.code() == 200) {

                            listener.onListener( response.code());
                        } else {
                            listener.onListener( 0);
                        }
                    } catch (Exception ex) {
                        Log.d("API_ERROR", "" + ex.getMessage());
                        listener.onListener( 0);
                    }
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    if(Network.isNetworkConnected(context)){
                        listener.onListener(504);
                    }else{
                        listener.onListener(0);
                    }
                }
            });
        } catch (Exception ex) {
            Log.d("API_ERROR", "" + ex.getMessage());
            listener.onListener( 0);
        }
    }
    //endregion
}
