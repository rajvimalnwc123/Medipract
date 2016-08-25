package com.example.nwcemp01.medipract;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

/**
 * Created by nwcemp01 on 26/7/16.
 */
public class BooklaterSubmitform extends AppCompatActivity {
    EditText mPatientnameEdittext, mPatientnumberEdittext, mAppoinmentdateEdittext;
    String sPatientname, sPatientnumber, sAppoinmentdate;
    Button mSubmitform;
    String formattedDate;

    TextView btnAm, btnPm;

    public static SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs";
    public static SharedPreferences.Editor editor;
    public static final String EmailId = "emailId";
    public static final String HospitalId = "hospitalid";
    public static final String SpecialId = "specialId";
    public static final String DoctorId = "doctorid";
    public static final String Dateselect = "dateselect";
    public static String mDateselect_sharedPref, mHospital_sharedpref, mSpecialpref, mDoctorpref;
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

                mDateselect_sharedPref = sharedpreferences.getString(Dateselect, null);
                sPatientname = mPatientnameEdittext.getText().toString();
                sPatientnumber = mPatientnumberEdittext.getText().toString();
                boolean network = Function.isNetworkAvailable(BooklaterSubmitform.this);
                if (sPatientname.isEmpty()) {
                    Toast.makeText(BooklaterSubmitform.this, "Name is required ", Toast.LENGTH_SHORT).show();
                } else if (sPatientnumber.isEmpty()) {
                    Toast.makeText(BooklaterSubmitform.this, "Phone number is required ", Toast.LENGTH_SHORT).show();
                } else if (sPatientnumber.length() < 10 || sPatientnumber.length() > 15) {
                    Toast.makeText(BooklaterSubmitform.this, "Valid mobile number is required ", Toast.LENGTH_SHORT).show();
                } else if (network == false) {
                    Function.showToast(BooklaterSubmitform.this, "Please activate the network", Toast.LENGTH_LONG);
                } else if (network == true) {
                    if (!sPatientname.isEmpty() && !sPatientnumber.isEmpty() && !sSession.isEmpty()) {
                        final CustomProgressDialog oCustomProgressDialog = CustomProgressDialog.getInstance();
                        oCustomProgressDialog.show(BooklaterSubmitform.this, "", "Please wait...");
                        submitformApi.getInstance().Callresponse(new Callback<submitformApi.BaseResponsesubmitform>() {
                            @Override
                            public void success(submitformApi.BaseResponsesubmitform baseResponsesubmitform, Response response) {
                                oCustomProgressDialog.dismiss();

                                mPatientnameEdittext.setText(" ");
                                mPatientnumberEdittext.setText(" ");
                                sPatientname = " ";
                                sPatientnumber = " ";
                                editor.clear();
                                Log.v("Submit form", baseResponsesubmitform.getMessage());
                                mHospital_sharedpref = sharedpreferences.getString(HospitalId, null);
                                mSpecialpref = sharedpreferences.getString(HospitalId, null);
                                mDoctorpref = sharedpreferences.getString(DoctorId, null);
                                sSession = "";
                                btnAm.setTextColor(Color.GRAY);
                                btnPm.setTextColor(Color.GRAY);
                                Intent intent = new Intent(BooklaterSubmitform.this, Thankyouform.class);
                                startActivity(intent);
                                Toast.makeText(BooklaterSubmitform.this, baseResponsesubmitform.getMessage(), Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void failure(RetrofitError error) {

                                Toast.makeText(BooklaterSubmitform.this, error.getMessage(), Toast.LENGTH_LONG).show();

                                oCustomProgressDialog.dismiss();
                            }
                        }, sPatientname, sPatientnumber, mDateselect_sharedPref, mSpecialpref, mDoctorpref, mHospital_sharedpref, "PM");
                    }
                }
            }
        });


    }

}
