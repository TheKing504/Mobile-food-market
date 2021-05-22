package vega.solutions.mobilefoodmarket2.http;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import vega.solutions.mobilefoodmarket2.http.HttpClient;
import vega.solutions.mobilefoodmarket2.object.Farm;
import vega.solutions.mobilefoodmarket2.utils.Constants;

public class MobileMarketAPI {

    public static final String TAG = "MobileMarketAPI";

    private HttpClient client;
    private Gson gson;

    public MobileMarketAPI(HttpClient client) {
        this.client = client;
        this.gson = new Gson();
    }

    public void getFarms(APICallback<ArrayList<Farm>> callback) {
        client.doGet(Constants.FARMS_LINK,  new AsyncCallback<String>() {
            @Override
            public void onCallback(String response) {
                try {
                    Type collectionType = new TypeToken<ApiResponseObjectWrapper>(){}.getType();
                    ApiResponseObjectWrapper responseObjectWrapper = gson.fromJson(response, collectionType);

                    callback.onCallback(responseObjectWrapper.getData());

                } catch (Exception e) {
                    onError(e);
                }
            }

            @Override
            public void onError(@Nullable Throwable t) {
                callback.onFailure(t);
            }
        });
    }
}
