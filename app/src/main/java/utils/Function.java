package utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;


public class Function {

    public static String getString(int StringId, Context mContext) {
        return mContext.getString(StringId);
    }

    public static void showToast(Context mContext, int mString, int length) {
        Toast.makeText(mContext, mContext.getString(mString), length).show();
    }

    public static void showToast(Context mContext, String mString, int length) {
        Toast.makeText(mContext, mString, length).show();
    }

    public static Double setTwoDigitDecimal(double twoDigitDecimal) {
        String val = String.format("%.2f", twoDigitDecimal);
        double d = 0;
        try {
            d = Double.parseDouble(val);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return d;
    }

    /*public static boolean CheckAndShowResposeMessag(Context mContext,ResponseBase resposeBase,String fields[],boolean ShowToast) {
        String msg=resposeBase.getMessage();
        if(resposeBase.getStatus().equals(ResponseBase.STATUS_SUCCESS))
            return true;
        else if(resposeBase.getMessage().equals(ResponseBase.Message_ValidationFailed) && fields!=null && fields.length>0) {
            for(int i=0;i<fields.length;i++) {
                if (resposeBase.getError().getAdditionalProperties().containsKey(CreateAccountApi.Parms_Email)) {
                 Object mObject=  resposeBase.getError().getAdditionalProperties().get(fields[i]);
                  if(mObject!=null) {
                      msg = mObject.toString();
                      break;
                  }
                }
            }

        }
        if(ShowToast)
            showToast(mContext,msg,Toast.LENGTH_LONG);
        return false;
    }*/

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();

            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    Log.i("Class", info[i].getState().toString());
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        Function.showToast(context, "Please enable your network connection", Toast.LENGTH_LONG);
        return false;
    }


}
