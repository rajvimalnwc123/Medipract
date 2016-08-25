package utils;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by android1 on 14-10-2015.
 */
public class CommonMember {

    public static final String FACEBOOK_SIGNUP = "2";
    //   public static String BASE_URL = "http://192.168.1.114:1082/api/v1";
// public static String BASE_URL = "http://matchbox.technodus.com/frontend/web/api/v1";
    public static String BASE_URL = "http://demo.medipract.com/api";

    public static final String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    public static final String NAME_REGEX = "[a-zA-z0-9 ]+";

    private static CommonMember ourInstance = new CommonMember();
    RestAdapter ApiBuilder;
    private String PrefName_User = "User";

    public static CommonMember getInstance() {
        if (ourInstance == null) {
            synchronized (CommonMember.class) {
                if (ourInstance == null)
                    ourInstance = new CommonMember();
            }
        }
        ourInstance.config();
        return ourInstance;
    }

    private CommonMember() {
    }
    private void config() {
//        ApiBuilder = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL)
//                .setEndpoint(BASE_URL)
//                .build();


        OkHttpClient mOkHttp = new OkHttpClient();
        mOkHttp.setReadTimeout(150, TimeUnit.SECONDS);
        mOkHttp.setConnectTimeout(150, TimeUnit.SECONDS);
        ApiBuilder = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(new OkClient(mOkHttp))
                .setEndpoint(BASE_URL)
                .build();

    }
    public RestAdapter getApiBuilder() {
        return ApiBuilder;
    }

    /**
     * We inject our own concept with json parsing when we convert to pojo class*
     */
    public void setApiBuilder(RestAdapter apiBuilder) {
        ApiBuilder = apiBuilder;
    }



}
