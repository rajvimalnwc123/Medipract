package com.example.nwcemp01.medipract;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import Api.submitformApi;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import utils.Function;
import utils.Validation;

public class Submitform extends AppCompatActivity {
    EditText mPatientnameEdittext, mPatientnumberEdittext, mAppoinmentdateEdittext;
    String sPatientname, sPatientnumber, sAppoinmentdate;
    Button mSubmitform;
    String formattedDate;


    public static SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    public static SharedPreferences.Editor editor;
    public static final String EmailId = "emailId";
    public static final String HospitalId = "hospitalid";
    public static final String SpecialId = "specialId";
    public static final String DoctorId = "doctorid";
    public static String mEmail_sharedPref, mHospital_sharedpref, mSpecialpref, mDoctorpref;
    TextView btnAm, btnPm;
    String sSession = "AM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submitform);

        // Now formattedDate have current date/time

        mPatientnameEdittext = (EditText) findViewById(R.id.ET_name);
        mPatientnumberEdittext = (EditText) findViewById(R.id.ET_number);
        //  mAppoinmentdateEdittext = (EditText) findViewById(R.id.ET_appoinmentdate);

        btnAm = (TextView) findViewById(R.id.btn_am);
        btnPm = (TextView) findViewById(R.id.btn_pm);
        sharedpreferences = getApplicationContext().getSharedPreferences(
                MyPREFERENCES, MODE_PRIVATE);
        editor = sharedpreferences.edit();
        mHospital_sharedpref = sharedpreferences.getString(HospitalId, null);
        mSpecialpref = sharedpreferences.getString(HospitalId, null);
        mDoctorpref = sharedpreferences.getString(DoctorId, null);
        mEmail_sharedPref = sharedpreferences.getString(EmailId, null);
        btnAm.setTextColor(Color.GRAY);
        btnPm.setTextColor(Color.GRAY);
        /*
        btnAm.setBackgroundColor(Color.parseColor("eedab3"));
        btnPm.setBackgroundColor(Color.parseColor("eedab3"));*/
        btnAm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAm.setTextColor(Color.BLUE);
                btnPm.setTextColor(Color.GRAY);

                sSession = "AM";

            }
        });

        btnPm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAm.setTextColor(Color.GRAY);
                btnPm.setTextColor(Color.BLUE);
                sSession = "PM";
            }
        });

        mSubmitform = (Button) findViewById(R.id.btn_submit);
        mSubmitform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Calendar c = Calendar.getInstance();


                System.out.println("Current time =&gt; " + c.getTime());

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                formattedDate = df.format(c.getTime());

                Log.v("Res current time ", "" + formattedDate);

                sPatientname = mPatientnameEdittext.getText().toString();
                sPatientnumber = mPatientnumberEdittext.getText().toString();

                if (sPatientname.isEmpty()) {
                    Toast.makeText(Submitform.this, "Name is required ", Toast.LENGTH_SHORT).show();
                }

                if (sPatientnumber.isEmpty()) {
                    Toast.makeText(Submitform.this, "Phone number is required ", Toast.LENGTH_SHORT).show();
                }
                if (sSession.isEmpty()) {
                    Toast.makeText(Submitform.this, "Session is required ", Toast.LENGTH_SHORT).show();
                }

                if (sPatientnumber.length() < 10 || sPatientnumber.length() > 15) {
                    Toast.makeText(Submitform.this, "Valid mobile number is required ", Toast.LENGTH_SHORT).show();
                }
                //    sAppoinmentdate = mPasswordEdittext.getText().toString();
                //    Validation oValidation = Validation.getInstance(getApplicationContext());
                //  boolean boolValidate = oValidation.loginValidation(sPatientname, sPatientnumber);
                //if (boolValidate) {

                if (!sPatientname.isEmpty() && !sPatientnumber.isEmpty() && !sSession.isEmpty()) {


                    boolean network = Function.isNetworkAvailable(Submitform.this);
                    if (network == false) {
                        Function.showToast(Submitform.this, "Please activate the network", Toast.LENGTH_LONG);
                    } else {
                        final CustomProgressDialog oCustomProgressDialog = CustomProgressDialog.getInstance();
                        oCustomProgressDialog.show(Submitform.this, "", "Please wait...");
                        submitformApi.getInstance().Callresponse(new Callback<submitformApi.BaseResponsesubmitform>() {
                            @Override
                            public void success(submitformApi.BaseResponsesubmitform baseResponsesubmitform, Response response) {
                                oCustomProgressDialog.dismiss();


                                mPatientnameEdittext.setText(" ");
                                mPatientnumberEdittext.setText(" ");
                                sPatientname = " ";
                                sPatientnumber = " ";
                                editor.putString(EmailId, null);

                                editor.clear();
                                sSession = "";
                                btnAm.setTextColor(Color.GRAY);
                                btnPm.setTextColor(Color.GRAY);
                                mHospital_sharedpref = sharedpreferences.getString(HospitalId, null);
                                mSpecialpref = sharedpreferences.getString(HospitalId, null);
                                mDoctorpref = sharedpreferences.getString(DoctorId, null);
                                mEmail_sharedPref = sharedpreferences.getString(EmailId, null);

                                Intent intent = new Intent(Submitform.this, Thankyouform.class);
                                startActivity(intent);
                                Toast.makeText(Submitform.this, baseResponsesubmitform.getMessage(), Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void failure(RetrofitError error) {

                                Toast.makeText(Submitform.this, error.getMessage(), Toast.LENGTH_SHORT).show();

                                oCustomProgressDialog.dismiss();
                            }
                        }, sPatientname, sPatientnumber, formattedDate, mSpecialpref, mDoctorpref, mHospital_sharedpref, sSession);
                    }
                }
            }
        });


    }

}
