/*
package utils;

*/
/*
* * Created by padhu on 28-10-2015.
* *//*


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.signature.StringSignature;
import com.example.nwcemp01.medipract.R;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;


public class CommonUtility {
    public static String filePath;
    private static CommonUtility ourInstance = new CommonUtility();
    Bitmap mImageBitmapView;
  //  AlertDialog mDialog;
    Dialog dialog;

    public CommonUtility() {

    }

    public static CommonUtility getInstance() {
        return ourInstance;
    }

  */
/*   public void show(Context mContext, String Title, String Msg) {
         if (mDialog != null)
             mDialog.dismiss();
         mDialog = new SpotsDialog(mContext);
         mDialog.setTitle(Title);

         mDialog.setMessage(Msg);
         mDialog.show();

         mDialog.setCancelable(false);



     }*//*

    public void alert(Context currentactivityname, String Title, String Msg) {
        dialog = new Dialog(currentactivityname);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom);

        // dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.CENTER);

        dialog.setCancelable(false);


        dialog.show();
    }
   */
/* public void dismissed() {
        if (mDialog != null)
            mDialog.dismiss();
    }*//*


    public void dismiss() {
        if (dialog != null)
            dialog.dismiss();
    }


    */
/* public void showed() {
        if (dialog != null)

}*//*

    public int screen(Context con) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) con.getSystemService(con.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        int dens = dm.densityDpi;
        double wi = (double) width / (double) dens;
        double hi = (double) height / (double) dens;
        double x = Math.pow(wi, 2);
        double y = Math.pow(hi, 2);
        double screenInches = Math.sqrt(x + y);
        double myDb = screenInches;

        return (int) Math.round(myDb);
    }


    public boolean oncheckinginternetconnection(Context currentactivityname) {
        Boolean status = null;
        ConnectivityManager connec = (ConnectivityManager) currentactivityname.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED || connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING || connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING || connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {
            return status = true;
        } else if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED || connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {
            return status = false;
        }

        return status;
    }

    public void display_internetstatus(Context currentactivityname) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(currentactivityname);

        alertDialog.setMessage("Please Check Your Internet Connection");
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = alertDialog.create();
        alert11.show();
    }


    public void activity_navigation(Context currentactivityname, Class<?> nextactivityname) {
        Intent i = new Intent(currentactivityname, nextactivityname);
        currentactivityname.startActivity(i);


    }

    public void setlatofont(Context con, TextView v) {
        Typeface custom_font = Typeface.createFromAsset(con.getAssets(), "Lato-Regular.ttf");
        v.setTypeface(custom_font);
    }


    public void imageload(final Context currentactivityname, String a, ImageView v) {
        Glide.with(currentactivityname.getApplicationContext())
                .load(a)
                .asBitmap()
               // .placeholder(R.drawable.ic_loading_button)
                .signature(new StringSignature(UUID.randomUUID().toString()))
                .into(new BitmapImageViewTarget(v) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        super.setResource(resource);
                        Glide.get(currentactivityname).clearMemory();
                    }
                });
    }


    public static class Config {
        public static final boolean DEVELOPER_MODE = false;
    }


    public void playstore(Context mcurrentActivityName) {
        Uri uri = Uri.parse("market://details?id=" + mcurrentActivityName.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            mcurrentActivityName.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            mcurrentActivityName.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=com.tech.matchbox" + mcurrentActivityName.getPackageName())));
        }
    }

}
*/
