package com.example.nwcemp01.medipract;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;

import dmax.dialog.SpotsDialog;

/**
 * Created by tech on 8/13/2015.
 */
public class CustomProgressDialog {
    private static CustomProgressDialog ourInstance = new CustomProgressDialog();
    ProgressDialog progress;
    private boolean showing;
    AlertDialog mDialog;

    public static CustomProgressDialog getInstance() {
        return ourInstance;
    }

    public void show(Activity mContext, String Title, String Msg) {
        if (mDialog != null)
            mDialog.dismiss();
        mDialog = new SpotsDialog(mContext);
        mDialog.setTitle(Title);
        mDialog.setMessage(Msg);
        mDialog.show();

        mDialog.setCancelable(false);


    }

    public void dismiss() {
        if (mDialog != null)
            mDialog.dismiss();
    }

    public void ChageMessage(String title, String msg) {
        mDialog.setMessage(msg);
        mDialog.setTitle(title);
    }

    public boolean isShowing() {
        if (mDialog != null && mDialog.isShowing())
            return true;
        else return false;
    }
}
