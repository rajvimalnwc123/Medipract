package com.example.nwcemp01.medipract;

import android.content.Intent;

import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Api.signinApi;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import utils.Function;
import utils.Validation;

public class MainActivity extends AppCompatActivity {
    EditText mHospitalnameEdittext, mNameEdittext, mPasswordEdittext;
    //    CommonUtility obj_util = new CommonUtility();
    String sEmail, sPassword, sHospitalid;
    Toolbar mToolbars;
    TextView mToolTitle;
    CustomProgressDialog oCustomProgressDialog;


    public static SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    public static SharedPreferences.Editor editor;
    public static final String EmailId = "emailId";
    public static final String HospitalId = "hospitalid";
    public static final String Password = "password";


    public static String mEmail_sharedPref, mHospital_sharedpref, mPassword_sharedpref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mToolbars = (Toolbar) findViewById(R.id.tb_header_view);
        mToolTitle = (TextView) findViewById(R.id.tv_toolbar_title);

        //mToolbars.setNavigationIcon(R.drawable.ic_back);
      /*  mToolTitle.setText("Sign in");
        setSupportActionBar(mToolbars);
*/
        sharedpreferences = getApplicationContext().getSharedPreferences(
                MyPREFERENCES, MODE_PRIVATE);
        editor = sharedpreferences.edit();

        mHospitalnameEdittext = (EditText) findViewById(R.id.ET_hospitalname);
        mNameEdittext = (EditText) findViewById(R.id.ET_emailid);
        mPasswordEdittext = (EditText) findViewById(R.id.ET_password);


        Button mSubmitbtn = (Button) findViewById(R.id.BT_login);

        //check if the remember option has been check before
        boolean rememberMe = sharedpreferences.getBoolean("rememberMe", false);

        if (rememberMe == true) {
            //get previously stored login details
            String shospitalId = sharedpreferences.getString(HospitalId, null);
            String semailid = sharedpreferences.getString(EmailId, null);
            String spassword = sharedpreferences.getString(Password, null);

            if (shospitalId != null && semailid != null && spassword != null) {
                //fill input boxes with stored login and pass/*
                /*EditText loginEbx = (EditText) findViewById(R.id.login_box);
                EditText passEbx = (EditText) findViewById(R.id.pass_box);
                */
                mHospitalnameEdittext.setText(shospitalId);
                mNameEdittext.setText(semailid);
                mPasswordEdittext.setText(spassword);

                //set the check box to 'checked'
                CheckBox rememberMeCbx = (CheckBox) findViewById(R.id.saveLoginCheckBox);
                rememberMeCbx.setChecked(true);
            }
        }


        mSubmitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                sHospitalid = mHospitalnameEdittext.getText().toString();
                sEmail = mNameEdittext.getText().toString();
                sPassword = mPasswordEdittext.getText().toString();
                Validation oValidation = Validation.getInstance(getApplicationContext());
                boolean boolValidate = oValidation.loginValidation(sEmail, sPassword, sHospitalid);
                if (boolValidate) {
                    boolean network = Function.isNetworkAvailable(MainActivity.this);
                    if (network == false) {
                        Function.showToast(MainActivity.this, "Please activate the network", Toast.LENGTH_LONG);
                    } else {
                        final CustomProgressDialog oCustomProgressDialog = CustomProgressDialog.getInstance();
                        oCustomProgressDialog.show(MainActivity.this, "", "Please wait...");

                        signinApi.getInstance().Callresponse(new Callback<signinApi.BaseResponsesignin>() {
/*


                            @Override
                            public void success(signinApi.Example example, Response response) {
                                Toast.makeText(MainActivity.this, example.getMessage(), Toast.LENGTH_LONG).show();
                                oCustomProgressDialog.dismiss();
                                String shospitalid = example.getData().getHospital_id();
                                editor.putString(EmailId, sEmail);
                                editor.putString(HospitalId, shospitalid);
                                editor.commit();
                                mEmail_sharedPref = sharedpreferences.getString(EmailId, null);
                                mHospital_sharedpref = sharedpreferences.getString(HospitalId, null);

                                Log.v("hospital id", example.getMessage());
                                Intent intent = new Intent(MainActivity.this, Booking.class);
                                startActivity(intent);
                            }
*/

                            @Override
                            public void success(signinApi.BaseResponsesignin baseResponsesignin, Response response) {
                                Toast.makeText(MainActivity.this, baseResponsesignin.getMessage(), Toast.LENGTH_SHORT).show();
                                // if (!baseResponsesignin.getMessage().isEmpty()) {
                                //
                                //     }


                                sEmail = " ";
                                sPassword = " ";
                                sHospitalid = " ";

                                mHospitalnameEdittext.setText(" ");
                                mNameEdittext.setText(" ");
                                mPasswordEdittext.setText(" ");

                                oCustomProgressDialog.dismiss();
                                String shospitalid = baseResponsesignin.getData().getHospital_id();
                                editor.putString(EmailId, sEmail);
                                editor.putString(HospitalId, shospitalid);
                                editor.commit();
                                mEmail_sharedPref = sharedpreferences.getString(EmailId, null);
                                mHospital_sharedpref = sharedpreferences.getString(HospitalId, null);
                                Log.v("hospital id", "" + baseResponsesignin.getSuccess());
                                Log.v("hospital message", "" + baseResponsesignin.getMessage());
                                Intent intent = new Intent(MainActivity.this, Booking.class);
                                startActivity(intent);

                            }

                            @Override
                            public void failure(RetrofitError error) {
                                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                                oCustomProgressDialog.dismiss();
                                Log.v("Res error", error.getMessage());
                              /*  Intent intent = new Intent(MainActivity.this, Specialization.class);
                                startActivity(intent);
*/
                            }
                        }, sHospitalid, sEmail, sPassword);
                    }
                }
            }
        });

    }

    private void rememberMe(String sEmail, String shospitalid, String sPassword) {

        editor.putString(EmailId, sEmail);
        editor.putString(HospitalId, shospitalid);
        editor.putString(Password, sPassword);
        editor.commit();
        mEmail_sharedPref = sharedpreferences.getString(EmailId, null);
        mHospital_sharedpref = sharedpreferences.getString(HospitalId, null);
        mPassword_sharedpref = sharedpreferences.getString(Password, null);


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
        startActivity(intent);
        finish();
        System.exit(0);
    }

}
