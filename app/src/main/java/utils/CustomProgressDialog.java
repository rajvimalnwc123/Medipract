package utils;

import android.app.Activity;
import android.app.ProgressDialog;

/**
 * Created by tech on 8/13/2015.
 */
public class CustomProgressDialog {
    private static CustomProgressDialog ourInstance = new CustomProgressDialog();
    ProgressDialog progress;
    private boolean showing;

    public static CustomProgressDialog getInstance() {
        return ourInstance;
    }

    public CustomProgressDialog() {

    }

    public void show(Activity mContext, String Title, String Msg) {
        if(progress!=null)
            progress.dismiss();
         progress = ProgressDialog.show(mContext, Title, Msg, true);
        progress.setCancelable(false);


    }

    public void dismiss() {
        if(progress!=null)
            progress.dismiss();
    }
    public void ChageMessage(String title, String msg)
    {
        progress.setMessage(msg);
        progress.setTitle(title);
    }

    public boolean isShowing() {
        if(progress!=null && progress.isShowing())
        return true;
        else return false;
    }
}
